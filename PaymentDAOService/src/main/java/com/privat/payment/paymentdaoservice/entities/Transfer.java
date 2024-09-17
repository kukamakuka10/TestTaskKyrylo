package com.privat.payment.paymentdaoservice.entities;

import com.privat.payment.paymentdaoservice.entities.enums.TransactionStatus;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
public class Transfer {
    @Id
    private String id;
    @ManyToOne
    @JoinColumn(name = "payment_instruction_id")
    private PaymentInstruction paymentInstruction;
    private LocalDateTime transactionDateTime;
    private Long transactionAmount;
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    @PrePersist
    protected void onCreate() {
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
        transactionDateTime = LocalDateTime.now();
    }
}
