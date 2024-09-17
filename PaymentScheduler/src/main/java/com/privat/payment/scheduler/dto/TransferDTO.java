package com.privat.payment.scheduler.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransferDTO {
    private String id;
    private PaymentInstructionDTO paymentInstruction;
    private LocalDateTime transactionDateTime;
    private Long transactionAmount;
    private TransactionStatus status;
}
