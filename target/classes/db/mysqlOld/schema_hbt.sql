
alter table  appointments
    drop foreign key FKjbablcqyqk60ajd230pufna0u;

drop table if exists review;

drop table if exists appointments;

drop table if exists grooming_services;

drop table if exists pets;

drop table if exists breeds;

drop table if exists users;

drop table if exists discounts;

create table  appointments
(
    client_id       integer,
    id              integer not null auto_increment,
    master_id       integer,
    pet_id          integer,
    review_id       integer,
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

create table discounts
(
    discount_rate float(53),
    id            integer     not null auto_increment,
    total_visits  integer,
    name          varchar(50) not null,
    primary key (id)
) engine = InnoDB;

create table grooming_services
(
    active             bit,
    duration_procedure time(6),
    id                 integer     not null auto_increment,
    price              float(53),
    name               varchar(20) not null,
    size               varchar(50) not null,
    description        varchar(255),
    primary key (id)
) engine = InnoDB;

create table pets
(
    breed_id      integer,
    id            integer            not null auto_increment,
    owner_id      integer,
    name          varchar(20)        not null,
    photo_url     varchar(255),
    special_notes varchar(255),
    pet_type      enum ('DOG','CAT') not null,
    primary key (id)
) engine = InnoDB;

create table review
(
    appointment_id integer,
    id             integer not null auto_increment,
    rating         float(53),
    review         varchar(255),
    primary key (id)
) engine = InnoDB;

create table users
(
    discount_id       integer,
    id                integer                          not null auto_increment,
    is_blocked        bit,
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


alter table appointments
    add constraint UK_i4p570ke17dvgp0408w0pvvow unique (review_id);
alter table review
    add constraint UK_p949n9n9yuj65778u7pedwtkn unique (appointment_id);
alter table users
    add constraint UK_6dotkott2kjsp8vw4d0m25fb7 unique (email);
alter table appointments
    add constraint FKgdcpcx3yc2abu5oyb2078lc24 foreign key (client_id) references users (id);
alter table appointments
    add constraint FK7i0uf0qa7rq380d73rapvhsny foreign key (service_id) references grooming_services (id);
alter table appointments
    add constraint FKrqdfukjcn0rhqrac8j96cu9w4 foreign key (master_id) references users (id);
alter table appointments
    add constraint FK62dl3dvwsbveq3vv067becwmj foreign key (pet_id) references pets (id);
alter table appointments
    add constraint FKjbablcqyqk60ajd230pufna0u foreign key (review_id) references review (id);
alter table pets
    add constraint FKr2wnqcmtrr16oaipocajcdn7w foreign key (breed_id) references breeds (id);
alter table pets
    add constraint FKoygstexeo9ivoylgrdrv2tc39 foreign key (owner_id) references users (id);
alter table review
    add constraint FK9sqilnr9peew7dunw8o4ajcup foreign key (appointment_id) references appointments (id);
alter table users
    add constraint FKojrj5qdwkaclxjsqmbcljpxyq foreign key (discount_id) references discounts (id);