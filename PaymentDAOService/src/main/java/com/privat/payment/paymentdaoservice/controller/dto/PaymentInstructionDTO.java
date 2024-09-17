package com.privat.payment.paymentdaoservice.controller.dto;

import lombok.Data;

@Data
public class PaymentInstructionDTO {
    private String id;
    private String payerName;
    private String payerInn;
    private String payerCardNumber;
    private String recipientAccount;
    private String recipientMfo;
    private String recipientOkpo;
    private String recipientName;
    private Long period;
    private Long paymentAmount;
}
