CREATE DATABASE IF NOT EXISTS grooming_salon;

ALTER DATABASE grooming_salon
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;

GRANT ALL PRIVILEGES ON grooming_salon.* TO 'grooming_salon'@'%' IDENTIFIED BY 'grooming_salon';
