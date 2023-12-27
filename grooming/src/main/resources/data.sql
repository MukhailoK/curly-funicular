CREATE TABLE IF NOT EXISTS role
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(25)
);
CREATE TABLE IF NOT EXISTS breed
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50)
);
CREATE TABLE IF NOT EXISTS pet_type
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50)
);
CREATE TABLE IF NOT EXISTS discount
(
    discount_id   INT AUTO_INCREMENT PRIMARY KEY,
    clients_id    INT REFERENCES client (id),
    discount_rate DOUBLE,
    total_visits  INT

);
CREATE TABLE IF NOT EXISTS breed
(
    breed_id INT AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(255),
    pet_id   INT,
    FOREIGN KEY (pet_id) REFERENCES pet (petId)
);
CREATE TABLE IF NOT EXISTS employee
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    name      VARCHAR(255),
    user_name VARCHAR(255),
    password  VARCHAR(255),
    email     VARCHAR(255),
    phone     VARCHAR(20),
    address   VARCHAR(255)
);
CREATE TABLE IF NOT EXISTS schedule
(
    schedule_id INT AUTO_INCREMENT PRIMARY KEY,
    employee_id INT,
    FOREIGN KEY (employee_id) REFERENCES employee (id),
    day_of_week INT,
    start_time  TIMESTAMP,
    end_time    TIMESTAMP
);
CREATE TABLE IF NOT EXISTS grooming_service
(
    service_id        INT AUTO_INCREMENT PRIMARY KEY,
    name              VARCHAR(50),
    description       VARCHAR(500),
    price             DOUBLE,
    durationProcedure TIMESTAMP
);
CREATE TABLE IF NOT EXISTS client
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(255),
    user_name  VARCHAR(255),
    password   VARCHAR(255),
    email      VARCHAR(255),
    phone      VARCHAR(20),
    is_blocked BOOLEAN,
    discount   INT REFERENCES discount (discount_id)
);

CREATE TABLE IF NOT EXISTS pet_type
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255)

);
CREATE TABLE IF NOT EXISTS pet
(
    petId     INT AUTO_INCREMENT PRIMARY KEY,
    ownerId   INT,
    petTypeId INT,
    breedId   INT,
    photoUrl  VARCHAR(255),
    FOREIGN KEY (ownerId) REFERENCES client (id),
    FOREIGN KEY (petTypeId) REFERENCES pet_type (id),
    FOREIGN KEY (breedId) REFERENCES breed (id)
);
CREATE TABLE IF NOT EXISTS appointment
(
    appointment_id INT AUTO_INCREMENT PRIMARY KEY,
    dateTimeStart  DATETIME,
    dateTimeEnd    DATETIME,
    status         VARCHAR(255),
    serviceId      INT,
    FOREIGN KEY (serviceId) REFERENCES grooming_service (service_id),
    clientId       INT,
    FOREIGN KEY (clientId) REFERENCES client (id),
    employeeId     INT,
    FOREIGN KEY (employeeId) REFERENCES employee (id),
    petId          INT,
    FOREIGN KEY (petId) REFERENCES pet (petId)
);
CREATE TABLE IF NOT EXISTS review
(
    review_id      INT AUTO_INCREMENT PRIMARY KEY,
    rating         DOUBLE,
    review         VARCHAR(255),
    appointment_id INT,
    FOREIGN KEY (appointment_id) REFERENCES appointment (appointment_id)
);
