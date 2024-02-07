DROP TABLE IF EXISTS appointments;
DROP TABLE IF EXISTS pets;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS grooming_services;
DROP TABLE IF EXISTS breeds;

CREATE TABLE IF NOT EXISTS breeds
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS grooming_services
(
    id                 INT AUTO_INCREMENT PRIMARY KEY,
    name               VARCHAR(20) NOT NULL,
    size               VARCHAR(50) NOT NULL,
    description        VARCHAR(255),
    price              FLOAT,
    duration_procedure TIME(6)
);

CREATE TABLE IF NOT EXISTS users
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

CREATE TABLE IF NOT EXISTS pets
(
    breed_id      INT,
    id            INT AUTO_INCREMENT PRIMARY KEY,
    owner_id      INT,
    name          VARCHAR(20) NOT NULL,
    special_notes VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS appointments
(
    id              INT AUTO_INCREMENT PRIMARY KEY,
    client_id       INT,
    pet_id          INT,
    service_id      INT,
    date_time_end   TIMESTAMP(6),
    date_time_start TIMESTAMP(6),
    status          VARCHAR(255)
);