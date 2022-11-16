package io.quarkus.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.quarkus.db.entities.CarEntity;

import java.time.LocalDateTime;

public class CarDto {

    private final Long id;
    private final boolean active;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private final LocalDateTime created;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private final LocalDateTime firstRegistrationDate;
    private final String description;

    public CarDto(boolean active, LocalDateTime created, LocalDateTime firstRegistrationDate, String description) {
        this.id = null;
        this.active = active;
        this.created = created;
        this.firstRegistrationDate = firstRegistrationDate;
        this.description = description;
    }

    public CarDto(CarEntity carEntity) {
        this.id = carEntity.getId();
        this.active = carEntity.isActive();
        this.created = carEntity.getCreated();
        this.firstRegistrationDate = carEntity.getFirstRegistrationDate();
        this.description = carEntity.getDescription();
    }

    public static CarEntity createEntity(CarDto carDto) {
        CarEntity carEntity = new CarEntity();
        carEntity.setActive(carDto.isActive());
        carEntity.setCreated(carDto.getCreated());
        carEntity.setFirstRegistrationDate(carDto.getFirstRegistrationDate());
        carEntity.setDescription(carDto.getDescription());
        return carEntity;
    }

    public Long getId() {
        return id;
    }

    public boolean isActive() {
        return active;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getFirstRegistrationDate() {
        return firstRegistrationDate;
    }

    public String getDescription() {
        return description;
    }
}
