package com.privat.payment.paymentdaoservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class PaymentInstruction {
    @Id
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

    @PrePersist
    protected void onCreate() {
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
    }
}
