# -- Создание таблицы roles
# /*
# CREATE TABLE IF NOT EXISTS roles (
#                        id LONG PRIMARY KEY AUTO_INCREMENT,
#                       name VARCHAR(20) NOT NULL
# );
#  */
# DELETE FROM roles;
# ALTER TABLE roles AUTO_INCREMENT = 1;
# INSERT INTO roles (name)
# VALUES ('ADMIN'),
#        ('MASTER'),
#        ('CLIENT'),
#        ('GUEST');


# /*
# CREATE TABLE IF NOT EXISTS clients (
#                          id LONG PRIMARY KEY AUTO_INCREMENT,
#                          name VARCHAR(50) NOT NULL,
#                          lastname VARCHAR(50) NOT NULL,
#                          username VARCHAR(50) NOT NULL,
#                          password VARCHAR(50) NOT NULL,
#                          email VARCHAR(50) NOT NULL,
#                          phone VARCHAR(20),
#                          registration_date DATETIME DEFAULT CURRENT_TIMESTAMP,
#                          is_blocked BIT DEFAULT 0,
#                          role_id LONG
# );
# */
# DELETE FROM clients;
# ALTER TABLE clients AUTO_INCREMENT = 1;
# INSERT INTO clients (name, lastname, username, password, email, phone, date_registration,is_blocked,role_id)
# VALUES
#     ('Name1', 'LastName1', 'username1', 'pa$$1111', 'client1@example.com', '123456789',CURRENT_TIMESTAMP, 0,3),
#     ('Name2', 'LastName2', 'username2', 'pa$$2222', 'client2@example.com', '987654321',CURRENT_TIMESTAMP, 0,3),
#     ('Name3', 'LastName3', 'username3', 'pa$$3333', 'client3@example.com', '111223344', CURRENT_TIMESTAMP,0,3),
#     ('Name4', 'LastName4', 'username4', 'pa$$4444', 'client4@example.com', '555666777',CURRENT_TIMESTAMP, 0,3),
#     ('Name5', 'LastName5', 'username5', 'pa$$5555', 'client5@example.com', '999000111', CURRENT_TIMESTAMP,0,3),
#     ('Name6', 'LastName6', 'username6', 'pa$$6666', 'client6@example.com', '444333222',CURRENT_TIMESTAMP, 0,3);

# CREATE TABLE IF NOT EXISTS discounts (
# discount_id BIGINT PRIMARY KEY AUTO_INCREMENT,
#     client_id BIGINT,
#     discount_rate DECIMAL(5,2) NOT NULL,
#     total_visits INT NOT NULL,
#     FOREIGN KEY (client_id) REFERENCES clients(id)
# );
# DELETE FROM discounts;
# ALTER TABLE discounts AUTO_INCREMENT = 1;
# INSERT INTO discounts (client_id, discount_rate, total_visits)
# VALUES (1, 0.70, 10),
#        (2, 0.70, 10),
#        (3, 0.70, 10),
#        (4, 0.70, 10),
#        (5, 0.70, 10),
#        (6, 0.70, 10);
# -- Создание таблицы clients
# /*
# CREATE TABLE IF NOT EXISTS breeds (
#                       id LONG PRIMARY KEY AUTO_INCREMENT,
#                        name VARCHAR(20) NOT NULL
# );
# */
# DELETE FROM breeds;
# ALTER TABLE breeds AUTO_INCREMENT = 1;
# INSERT INTO breeds (name)
# VALUES ('Labrador'),
#        ('Golden'),
#        ('Poodle'),
#        ('GermanShepherd'),
#        ('Bulldog'),
#        ('Beagle'),
#        ('Rottweiler'),
#        ('Dachshund'),
#        ('ShihTzu'),
#        ('SiberianHusky'),
#        ('Boxer'),
#        ('Pomeranian'),
#        ('Chihuahua'),
#        ('Doberman'),
#        ('GreatDane'),
#        ('CockerSpaniel'),
#        ('Maltese'),
#        ('ShetlandSheepdog'),
#        ('SaintBernard');

# /*
# CREATE TABLE IF NOT EXISTS pet_types (
#                        id AUTOINCREMENT PRIMARY KEY,
#                        name VARCHAR(20) NOT NULL
# );
# */
# DELETE FROM pet_types;
# ALTER TABLE pet_types AUTO_INCREMENT = 1;
# INSERT INTO pet_types (name)
# VALUES ('Dog'),
#        ('Cat');
#
#CREATE TABLE IF NOT EXISTS pets (
# id BIGINT PRIMARY KEY AUTO_INCREMENT,
#     name VARCHAR(20),
#     owner_id BIGINT,
#     petType_id BIGINT,
#     breed_id BIGINT,
#     photoUrls VARCHAR(255),
#     special_notes VARCHAR(255),
#     FOREIGN KEY (owner_id) REFERENCES clients(id),
#     FOREIGN KEY (petType_id) REFERENCES petTypes(id),
#     FOREIGN KEY (breed_id) REFERENCES breeds(id)
# );
# INSERT INTO pets (name, owner_id, pet_Type_id, breed_id, photo_Url, special_notes)
# VALUES ('Joy', 1, 1, 1, 'joy.jpg', 'Likes to play with toys'),
#        ('Joschy', 1, 1, 2, 'joschy.jpg', 'Enjoys long walks'),
#         ('Fluffy', 2, 1, 1, 'fluffy.jpg', 'Likes to play with toys'),
#        ('Buddy', 2, 1, 2, 'buddy.jpg', 'Enjoys long walks'),
#        ('Mittens', 4,1, 3, 'mittens.jpg', 'Loves to nap in the sun');

