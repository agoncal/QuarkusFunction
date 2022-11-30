create table cars
(
    id                    bigint not null auto_increment,
    active                bit    not null,
    description           varchar(255),
    created               datetime(6),
    firstRegistrationDate datetime(6),
    primary key (id)
) engine=InnoDB;

INSERT INTO cars(id, active, created, firstRegistrationDate, description)
VALUES (1000, true, "2022-11-17 10:10:10", "2022-11-17 10:10:10", 'Alfa Romeo MiTo');
INSERT INTO cars(id, active, created, firstRegistrationDate, description)
VALUES (1001, true, "2022-11-17 10:10:10", "2022-11-17 10:10:10", 'Alfa Romeo GIULIA QUADRIFOGLIO');
INSERT INTO cars(id, active, created, firstRegistrationDate, description)
VALUES (1002, true, "2022-11-17 10:10:10", "2022-11-17 10:10:10", 'Audi A7 Sportback');
INSERT INTO cars(id, active, created, firstRegistrationDate, description)
VALUES (1003, true, "2022-11-17 10:10:10", "2022-11-17 10:10:10", 'Audi A8');
INSERT INTO cars(id, active, created, firstRegistrationDate, description)
VALUES (1004, true, "2022-11-17 10:10:10", "2022-11-17 10:10:10", 'BMW X6');
INSERT INTO cars(id, active, created, firstRegistrationDate, description)
VALUES (1005, true, "2022-11-17 10:10:10", "2022-11-17 10:10:10", 'BMW X3');
INSERT INTO cars(id, active, created, firstRegistrationDate, description)
VALUES (1006, true, "2022-11-17 10:10:10", "2022-11-17 10:10:10", 'BMW 5 Series');
INSERT INTO cars(id, active, created, firstRegistrationDate, description)
VALUES (1007, true, "2022-11-17 10:10:10", "2022-11-17 10:10:10", 'BMW 7 Series');
INSERT INTO cars(id, active, created, firstRegistrationDate, description)
VALUES (1008, true, "2022-11-17 10:10:10", "2022-11-17 10:10:10", 'BMW Z4 Roadster');
INSERT INTO cars(id, active, created, firstRegistrationDate, description)
VALUES (1009, true, "2022-11-17 10:10:10", "2022-11-17 10:10:10", 'BMW X2');
INSERT INTO cars(id, active, created, firstRegistrationDate, description)
VALUES (1010, true, "2022-11-17 10:10:10", "2022-11-17 10:10:10", 'BMW I8');
