package io.quarkus.db.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

class AbstractDao<Entity> {

    private final EntityManager entityManager;

    protected AbstractDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Entity> find(String query, Class<Entity> clazz) {
        Query nativeQuery = entityManager.createNativeQuery(query, clazz);
        return nativeQuery.getResultList();
    }
}
