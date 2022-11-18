package io.azfa.db.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "cars")
public class CarEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean active;

    private LocalDateTime created;

    private LocalDateTime firstRegistrationDate;

    private String description;

    public CarEntity() {
    }

    public CarEntity(boolean active, LocalDateTime created, LocalDateTime firstRegistrationDate, String description) {
        this.active = active;
        this.created = created;
        this.firstRegistrationDate = firstRegistrationDate;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getFirstRegistrationDate() {
        return firstRegistrationDate;
    }

    public void setFirstRegistrationDate(LocalDateTime firstRegistrationDate) {
        this.firstRegistrationDate = firstRegistrationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "CarEntity{" +
            "id=" + id +
            ", active=" + active +
            ", created=" + created +
            ", firstRegistrationDate=" + firstRegistrationDate +
            ", description=" + description +
            '}';
    }
}
