package io.quarkus.db.dao;

import io.quarkus.db.entities.CarEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public
class CarRepository implements PanacheRepository<CarEntity> {

}
