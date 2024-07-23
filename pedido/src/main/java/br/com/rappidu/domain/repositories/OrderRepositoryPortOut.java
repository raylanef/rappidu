package br.com.rappidu.domain.repositories;

import br.com.rappidu.domain.entities.Order;
import br.com.rappidu.domain.entities.Product;

import java.util.List;

public interface OrderRepositoryPortOut {
    Order save(Order order);
    Product findProductByCode(Long id);
    Order findByCode(Long id);
    List<Order> findAll();
}