# /*
# -- Создание таблицы grooming_services
# CREATE TABLE IF NOT EXISTS grooming_services (
#                                    id LONG AUTO_INCREMENT PRIMARY KEY,
#                                    name VARCHAR(50),
#                                    size VARCHAR(50),
#                                    description VARCHAR(255),
#                                    price DOUBLE,
#                                    duration_procedure TIME
# );
# */
# DELETE FROM grooming_services;
# ALTER TABLE grooming_services AUTO_INCREMENT = 1;
# -- Вставка данных о услугах грумминг сервисах
# INSERT INTO grooming_services (name, size, description, price, duration_procedure)
# VALUES ('SmallCare: XS', 'bis 2.5 kg (small breed)',
#         'Hunde bis 2.5kg mit kurzem Fell, wie z.B. Mops. Dogs up to 2.5kg with short coat, such as pugs.', 69.00,
#         '01:30:00'),
#        ('SmallCare: S', '2.5-8 kg (Zwergspitz, Pomeranian)',
#         'Kleine Rassen bis 2.5-8kg, z.B. Zwergspitz, Poms. Small breeds up to 2.5-8kg, e.g. Miniature Spitz, Poms.',
#         69.00, '01:30:00'),
#        ('SmallCare: M', '9-15 kg (medium breed)',
#         'Mittlere Rassen bis 8-15kg, z.B. Doodles.. Medium breeds up to 8-15kg, e.g. doodles....', 79.00, '01:30:00'),
#        ('SmallCare: L', 'ab 16 kg (large breed)',
#         'Große Rassen ab 16kg, z.B. Labrador, golden Retriever.. Large breeds from 16kg, e.g. Labrador, golden Retriever....',
#         119.00, '01:30:00'),
#        ('Completely: XS short', 'Bis 2.5 kg (short hair, Mops etc.)',
#         'Hunde bis 2.5kg mit kurzem Fell, wie z.B. Mops. Dogs up to 2.5kg with short coat, such as pugs.', 89.00,
#         '02:00:00'),
#        ('Completely: XS long', 'Bis 2.5kg (long hair)',
#         'Hunde bis 2.5kg mit längerem. voluminösem Fell wie York, Maltipoo etc. Dogs up to 2.5kg with short coat, such as pugs.',
#         99.00, '02:00:00'),
#        ('Completely: S', '2.5-8kg',
#         'Hunde, die zwischen 2.5-8kg wiegen, z.B. ZWERGSPITZ, POMERANIAN etc. Dogs weighing between 2.5-8kg, e.g. ZWERGSPITZ, POMERANIAN etc.',
#         109.00, '02:00:00'),
#        ('Completely: M', '9-15kg',
#         'Hunde mit 9-15 kg Gewicht (Doodles, Shepard etc.) Dogs weighing 9-15 kg (Doodles, Shepard etc.)', 125.00,
#         '02:00:00'),
#        ('Completely: L', '16-40kg', 'Große Rassen 16-40kg (z.B. Labrador) Large breeds 16-40kg (e...MORE', 189.00,
#         '02:00:00'),
#        ('Completely: XL', 'ab 40kg',
#         'XL Rassen ab 40kg. z.B. Chow Chow, Sennenhund, Neufundländer, American Akita, Königspudel.. XL breeds from 40kg. e.g. Chow Chow, Mountain Dog, Newfoundland, American Akita, Roya',
#         209.00, '02:00:00');
#


# -- Создание таблицы Employee
# CREATE TABLE IF NOT EXISTS employees
# (
#     id                BIGINT PRIMARY KEY AUTO_INCREMENT,
#     name              VARCHAR(20) NOT NULL,
#     lastname          VARCHAR(20) NOT NULL,
#     username          VARCHAR(20),
#     password          VARCHAR(20),
#     email             VARCHAR(20) NOT NULL,
#     phone             VARCHAR(20) NOT NULL,
#     date_registration DATE,
#     address           VARCHAR(255),
#     role_id           BIGINT,
#     FOREIGN KEY (role_id) REFERENCES roles (id)
# );
#
# -- Заполнение таблицы Employee данными
# INSERT INTO employees (name, lastname, username, password, email, phone, date_registration, address, role_id)
# VALUES ('Master1', 'LastName1', 'master1', 'password1', 'master1@example.com', '123456789', '2023-01-01', 'Address1',
#         2),
#        ('Master2', 'LastName2', 'master2', 'password2', 'master2@example.com', '987654321', '2023-01-01', 'Address2',
#         2);

# /*
# CREATE TABLE IF NOT EXISTS schedules (
#                           id LONG PRIMARY KEY AUTO_INCREMENT,
#                           master_id LONG,
#                           day_of_week LONG,
#                           start_time TIME,
#                           end_time TIME
# );
# */
# DELETE FROM schedules;
# ALTER TABLE schedules AUTO_INCREMENT = 1;
# INSERT INTO schedules (master_id, day_of_week, start_time, end_time)
# VALUES (1, 1, '10:00:00', '18:00:00'), -- Понедельник, мастер 1 10:00,12:00,14:00,16:00
#        (1, 2, '10:00:00', '18:00:00'), -- Вторник, мастер 1
#        (1, 3, '10:00:00', '18:00:00'), -- Среда, мастер 1
#        (1, 4, '10:00:00', '18:00:00'), -- Четверг, мастер 1
#        (1, 5, '10:00:00', '18:00:00'), -- Пятница, мастер 1
#
#        (2, 1, '10:00:00', '18:00:00'), -- Понедельник, мастер 2
#        (2, 2, '10:00:00', '18:00:00'), -- Вторник, мастер 2
#        (2, 3, '10:00:00', '18:00:00'), -- Среда, мастер 2
#        (2, 4, '10:00:00', '18:00:00'), -- Четверг, мастер 2
#        (2, 5, '10:00:00', '18:00:00'); -- Пятница, мастер 2