
# This project developed using Java@ Spring Boot@

## Stack of technology:

### Develop:

- Java 17
- Spring Boot

### Database:

- PostgreSQL/MySQL (deploy/local)
- H2 (testing)

### Security:

- JWT

### Testing:

- Spring Boot Test
- Swagger 
- Postman

### Build/Deploy:

- Maven
- Docker

___
# SCHEMA DB
![scr1](images/grooming_db_UML.png)
___
# SCHEMA JWT
![scr2](images/jwt-workflow-4.png)
![scr2](images/jwt-structure.webp)

___

## Instruction for starting:

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
    ${SWITCH_SEND_MAIL}
    ${SERVICE_MAIL}
    ${JWT_LIVE_TIME}
    ${ACTUATOR_USERNAME}
    ${ACTUATOR_PASSWORD}

> - comment annotation "@PropertySource" in GroomingApplication.java
> - Create ENV
> - start maven install
> - build Docker image:
>> #### docker build -t 'my-image-name:x.x.x' 
>> #### docker run 'my-image-name:x.x.x'
___

# Links:

### [Backend production proxy](https://clownfish-app-rrzzv.ondigitalocean.app/)

### [Frontend production](https://clownfish-app-rrzzv.ondigitalocean.app/front/)

### [Swagger](https://clownfish-app-rrzzv.ondigitalocean.app/swagger-ui/index.html)

### [Postman](https://lunar-rocket-878527.postman.co/workspace/Team-Workspace~bb41eb72-1a6e-41bd-86c8-90220f724d60/collection/25310437-4ef92413-b47d-4ad8-961e-7077a7a3d73e)

___
## Authors:
### [Kolotailo M.](https://www.linkedin.com/in/mykhailo-kolotailo-53995a23b/)
### [Dikareva O.](https://github.com/OlenaDikarieva)
