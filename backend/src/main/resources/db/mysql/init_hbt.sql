drop table if exists discounts;
drop table if exists review;
drop table if exists appointments;
drop table if exists grooming_services;
drop table if exists pets;

drop table if exists breeds;
drop table if exists token;
drop table if exists users;

create table appointments
(
    client_id       bigint,
    date_time_end   datetime(6),
    date_time_start datetime(6),
    id              bigint not null auto_increment,
    master_id       bigint,
    pet_id          bigint,
    service_id      bigint,
    status          varchar(255),
    primary key (id)
) engine = InnoDB;
create table breeds
(
    id   bigint      not null auto_increment,
    name varchar(20) not null,
    primary key (id)
) engine = InnoDB;
create table discounts
(
    discount_rate float(53),
    total_visits  integer,
    client_id     bigint,
    id            bigint not null auto_increment,
    primary key (id)
) engine = InnoDB;
create table grooming_services
(
    active             bit,
    duration_procedure time(6),
    price              float(53),
    id                 bigint      not null auto_increment,
    name               varchar(20) not null,
    size               varchar(50) not null,
    description        varchar(255),
    primary key (id)
) engine = InnoDB;
create table pets
(
    breed_id      bigint,
    id            bigint             not null auto_increment,
    owner_id      bigint,
    name          varchar(20)        not null,
    photo_url     varchar(255),
    special_notes varchar(255),
    pet_type      enum ('DOG','CAT') not null,
    primary key (id)
) engine = InnoDB;
create table review
(
    rating         float(53),
    appointment_id bigint,
    id             bigint not null auto_increment,
    review         varchar(255),
    primary key (id)
) engine = InnoDB;
create table token
(
    expired    bit     not null,
    id         integer not null auto_increment,
    revoked    bit     not null,
    user_id    bigint,
    token      varchar(255),
    token_type enum ('BEARER'),
    primary key (id)
) engine = InnoDB;
create table users
(
    is_blocked        bit,
    registration_date date,
    id                bigint                           not null auto_increment,
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
alter table review
    add constraint UK_p949n9n9yuj65778u7pedwtkn unique (appointment_id);
alter table token
    add constraint UK_pddrhgwxnms2aceeku9s2ewy5 unique (token);
alter table appointments
    add constraint FKgdcpcx3yc2abu5oyb2078lc24 foreign key (client_id) references users (id);
alter table appointments
    add constraint FK7i0uf0qa7rq380d73rapvhsny foreign key (service_id) references grooming_services (id);
alter table appointments
    add constraint FKrqdfukjcn0rhqrac8j96cu9w4 foreign key (master_id) references users (id);
alter table appointments
    add constraint FK62dl3dvwsbveq3vv067becwmj foreign key (pet_id) references pets (id);
alter table discounts
    add constraint FK2dlhdtb3rvytmuixec5lp3ol3 foreign key (client_id) references users (id);
alter table pets
    add constraint FKr2wnqcmtrr16oaipocajcdn7w foreign key (breed_id) references breeds (id);
alter table pets
    add constraint FKoygstexeo9ivoylgrdrv2tc39 foreign key (owner_id) references users (id);
alter table review
    add constraint FK9sqilnr9peew7dunw8o4ajcup foreign key (appointment_id) references appointments (id);
alter table token
    add constraint FKj8rfw4x0wjjyibfqq566j4qng foreign key (user_id) references users (id)