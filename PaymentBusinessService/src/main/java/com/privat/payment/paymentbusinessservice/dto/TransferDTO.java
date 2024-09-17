package com.privat.payment.paymentbusinessservice.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

@Data
@Builder
@Jacksonized
public class TransferDTO {
    private String id;
    private PaymentInstructionDTO paymentInstruction;
    private LocalDateTime transactionDateTime;
    private Long transactionAmount;
    private TransactionStatus status;
}
