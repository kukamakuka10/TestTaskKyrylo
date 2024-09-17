package com.privat.payment.paymentbusinessservice.controller;

import com.privat.payment.paymentbusinessservice.dto.PaymentInstructionDTO;
import com.privat.payment.paymentbusinessservice.dto.WrapperListResponse;
import com.privat.payment.paymentbusinessservice.controller.dto.PaymentInstructionRequestDTO;
import com.privat.payment.paymentbusinessservice.service.PaymentInstructionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment/instruction")
@AllArgsConstructor
public class PaymentInstructionController {

    private final PaymentInstructionService paymentInstructionService;

    @PostMapping
    public HttpStatus create(@RequestBody @Valid PaymentInstructionRequestDTO paymentInstruction) {
        paymentInstructionService.create(paymentInstruction);
        return HttpStatus.CREATED;
    }

    @GetMapping
    public ResponseEntity<WrapperListResponse<PaymentInstructionDTO>> getAll() {

        WrapperListResponse<PaymentInstructionDTO> paymentInstructions =
                paymentInstructionService.getAll();

        return ResponseEntity.ok(paymentInstructions);
    }

    @GetMapping("/payer")
    public ResponseEntity<WrapperListResponse<PaymentInstructionDTO>> getAllByPayerInn(
            @RequestParam("payerInn") String payerInn) {

        WrapperListResponse<PaymentInstructionDTO> paymentInstructions =
                paymentInstructionService.getAllByPayerInn(payerInn);

        return ResponseEntity.ok(paymentInstructions);
    }

    @GetMapping("/recipient")
    public ResponseEntity<WrapperListResponse<PaymentInstructionDTO>> getAllByRecipientOkpo(
            @RequestParam("recipientOkpo") String recipientOkpo) {

        WrapperListResponse<PaymentInstructionDTO> paymentInstructions =
                paymentInstructionService.getAllByRecipientOkpo(recipientOkpo);

        return ResponseEntity.ok(paymentInstructions);
    }
}
