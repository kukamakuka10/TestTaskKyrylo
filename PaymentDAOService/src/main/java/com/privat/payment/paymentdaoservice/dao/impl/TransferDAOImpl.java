package com.privat.payment.paymentdaoservice.dao.impl;

import com.privat.payment.paymentdaoservice.dao.AbstractHibernateDAO;
import com.privat.payment.paymentdaoservice.dao.TransferDAO;
import com.privat.payment.paymentdaoservice.entities.Transfer;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class TransferDAOImpl extends AbstractHibernateDAO<Transfer, String> implements TransferDAO {

    public TransferDAOImpl(EntityManager entityManager) {
        super(Transfer.class, entityManager);
    }

    @Override
    public void save(Transfer transfer) {
        super.save(transfer);
    }

    @Override
    public Transfer update(Transfer transfer) {
        return super.update(transfer);
    }

    @Override
    public boolean delete(String id) {
       return super.delete(id);
    }

    @Override
    public Optional<Transfer> findById(String id) {
        return super.findById(id);
    }

    @Override
    public List<Transfer> findAllByPaymentInstructionId(String id) {
        return super.getEntityManager().createQuery("SELECT t FROM Transfer t WHERE t.paymentInstruction.id = :paymentInstructionId", Transfer.class)
                .setParameter("paymentInstructionId", id)
                .getResultList();
    }
}
