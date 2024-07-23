package br.com.rappidu.infra.gateways;

import br.com.rappidu.infra.persistence.repositories.exceptions.CustomerNotFoundException;

import br.com.rappidu.domain.entities.Customer;
import br.com.rappidu.application.gateways.CustomerGateway;
import br.com.rappidu.infra.persistence.entities.CustomerEntity;
import br.com.rappidu.infra.mappers.CustomerEntityMapper;
import br.com.rappidu.infra.persistence.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class CustomerRepositoryGateway implements CustomerGateway {

    private final CustomerRepository repo;
    private final CustomerEntityMapper mapper;

    @Override
    public Customer findByCpf(String cpf) {
        return repo.findByCpf(cpf)
                .map(mapper::toModel)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found"));
    }

    @Override
    public Customer save(Customer customer) {
        CustomerEntity entity = mapper.toEntity(customer);
        CustomerEntity response = repo.save(entity);

        return mapper.toModel(response);
    }
}
