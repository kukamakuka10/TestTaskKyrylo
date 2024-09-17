package com.privat.payment.paymentbusinessservice.controller.dto;



import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class PaymentInstructionRequestDTO {
    @NotNull
    private String payerName;
    @NotNull
    @Size(min = 10, max = 10, message = "The INN must be exactly 10 digits long.")
    @Digits(integer = 10, fraction = 0, message = "The INN must contain only digits.")
    private String payerInn;
    @NotNull
    @Size(min = 16, max = 16, message = "The card number must be exactly 10 digits long.")
    @Digits(integer = 16, fraction = 0, message = "The card number must contain only digits.")
    private String payerCardNumber;
    @NotNull
    private String recipientAccount;
    @NotNull
    @Size(min = 6, max = 6, message = "MFO must be exactly 6 digits.")
    @Digits(integer = 6, fraction = 0, message = "MFO must contain only digits.")
    private String recipientMfo;
    @NotNull
    private String recipientOkpo;
    @NotNull
    private String recipientName;
    @NotNull
    private Long period;
    @NotNull
    private Long paymentAmount;
}
