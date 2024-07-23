package br.com.rappidu.application.usecases;

import br.com.rappidu.application.gateways.CustomerGateway;
import br.com.rappidu.domain.entities.Cpf;
import br.com.rappidu.domain.entities.Customer;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindCustomerUseCase {

    private final CustomerGateway customerGateway;

    public Customer findByCpf(Cpf cpf)  {
        return customerGateway.findByCpf(cpf.get());
    }
}
