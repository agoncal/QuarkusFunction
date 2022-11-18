package io.azfa.db.dao;

import io.azfa.db.entities.CarEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional
public class CarRepository implements PanacheRepository<CarEntity> {

}
