package br.com.rappidu.infra.persistence;

import br.com.rappidu.infra.persistence.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
