package com.serenity.api.serenity.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class ObjectCustomRepository {

    @PersistenceContext
    private final EntityManager em;

    public Boolean isDuplicated(Class<?> object, String field, Object value) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<?> root = query.from(object);
        query.select(cb.count(root)).where(cb.equal(root.get(field), value));

        return em.createQuery(query).getSingleResult() > 0;
    }
}
