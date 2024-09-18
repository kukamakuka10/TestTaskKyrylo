package com.privat.payment.paymentbusinessservice.controller.dto;



import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
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
    @Size(min = 8, max = 8, message = "Okpo must be exactly 8 digits.")
    @Digits(integer = 8, fraction = 0, message = "Okpo must contain only digits.")
    private String recipientOkpo;
    @NotNull
    private String recipientName;
    @NotNull
    @Min(value = 60,message = "Period must be >= 60 seconds")
    private Long period;
    @NotNull
    @Min(value = 100,message = "Payment amount must be at least 100")
    private Long paymentAmount;
}
