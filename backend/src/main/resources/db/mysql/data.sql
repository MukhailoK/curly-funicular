INSERT INTO role (name) VALUES
                             ('Admin'),
                             ('Master'),
                             ('Client'),
                             ('Guest');

INSERT INTO breed (name) VALUES
                             ('Labrador'),
                             ('Golden'),
                             ('Poodle'),
                             ('GermanShepherd'),
                             ('Bulldog'),
                             ('Beagle'),
                             ('Rottweiler'),
                             ('Dachshund'),
                             ('ShihTzu'),
                             ('SiberianHusky'),
                             ('Boxer'),
                             ('Pomeranian'),
                             ('Chihuahua'),
                             ('Doberman'),
                             ('GreatDane'),
                             ('CockerSpaniel'),
                             ('Maltese'),
                             ('ShetlandSheepdog'),
                             ('SaintBernard');
INSERT INTO pet_type (name) VALUES
                             ('Dog'),
                             ('Cat');
/*
-- Создание таблицы grooming_services
CREATE TABLE grooming_services (
                                   service_id INT AUTO_INCREMENT PRIMARY KEY,
                                   name VARCHAR(50),
                                   size VARCHAR(50),
                                   description VARCHAR(255),
                                   price DOUBLE,
                                   duration_procedure TIME
);
*/
-- Вставка данных
INSERT INTO grooming_services (name, size, description, price, duration_procedure) VALUES
                                                                                       ('SmallCare: XS', 'bis 2.5 kg (small breed)', 'Hunde bis 2.5kg mit kurzem Fell, wie z.B. Mops. Dogs up to 2.5kg with short coat, such as pugs.', 69.00, '01:30:00'),
                                                                                       ('SmallCare: S', '2.5-8 kg (Zwergspitz, Pomeranian)', 'Kleine Rassen bis 2.5-8kg, z.B. Zwergspitz, Poms. Small breeds up to 2.5-8kg, e.g. Miniature Spitz, Poms.', 69.00, '01:30:00'),
                                                                                       ('SmallCare: M', '9-15 kg (medium breed)', 'Mittlere Rassen bis 8-15kg, z.B. Doodles.. Medium breeds up to 8-15kg, e.g. doodles....', 79.00, '01:30:00'),
                                                                                       ('SmallCare: L', 'ab 16 kg (large breed)', 'Große Rassen ab 16kg, z.B. Labrador, golden Retriever.. Large breeds from 16kg, e.g. Labrador, golden Retriever....', 119.00, '01:30:00'),
                                                                                       ('Completely: XS short', 'Bis 2.5 kg (short hair, Mops etc.)', 'Hunde bis 2.5kg mit kurzem Fell, wie z.B. Mops. Dogs up to 2.5kg with short coat, such as pugs.', 89.00, '02:00:00'),
                                                                                       ('Completely: XS long', 'Bis 2.5kg (long hair)', 'Hunde bis 2.5kg mit längerem. voluminösem Fell wie York, Maltipoo etc. Dogs up to 2.5kg with short coat, such as pugs.', 99.00, '02:00:00'),
                                                                                       ('Completely: S', '2.5-8kg', 'Hunde, die zwischen 2.5-8kg wiegen, z.B. ZWERGSPITZ, POMERANIAN etc. Dogs weighing between 2.5-8kg, e.g. ZWERGSPITZ, POMERANIAN etc.', 109.00, '02:00:00'),
                                                                                       ('Completely: M', '9-15kg', 'Hunde mit 9-15 kg Gewicht (Doodles, Shepard etc.) Dogs weighing 9-15 kg (Doodles, Shepard etc.)', 125.00, '02:00:00'),
                                                                                       ('Completely: L', '16-40kg', 'Große Rassen 16-40kg (z.B. Labrador) Large breeds 16-40kg (e...MORE', 189.00, '02:00:00'),
                                                                                       ('Completely: XL', 'ab 40kg', 'XL Rassen ab 40kg. z.B. Chow Chow, Sennenhund, Neufundländer, American Akita, Königspudel.. XL breeds from 40kg. e.g. Chow Chow, Mountain Dog, Newfoundland, American Akita, Roya', 209.00, '02:00:00');