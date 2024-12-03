package br.com.rappidu.domain.entities;
import java.math.BigDecimal;

public record Item(Long code, String name, BigDecimal amount, String customs) {
}
