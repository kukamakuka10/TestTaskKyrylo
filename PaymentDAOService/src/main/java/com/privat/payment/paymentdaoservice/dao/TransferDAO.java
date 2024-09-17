package com.privat.payment.paymentdaoservice.dao;

import com.privat.payment.paymentdaoservice.entities.Transfer;

import java.util.List;

public interface TransferDAO extends HibernateDAO<Transfer, String> {

    List<Transfer> findAllByPaymentInstructionId(String id);
}
