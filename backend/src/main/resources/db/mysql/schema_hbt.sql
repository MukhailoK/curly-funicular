drop table if exists appointments;

drop table if exists grooming_services;

drop table if exists pets;

drop table if exists breeds;

drop table if exists users;

create table  appointments
(
    id              integer not null auto_increment,
    client_id       integer,
    pet_id          integer,
    service_id      integer,
    date_time_end   datetime(6),
    date_time_start datetime(6),
    status          varchar(255),
    primary key (id)
) engine = InnoDB;

create table breeds
(
    id   integer     not null auto_increment,
    name varchar(20) not null,
    primary key (id)
) engine = InnoDB;

create table grooming_services
(
    id                 integer     not null auto_increment,
    name               varchar(20) not null,
    size               varchar(50) not null,
    description        varchar(255),
    price              float(53),
    duration_procedure time(6),
    primary key (id)
) engine = InnoDB;

create table pets
(
    breed_id      integer,
    id            integer            not null auto_increment,
    owner_id      integer,
    name          varchar(20)        not null,
    special_notes varchar(255),
    primary key (id)
) engine = InnoDB;

create table users
(
    id                integer                          not null auto_increment,
    registration_date date,
    email             varchar(20)                      not null,
    lastname          varchar(20),
    name              varchar(20)                      not null,
    phone             varchar(20),
    username          varchar(20),
    address           varchar(255),
    password          varchar(255),
    role              enum ('CLIENT','ADMIN','MASTER') not null,
    primary key (id)
) engine = InnoDB;

 alter table users add constraint UK_6dotkott2kjsp8vw4d0m25fb7 unique (email);
 alter table appointments add constraint FKgdcpcx3yc2abu5oyb2078lc24 foreign key (client_id) references users (id);
 alter table appointments add constraint FK7i0uf0qa7rq380d73rapvhsny foreign key (service_id) references grooming_services (id);
 alter table appointments add constraint FK62dl3dvwsbveq3vv067becwmj foreign key (pet_id) references pets (id);
 alter table pets add constraint FKr2wnqcmtrr16oaipocajcdn7w foreign key (breed_id) references breeds (id);
 alter table pets add constraint FKoygstexeo9ivoylgrdrv2tc39 foreign key (owner_id) references users (id);