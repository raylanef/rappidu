package br.com.rappidu.application.gateways;

import br.com.rappidu.domain.entities.Customer;

public interface CustomerGateway {

    Customer findByCpf(String cpf);
    Customer save(Customer customer);
}
