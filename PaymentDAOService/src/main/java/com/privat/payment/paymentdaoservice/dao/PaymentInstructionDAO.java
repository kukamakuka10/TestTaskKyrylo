package com.privat.payment.paymentdaoservice.dao;

import com.privat.payment.paymentdaoservice.entities.PaymentInstruction;

import java.util.List;

public interface PaymentInstructionDAO extends HibernateDAO<PaymentInstruction, String> {

    List<PaymentInstruction> findAllByPayerInn(String payerInn);

    List<PaymentInstruction> findAllByRecipientOkpo(String recipientOkpo);
}
