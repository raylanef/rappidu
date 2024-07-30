package br.com.rappidu.domain.entities;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class Order {

    private final Long code;
    private final String customerName;
    private final BigDecimal amount;
    private final List<Item> items;
    private final LocalDateTime createAt;

    @Setter
    private StatusOrder status;


    public Order(Long code, String customerName, List<Item> items, StatusOrder status, LocalDateTime createAt) {
        this.code = code;
        this.status = status;
        this.customerName = customerName;
        this.items = items;
        this.createAt = createAt;
        this.amount = items.stream()
                .map(Item::amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void pay() {
        this.status = StatusOrder.RECEIVED;
    }

}
