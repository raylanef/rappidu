package br.com.rappidu.infra.controllers.dto;

import java.math.BigDecimal;

public record RequestPaymentUpdate(BigDecimal amount, Integer type, Integer status) {

}
