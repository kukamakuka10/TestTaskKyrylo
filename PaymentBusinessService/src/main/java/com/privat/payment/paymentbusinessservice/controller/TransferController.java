package com.privat.payment.paymentbusinessservice.controller;

import com.privat.payment.paymentbusinessservice.dto.PaymentInstructionDTO;
import com.privat.payment.paymentbusinessservice.dto.TransferDTO;
import com.privat.payment.paymentbusinessservice.dto.WrapperListResponse;
import com.privat.payment.paymentbusinessservice.service.TransferService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment/transfer")
@AllArgsConstructor
public class TransferController {

    private final TransferService transferService;

    @PostMapping
    public HttpStatus create(@RequestBody PaymentInstructionDTO paymentInstructionDTO) {
        transferService.create(paymentInstructionDTO);
        return HttpStatus.CREATED;
    }

    @GetMapping("/check-recurring-transfer")
    public ResponseEntity<Boolean> checkRecurringTransferNeed(
            @RequestParam("paymentInstructionId") String paymentInstructionId) {

        boolean isTransferNeeded = transferService.checkRecurringTransferNeed(paymentInstructionId);
        return ResponseEntity.ok(isTransferNeeded);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        transferService.cancel(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/history")
    public ResponseEntity<WrapperListResponse<TransferDTO>> getHistoryTransfers(
            @RequestParam("paymentInstructionId") String paymentInstructionId) {

        WrapperListResponse<TransferDTO> response = transferService.getHistory(paymentInstructionId);
        return ResponseEntity.ok(response);
    }
}
