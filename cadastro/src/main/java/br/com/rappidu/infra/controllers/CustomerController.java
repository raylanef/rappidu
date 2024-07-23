package br.com.rappidu.infra.controllers;

import br.com.rappidu.infra.controllers.mappers.CustomerModelMapper;
import br.com.rappidu.application.usecases.CreateCustomerUserCase;
import br.com.rappidu.application.usecases.FindCustomerUseCase;
import br.com.rappidu.domain.entities.Cpf;
import br.com.rappidu.domain.entities.Customer;
import br.com.rappidu.infra.controllers.dto.request.CustomerRequestDto;
import br.com.rappidu.infra.controllers.dto.response.CustomerResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@RestController
@RequestMapping("/customers")
@ComponentScan(
        basePackages = "br.com.rappidu.application.usecases",
        includeFilters = @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes ={CreateCustomerUserCase.class, FindCustomerUseCase.class}
        )
)
@AllArgsConstructor
public class CustomerController {

    private final CreateCustomerUserCase createCustomerUserCase;
    private final FindCustomerUseCase findCustomerUseCase;
    private final CustomerModelMapper mapper;

    @GetMapping("/{cpf}")
    public ResponseEntity<CustomerResponseDto> findByCpf(@PathVariable("cpf") String cpf)  {
        var document = new Cpf(cpf);

        Customer response = findCustomerUseCase.findByCpf(document);
        CustomerResponseDto responseDto = mapper.toResponseDTO(response);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDto> create(@RequestBody CustomerRequestDto customerRequestDto) {
        Customer request = mapper.toModel(customerRequestDto);
        Customer response = createCustomerUserCase.create(request);
        CustomerResponseDto responseDto = mapper.toResponseDTO(response);

       responseDto.add(linkTo(methodOn(CustomerController.class)
                .findByCpf(responseDto.getCpf()))
                .withSelfRel());

        return ResponseEntity.ok(responseDto);
    }
}
