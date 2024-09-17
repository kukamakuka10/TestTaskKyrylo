package com.privat.payment.paymentdaoservice.controller.dto;

import com.privat.payment.paymentdaoservice.entities.PaymentInstruction;
import com.privat.payment.paymentdaoservice.entities.enums.TransactionStatus;
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
