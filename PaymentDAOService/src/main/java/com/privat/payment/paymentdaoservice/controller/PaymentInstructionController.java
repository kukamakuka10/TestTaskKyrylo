package com.privat.payment.paymentdaoservice.controller;

import com.privat.payment.paymentdaoservice.controller.dto.PaymentInstructionDTO;
import com.privat.payment.paymentdaoservice.dto.WrapperListResponse;
import com.privat.payment.paymentdaoservice.entities.PaymentInstruction;
import com.privat.payment.paymentdaoservice.service.PaymentInstructionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment/instruction")
@AllArgsConstructor
public class PaymentInstructionController {
    private final PaymentInstructionService paymentInstructionService;

    @PostMapping
    public HttpStatus save(@RequestBody PaymentInstructionDTO paymentInstruction) {
        paymentInstructionService.save(paymentInstruction);
        return HttpStatus.CREATED;
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody PaymentInstructionDTO paymentInstruction) {

        paymentInstructionService.update(paymentInstruction);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        paymentInstructionService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<WrapperListResponse<PaymentInstructionDTO>> findAll() {

        WrapperListResponse<PaymentInstructionDTO> paymentInstructions = paymentInstructionService.findAll();
        return ResponseEntity.ok(paymentInstructions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentInstructionDTO> findById(@PathVariable("id") String id) {

        PaymentInstructionDTO paymentInstructionDTO = paymentInstructionService.findById(id);
        return ResponseEntity.ok(paymentInstructionDTO);
    }

    @GetMapping("/payer")
    public ResponseEntity<WrapperListResponse<PaymentInstructionDTO>> findAllByPayerInn(
            @RequestParam("payerInn") String payerInn) {

        WrapperListResponse<PaymentInstructionDTO> paymentInstructions =
                paymentInstructionService.findAllByPayerInn(payerInn);

        return ResponseEntity.ok(paymentInstructions);
    }

    @GetMapping("/recipient")
    public ResponseEntity<WrapperListResponse<PaymentInstructionDTO>> findAllByRecipientOkpo(
            @RequestParam("recipientOkpo") String recipientOkpo) {

        WrapperListResponse<PaymentInstructionDTO> paymentInstructions =
                paymentInstructionService.findAllByRecipientOkpo(recipientOkpo);

        return ResponseEntity.ok(paymentInstructions);
    }

}
