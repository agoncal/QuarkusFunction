package io.quarkus.db.dao;

@FunctionalInterface
public interface EntityFieldMapper<EntityType> {
    EntityType map(Object[] fields);
}
