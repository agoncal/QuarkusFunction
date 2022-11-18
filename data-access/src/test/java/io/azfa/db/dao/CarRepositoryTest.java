package io.azfa.db.dao;

import io.azfa.db.entities.CarEntity;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
class CarRepositoryTest {

    @Inject
    CarRepository carRepository;

    @Test
    void shouldSaveAndCheckCars() {
        long initialNumberOfCars = carRepository.count();

        System.out.println("########################");
        System.out.println(initialNumberOfCars);
        carRepository.listAll().forEach(System.out::println);
        System.out.println("########################");

        assertEquals(11, initialNumberOfCars); // Number of cars in the import.sql file

        CarEntity fiesta = new CarEntity(true, LocalDateTime.now(), LocalDateTime.now(), "Ford Fiesta");
        carRepository.persist(fiesta);
        assertNotNull(fiesta.getId());
        assertEquals(initialNumberOfCars + 1, carRepository.count());

        CarEntity audit = new CarEntity(false, LocalDateTime.now(), LocalDateTime.now(), "Audit A4");
        carRepository.persist(audit);
        assertNotNull(audit.getId());
        assertEquals(initialNumberOfCars + 2, carRepository.count());
    }
}
