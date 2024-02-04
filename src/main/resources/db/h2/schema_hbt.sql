DROP TABLE IF EXISTS appointments;
DROP TABLE IF EXISTS grooming_services;
DROP TABLE IF EXISTS pets;
DROP TABLE IF EXISTS breeds;
DROP TABLE IF EXISTS users;

CREATE TABLE appointments
(
    id              INT AUTO_INCREMENT PRIMARY KEY,
    client_id       INT,
    pet_id          INT,
    service_id      INT,
    date_time_end   TIMESTAMP(6),
    date_time_start TIMESTAMP(6),
    status          VARCHAR(255)
);

CREATE TABLE breeds
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20) NOT NULL
);

CREATE TABLE grooming_services
(
    id                 INT AUTO_INCREMENT PRIMARY KEY,
    name               VARCHAR(20) NOT NULL,
    size               VARCHAR(50) NOT NULL,
    description        VARCHAR(255),
    price              FLOAT,
    duration_procedure TIME(6)
);

CREATE TABLE pets
(
    breed_id      INT,
    id            INT AUTO_INCREMENT PRIMARY KEY,
    owner_id      INT,
    name          VARCHAR(20) NOT NULL,
    special_notes VARCHAR(255)
);

CREATE TABLE users
(
    id                INT AUTO_INCREMENT PRIMARY KEY,
    registration_date DATE,
    email             VARCHAR(20)                                 NOT NULL,
    lastname          VARCHAR(20),
    name              VARCHAR(20)                                 NOT NULL,
    phone             VARCHAR(20),
    username          VARCHAR(20),
    address           VARCHAR(255),
    password          VARCHAR(255),
    role              ENUM ('CLIENT', 'ADMIN', 'MASTER', 'GUEST') NOT NULL
);

ALTER TABLE users
    ADD CONSTRAINT UK_6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email);
ALTER TABLE appointments
    ADD CONSTRAINT FKgdcpcx3yc2abu5oyb2078lc24 FOREIGN KEY (client_id) REFERENCES users (id);
ALTER TABLE appointments
    ADD CONSTRAINT FK7i0uf0qa7rq380d73rapvhsny FOREIGN KEY (service_id) REFERENCES grooming_services (id);
ALTER TABLE appointments
    ADD CONSTRAINT FK62dl3dvwsbveq3vv067becwmj FOREIGN KEY (pet_id) REFERENCES pets (id);
ALTER TABLE pets
    ADD CONSTRAINT FKr2wnqcmtrr16oaipocajcdn7w FOREIGN KEY (breed_id) REFERENCES breeds (id);
ALTER TABLE pets
    ADD CONSTRAINT FKoygstexeo9ivoylgrdrv2tc39 FOREIGN KEY (owner_id) REFERENCES users (id);
