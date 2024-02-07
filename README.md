# This project developed by Java@ Spring Boot@

## Stack of technology:

### Develop:

1. OOP
2. Lombok
3. TomCat server
4. Spring boot validation
5. Java17

### Data:

1. database by (Postgres)
2. Spring Data JPA
3. H2
4. Mysql

### Security:

1. Spring Security by Java Web Token(JWT)

### Testing:

1. MockMVC
2. Junit
3. H2 database in memory for testing
4. Swagger
5. Postman

### Deploy/Package:

1. Maven
2. Docker
3. Spring boot actuator

___

## Instruction for start:

___ 

#### 1) for application-local.properties

### db - MySQL with name "grooming_salon"

### require ENV:

     ${MYSQL_USERNAME}
     ${MYSQL_PASSWORD}
     ${JWT_SECRET_KEY}
     ${MAIL_PASSWORD}

> - uncomment annotation "@PropertySource" in GroomingApplication.java
> - Create ENV
> - Start Mysql server
> - create database with name "grooming_salon"
> - run GroomingApplication.java
___

#### 2) for application.properties

### db - Postgres

### require ENV:

    ${DB_HOST}
    ${DB_PORT}
    ${DB_NAME}
    ${DB_USERNAME}
    ${DB_PASSWORD}
    ${JWT_SECRET_KEY}
    ${MAIL_PASSWORD}

> - comment annotation "@PropertySource" in GroomingApplication.java
> - Create ENV
> - start maven install
> - build Docker image
>   #### #docker build -t 'my-image-name:x.x.x'
>  #### #docker run 'my-image-name:x.x.x'
___


