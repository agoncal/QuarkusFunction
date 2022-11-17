package io.quarkus.services;

import io.quarkus.db.dao.CarRepository;
import io.quarkus.db.entities.CarEntity;
import io.quarkus.dto.CarDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

import static java.util.stream.Collectors.toList;

@ApplicationScoped
public class ResourceService {

    @Inject
    CarRepository carDao;

    public List<CarDto> getCars() {
        return carDao.findAll()
            .stream()
            .map(CarDto::new)
            .collect(toList());
    }

    public void persistCar(CarDto carDto) {
        CarEntity carEntity = new CarEntity(carDto.isActive(), carDto.getCreated(), carDto.getFirstRegistrationDate(), carDto.getDescription());
        carDao.persist(carEntity);
    }
}
