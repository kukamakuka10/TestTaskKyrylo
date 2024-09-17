package com.privat.payment.paymentbusinessservice.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;


@Data
@Builder
@Jacksonized
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
