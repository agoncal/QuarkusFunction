package io.azfa.function.services;

import io.azfa.db.dao.CarRepository;
import io.azfa.db.entities.CarEntity;
import io.azfa.function.dto.CarDto;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

import static java.util.stream.Collectors.toList;

@ApplicationScoped
public class CarService {

    @Inject
    Logger logger;

    @Inject
    CarRepository carRepository;

    public List<CarDto> getCars() {
        List<CarEntity> cars = carRepository.findAll().list();
        logger.info("Getting all cars from the database: " + cars.size());

        return cars
            .stream()
            .map(CarDto::new)
            .collect(toList());
    }

    public void persistCar(CarDto carDto) {
        CarEntity car = new CarEntity(carDto.isActive(), carDto.getCreated(), carDto.getFirstRegistrationDate(), carDto.getDescription());
        logger.info("Persisting a car into the database: " + car);
        carRepository.persist(car);
    }
}
