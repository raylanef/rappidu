package br.com.rappidu.application.usecases;

import br.com.rappidu.domain.entities.Customer;
import br.com.rappidu.application.gateways.CustomerGateway;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateCustomerUserCase  {

    private final CustomerGateway customerGateway;

    public Customer create(Customer customer) {
        return customerGateway.save(customer);
    }
}
