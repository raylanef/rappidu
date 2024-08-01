package br.com.rappidu.infra.controllers;


import br.com.rappidu.application.usecases.CreateOrderUseCase;
import br.com.rappidu.application.usecases.FindOrderUseCase;
import br.com.rappidu.application.usecases.PayOrderUseCase;
import br.com.rappidu.application.usecases.UpdateStatusOrderUseCase;
import br.com.rappidu.domain.entities.Order;
import br.com.rappidu.domain.entities.OrderRequest;
import br.com.rappidu.infra.controllers.dto.OrderUpdateRequestDTO;
import br.com.rappidu.infra.controllers.dto.mappers.OrderMapper;
import br.com.rappidu.infra.controllers.dto.request.OrderRequestDto;
import br.com.rappidu.infra.controllers.dto.response.OrderResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@RestController
@RequestMapping("/orders")
@ComponentScan(
        basePackages = "br.com.rappidu.application.usecases",
        includeFilters = @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = {CreateOrderUseCase.class, FindOrderUseCase.class,
                        PayOrderUseCase.class, UpdateStatusOrderUseCase.class}
        )
)
@AllArgsConstructor
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;
    private final FindOrderUseCase findOrderUseCase;
    private final PayOrderUseCase payOrderUseCase;
    private final UpdateStatusOrderUseCase updateStatusOrderUseCase;
    private final OrderMapper mapper;

    // TODO Aplicar paginação
    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> findAll() {
        return ResponseEntity.ok(mapper.toResponseDto(findOrderUseCase.listAll()));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<OrderResponseDto> newOrder(@RequestBody OrderRequestDto orderRequestDto) {
        OrderRequest request = mapper.toModel(orderRequestDto);
        Order order = createOrderUseCase.create(request);
        OrderResponseDto orderResponseDto = mapper.toResponseDto(order);

        orderResponseDto.add(linkTo(methodOn(OrderController.class)
                .pay(orderResponseDto.getCode()))
                .withSelfRel());

        return ResponseEntity.ofNullable(orderResponseDto);
    }

    @PostMapping("/{code}/pay")
    public ResponseEntity<?> pay(Long code) {
        Order order = payOrderUseCase.pay(code);
        OrderResponseDto responseDto = mapper.toResponseDto(order);

        responseDto.add(linkTo(methodOn(OrderController.class)
                .findByCode(responseDto.getCode()))
                .withSelfRel());

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{code}")
    public ResponseEntity<OrderResponseDto> findByCode(@PathVariable Long code) {
        var order = findOrderUseCase.findByCode(code);
        var responseDto = mapper.toResponseDto(order);

        return ResponseEntity.ok(responseDto);
    }

    @PatchMapping("/{code}")
    public ResponseEntity<?> updateStatus(Long code, @RequestBody OrderUpdateRequestDTO orderUpdateRequest) {
        updateStatusOrderUseCase.updateStatus(code, orderUpdateRequest.statusOrder());
        return ResponseEntity.ok().build();
    }
}
