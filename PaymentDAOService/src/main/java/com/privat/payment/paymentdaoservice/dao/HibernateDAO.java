package com.privat.payment.paymentdaoservice.dao;

import com.privat.payment.paymentdaoservice.entities.PaymentInstruction;

import java.util.List;
import java.util.Optional;

public interface HibernateDAO<T,ID> {
    void save(final T entity);
    T update(final T entity);
    boolean delete(final ID id);
    Optional<T> findById(final ID id);
    List<T> findAll();
}
