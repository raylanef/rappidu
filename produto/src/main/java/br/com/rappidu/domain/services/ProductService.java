package br.com.rappidu.domain.services;

import br.com.rappidu.domain.entities.Product;
import br.com.rappidu.domain.entities.ProductType;

import java.util.List;

public interface ProductService {
    void create(Product produto);

    List<Product> findByType(ProductType productTypeResponse);

    void remove(Long id);

    void update(Long id, Product product);

    List<Product> all();

}
