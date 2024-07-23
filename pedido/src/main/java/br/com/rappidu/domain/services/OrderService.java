package br.com.rappidu.domain.services;

import br.com.rappidu.domain.entities.Order;
import br.com.rappidu.domain.entities.OrderRequest;

import java.util.List;

public interface OrderService {
    Order create(OrderRequest orderRequest);
    List<Order> listAll();
    Order pay(Long oderId);
    Order findByCode(Long code);
}
