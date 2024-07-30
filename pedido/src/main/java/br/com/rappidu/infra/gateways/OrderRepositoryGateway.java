package br.com.rappidu.infra.gateways;

import br.com.rappidu.application.gateways.OrderGateway;
import br.com.rappidu.infra.exceptions.InvalidProductException;
import br.com.rappidu.infra.exceptions.OrderNotFountException;

import br.com.rappidu.domain.entities.Order;
import br.com.rappidu.domain.entities.Product;

import br.com.rappidu.infra.mappers.OrderEntityMapper;
import br.com.rappidu.infra.persistence.mappers.ProductEntityMapper;
import br.com.rappidu.infra.persistence.OrderRepository;
import br.com.rappidu.infra.persistence.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class OrderRepositoryGateway implements OrderGateway {

    private final ProductRepository productRepository;
    private final ProductEntityMapper productMapper;
    private final OrderRepository repo;
    private final OrderEntityMapper mapper;

    @Override
    public Order save(Order order) {
        var entity = mapper.toEntity(order);
        var response = repo.save(entity);
        return mapper.toModel(response);
    }

    @Override
    public Product findProductByCode(Long code) {
        return productRepository.findById(code)
                .map(productMapper::toModel)
                .orElseThrow(() ->  new InvalidProductException("Product code invalid or not exist"));
    }

    @Override
    public Order findByCode(Long id) {
        return repo.findById(id)
                .map(mapper::toModel)
                .orElseThrow(() -> new OrderNotFountException("Order Not Found"));
    }

    @Override
    public List<Order> findAll() {
       return repo
               .findWhereStatusIsNotFinishedOrWaitPaymentOrderByCreateAtAsc()
               .map(mapper::toModel)
               .orElseThrow(() -> new OrderNotFountException("No Orders found"));
    }

}
