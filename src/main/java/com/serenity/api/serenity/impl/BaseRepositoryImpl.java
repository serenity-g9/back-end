package com.serenity.api.serenity.impl;

import com.serenity.api.serenity.interfaces.BaseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

    @PersistenceContext
    private EntityManager entityManager;

    public BaseRepositoryImpl(JpaEntityInformation<T, ID> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void softDeleteById(ID id) {
        T entity = this.findById(id).orElseThrow(() -> new IllegalArgumentException("Entity not found"));
        try {
            Field deletedAtField = entity.getClass().getSuperclass().getDeclaredField("deletedAt");
            deletedAtField.setAccessible(true);
            deletedAtField.set(entity, LocalDateTime.now());
            entityManager.merge(entity);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Failed to set deletedAt field", e);
        }
    }

    @Override
    @Transactional
    public void undeleteById(ID id) {
        T entity = this.findById(id).orElseThrow(() -> new IllegalArgumentException("Entity not found"));
        try {
            Field deletedAtField = entity.getClass().getSuperclass().getDeclaredField("deletedAt");
            deletedAtField.setAccessible(true);
            deletedAtField.set(entity, null);
            entityManager.merge(entity);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Failed to set deletedAt field", e);
        }
    }

    @Override
    public List<T> findAllActive() {
        String jpql = "SELECT e FROM " + getDomainClass().getName() + " e WHERE e.deletedAt IS NULL";
        return entityManager.createQuery(jpql, getDomainClass()).getResultList();
    }


    @Override
    public Optional<T> findByIdActive(ID id) {
        String jpql = "SELECT e FROM " + getDomainClass().getName() + " e WHERE e.id = :id AND e.deletedAt IS NULL";
        return entityManager.createQuery(jpql, getDomainClass())
                .setParameter("id", id)
                .getResultStream()
                .findFirst();
    }

    @Override
    public List<T> findAll() {
        return findAllActive();
    }
}

