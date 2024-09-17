package com.privat.payment.paymentdaoservice.dao.impl;

import com.privat.payment.paymentdaoservice.dao.AbstractHibernateDAO;
import com.privat.payment.paymentdaoservice.dao.PaymentInstructionDAO;
import com.privat.payment.paymentdaoservice.entities.PaymentInstruction;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PaymentInstructionDAOImpl extends AbstractHibernateDAO<PaymentInstruction, String> implements PaymentInstructionDAO {

    public PaymentInstructionDAOImpl(EntityManager entityManager) {
        super(PaymentInstruction.class, entityManager);
    }

    @Override
    public void save(PaymentInstruction paymentInstruction) {
        super.save(paymentInstruction);
    }

    @Override
    public PaymentInstruction update(PaymentInstruction paymentInstruction) {
        return super.update(paymentInstruction);
    }

    @Override
    public boolean delete(String id) {
       return super.delete(id);
    }

    @Override
    public List<PaymentInstruction> findAll() {
        return super.findAll();
    }

    @Override
    public Optional<PaymentInstruction> findById(String id) {
        return super.findById(id);
    }

    @Override
    public List<PaymentInstruction> findAllByPayerInn(String payerInn) {
        return super.getEntityManager().createQuery("SELECT pi FROM PaymentInstruction pi WHERE pi.payerInn = :payerInn", PaymentInstruction.class)
                .setParameter("payerInn", payerInn)
                .getResultList();
    }

    @Override
    public List<PaymentInstruction> findAllByRecipientOkpo(String recipientOkpo) {
        return super.getEntityManager().createQuery("SELECT pi FROM PaymentInstruction pi WHERE pi.recipientOkpo = :recipientOkpo", PaymentInstruction.class)
                .setParameter("recipientOkpo", recipientOkpo)
                .getResultList();
    }
}
