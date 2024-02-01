-- Drop tables if they exist
DROP TABLE IF EXISTS appointments CASCADE;
DROP TABLE IF EXISTS grooming_services CASCADE;
DROP TABLE IF EXISTS pets CASCADE;
DROP TABLE IF EXISTS breeds CASCADE;
DROP TABLE IF EXISTS users CASCADE;

-- Create table appointments
CREATE TABLE breeds
(
    id   serial PRIMARY KEY,
    name varchar(20) NOT NULL
);

-- Create table breeds
CREATE TABLE grooming_services
(
    id          serial PRIMARY KEY,
    name        varchar(20) NOT NULL,
    size        varchar(50) NOT NULL,
    description varchar(255),
    price       numeric,
    duration_procedure time
);

-- Create table grooming_services
CREATE TABLE users
(
    id                serial PRIMARY KEY,
    registration_date date,
    email             varchar(20)                                                        NOT NULL UNIQUE,
    lastname          varchar(20),
    name              varchar(20)                                                        NOT NULL,
    phone             varchar(20),
    username          varchar(20),
    address           varchar(255),
    password          varchar(255),
    role              varchar(10) CHECK (role IN ('CLIENT', 'ADMIN', 'MASTER', 'GUEST')) NOT NULL
);

-- Create table pets
CREATE TABLE pets
(
    id            serial PRIMARY KEY,
    breed_id      integer REFERENCES breeds (id),
    owner_id      integer REFERENCES users (id),
    name          varchar(20) NOT NULL,
    special_notes varchar(255)
);

-- Create table users
CREATE TABLE appointments
(
    id              serial PRIMARY KEY,
    client_id       integer REFERENCES users (id),
    pet_id          integer REFERENCES pets (id),
    service_id      integer REFERENCES grooming_services (id),
    date_time_end   timestamp(6),
    date_time_start timestamp(6),
    status          varchar(255)
);

-- Add foreign key constraints
ALTER TABLE appointments
    ADD CONSTRAINT fk_client_id FOREIGN KEY (client_id) REFERENCES users (id);
ALTER TABLE appointments
    ADD CONSTRAINT fk_service_id FOREIGN KEY (service_id) REFERENCES grooming_services (id);
ALTER TABLE appointments
    ADD CONSTRAINT fk_pet_id FOREIGN KEY (pet_id) REFERENCES pets (id);
ALTER TABLE pets
    ADD CONSTRAINT fk_breed_id FOREIGN KEY (breed_id) REFERENCES breeds (id);
ALTER TABLE pets
    ADD CONSTRAINT fk_owner_id FOREIGN KEY (owner_id) REFERENCES users (id);
