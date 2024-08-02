package br.com.rappidu.infra.controllers;

import br.com.rappidu.application.usecases.*;
import br.com.rappidu.domain.entities.Payment;
import br.com.rappidu.infra.controllers.dto.RequestPaymentUpdate;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
@ComponentScan(
        basePackages = "br.com.rappidu.application.usecases",
        includeFilters = @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = {UpdatePaymentUseCase.class, FindPaymentUseCase.class}
        )
)
@AllArgsConstructor
public class PaymentController {

    private final FindPaymentUseCase findPaymentUseCase;
    private final UpdatePaymentUseCase updatePaymentUseCase;


    @PatchMapping("/{code}")
    public ResponseEntity<?> update(@PathVariable Long code, @RequestBody RequestPaymentUpdate paymentUpdate) {
        Payment payment = new Payment(code, paymentUpdate.amount(), paymentUpdate.status(), paymentUpdate.type());
        updatePaymentUseCase.update(payment);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{code}")
    public ResponseEntity<Payment> get(@PathVariable Long code) {
        return ResponseEntity.ok(findPaymentUseCase.get(code));
    }
}
