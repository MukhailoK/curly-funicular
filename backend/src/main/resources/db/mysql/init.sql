CREATE DATABASE IF NOT EXISTS grooming_salon;
CREATE SCHEMA IF NOT EXISTS grooming_salon;

USE grooming_salon;

-- This file will create the database if it does not exist.

-- contains the schema for the database.
CREATE TABLE IF NOT EXISTS roles
(
    id   INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(20) NOT NULL
);
CREATE TABLE IF NOT EXISTS pet_types
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20) NOT NULL
);
CREATE TABLE IF NOT EXISTS breeds
(
    id   INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(20) NOT NULL
);
CREATE TABLE IF NOT EXISTS schedules
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    master_id   INT,
    day_of_week INT,
    start_time  TIME,
    end_time    TIME
);
CREATE TABLE IF NOT EXISTS grooming_services
(
    id                 INT AUTO_INCREMENT PRIMARY KEY,
    name               VARCHAR(50),
    size               VARCHAR(50),
    description        VARCHAR(255),
    price              DOUBLE,
    duration_procedure TIME,
    active             BOOLEAN DEFAULT true
);
CREATE TABLE IF NOT EXISTS users
(
    id                INT AUTO_INCREMENT PRIMARY KEY,
    name              VARCHAR(50) NOT NULL,
    lastname          VARCHAR(50) NOT NULL,
    username          VARCHAR(50) NOT NULL,
    password          VARCHAR(50) NOT NULL,
    email             VARCHAR(50) NOT NULL,
    phone             VARCHAR(50),
    registration_date DATE,
    is_blocked        DOUBLE DEFAULT FALSE,
    role_id           INT,
    FOREIGN KEY (role_id) REFERENCES roles (id),
    address           VARCHAR(255),
    schedule_id       INT,
    FOREIGN KEY (schedule_id) REFERENCES schedules (id)
) COLLATE = utf8mb4_unicode_ci;
CREATE TABLE IF NOT EXISTS discounts
(
    id            INT PRIMARY KEY AUTO_INCREMENT,
    client_id     INT,
    discount_rate DECIMAL(5, 2) NOT NULL,
    total_visits  INT           NOT NULL,
    FOREIGN KEY (client_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS pets
(
    id            INT PRIMARY KEY AUTO_INCREMENT,
    name          VARCHAR(20),
    owner_id      INT,
    FOREIGN KEY (owner_id) REFERENCES users (id),
    pet_type_id   INT,
    FOREIGN KEY (pet_type_id) REFERENCES pet_types (id),
    breed_id      INT,
    FOREIGN KEY (breed_id) REFERENCES breeds (id),
    photo_Url     VARCHAR(255),
    special_notes VARCHAR(255)
) COLLATE = utf8mb4_unicode_ci;
CREATE TABLE IF NOT EXISTS appointments
(
    id              INT AUTO_INCREMENT PRIMARY KEY,
    client_id       INT,
    FOREIGN KEY (client_id) REFERENCES users (id),
    master_id       INT,
    FOREIGN KEY (master_id) REFERENCES users (id),
    service_id      INT,
    FOREIGN KEY (service_id) REFERENCES grooming_services (id),
    pet_id          INT,
    FOREIGN KEY (pet_id) REFERENCES pets (id),
    date_time_start DATETIME,
    date_time_end   DATETIME,
    status          VARCHAR(255)

);
CREATE TABLE IF NOT EXISTS review
(
    id             INT AUTO_INCREMENT PRIMARY KEY,
    appointment_id INT,
    FOREIGN KEY (appointment_id) REFERENCES appointments (id),
    rating         DOUBLE,
    review         VARCHAR(255)

);
CREATE TABLE IF NOT EXISTS clients_discounts
(
    client_id    INT,
    FOREIGN KEY (client_id) REFERENCES users (id),
    discounts_id INT,
    FOREIGN KEY (discounts_id) REFERENCES discounts (id)
);
CREATE TABLE IF NOT EXISTS clients_pets
(
    client_id INT,
    FOREIGN KEY (client_id) REFERENCES users (id),
    pets_id   INT,
    FOREIGN KEY (pets_id) REFERENCES pets (id)
);
CREATE TABLE IF NOT EXISTS masters_schedule
(
    master_is   INT,
    FOREIGN KEY (master_is) REFERENCES users (id),
    schedule_id INT,
    FOREIGN KEY (schedule_id) REFERENCES schedules (id)
);
CREATE TABLE IF NOT EXISTS users_discounts
(
    client_id   INT,
    FOREIGN KEY (client_id) REFERENCES users (id),
    discount_id INT,
    FOREIGN KEY (discount_id) REFERENCES discounts (id)
)
#
# ALTER DATABASE grooming_salon
#   DEFAULT CHARACTER SET utf8
#   DEFAULT COLLATE utf8_general_ci;
#
# GRANT ALL PRIVILEGES ON grooming_salon.* TO 'grooming_salon'@'%' IDENTIFIED BY 'grooming_salon';
