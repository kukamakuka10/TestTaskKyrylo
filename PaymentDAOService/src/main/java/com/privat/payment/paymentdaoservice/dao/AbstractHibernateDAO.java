package com.privat.payment.paymentdaoservice.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Transactional
public abstract class AbstractHibernateDAO<T, ID extends Serializable>  {
    private final Class<T> persistentClass;

    private final EntityManager entityManager;

    protected AbstractHibernateDAO(Class<T> persistentClass, EntityManager entityManager) {
        this.persistentClass = persistentClass;
        this.entityManager = entityManager;
    }
    protected EntityManager getEntityManager() {
        return entityManager;
    }
    public void save(final T entity) {
        entityManager.persist(entity);
    }

    public T update(final T entity) {
        return entityManager.merge(entity);
    }

    public boolean delete(final ID id) {
        Optional<T> entityOptional = findById(id);
        entityOptional.ifPresent(entityManager::remove);
        return entityOptional.isPresent();
    }

    public Optional<T> findById(final ID id) {
        return Optional.ofNullable(entityManager.find(persistentClass, id));
    }

    public List<T> findAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(persistentClass);
        Root<T> root = criteriaQuery.from(persistentClass);
        criteriaQuery.select(root);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

}
