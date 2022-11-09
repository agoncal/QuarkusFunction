package io.quarkus.db.dao;

import io.quarkus.db.entities.CarEntity;

import java.util.List;

public interface CarDao {
    List<CarEntity> findAll();
    CarEntity save(CarEntity carEntity);
}
