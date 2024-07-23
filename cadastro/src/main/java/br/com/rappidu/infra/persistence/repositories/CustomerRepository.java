package br.com.rappidu.infra.persistence.repositories;

import br.com.rappidu.infra.persistence.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long>  {
    Optional<CustomerEntity> findByCpf(String cpf);
}
