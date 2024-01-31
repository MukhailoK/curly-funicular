# -- # DELETE
# -- # FROM clients;
# -- # ALTER TABLE clients
# -- #     AUTO_INCREMENT = 1;
# -- INSERT INTO discounts (discount_rate, total_visits, name)
# -- VALUES ( 0.70, 3, '5%'),
# --        ( 0.70, 5, '10%'),
# --        ( 0.70, 10, '15%'),
# --        ( 0.70, 15, '20%'),
# --        ( 0.70, 20, '22%'),
# --        ( 0.70, 50, '30%');
# --
# --
# -- /*
# --
# -- */
# -- # DELETE
# -- # FROM discounts;
# -- # ALTER TABLE discounts
# -- #     AUTO_INCREMENT = 1;
# INSERT INTO users (name, lastname, username, password, email, phone, registration_date, is_blocked, role, address)
# VALUES ('Master1', 'LastName1', 'master1', '$2a$10$RwUhI4UxVEVRMxjrWKRa4eIIM.KOUqZNgYuBorTDEIGtu45lvc5lm',
#         'master1@example.com', '123456789', '2023-01-01', false,
#         'MASTER',
#         'Address1'),
#        ('Master2', 'LastName2', 'master2', '$2a$10$RwUhI4UxVEVRMxjrWKRa4eIIM.KOUqZNgYuBorTDEIGtu45lvc5lm',
#         'master2@example.com', '987654321', '2023-01-01', false,
#         'MASTER',
#         'Address2'),
#        ('Name1', 'LastName1', 'username1', '$2a$10$RwUhI4UxVEVRMxjrWKRa4eIIM.KOUqZNgYuBorTDEIGtu45lvc5lm',
#         'client1@example.com', '123456789', CURRENT_TIMESTAMP, false,
#         'CLIENT',
#         'userAddress1'),
#        ('Name2', 'LastName2', 'username2', '$2a$10$RwUhI4UxVEVRMxjrWKRa4eIIM.KOUqZNgYuBorTDEIGtu45lvc5lm',
#         'client2@example.com', '987654321', CURRENT_TIMESTAMP, false,
#         'CLIENT',
#         'userAddress2'),
#        ('Name3', 'LastName3', 'username3', '$2a$10$RwUhI4UxVEVRMxjrWKRa4eIIM.KOUqZNgYuBorTDEIGtu45lvc5lm',
#         'client3@example.com', '111223344', CURRENT_TIMESTAMP, false,
#         'CLIENT',
#         'userAddress3' ),
#        ('Name4', 'LastName4', 'username4', '$2a$10$RwUhI4UxVEVRMxjrWKRa4eIIM.KOUqZNgYuBorTDEIGtu45lvc5lm',
#         'client4@example.com', '555666777', CURRENT_TIMESTAMP, false,
#         'CLIENT',
#         'userAddress4'),
#        ('Name5', 'LastName5', 'username5', '$2a$10$RwUhI4UxVEVRMxjrWKRa4eIIM.KOUqZNgYuBorTDEIGtu45lvc5lm',
#         'client5@example.com', '999000111', CURRENT_TIMESTAMP, true,
#         'CLIENT',
#         'userAddress5' ),
#        ('Name6', 'LastName6', 'username6', '$2a$10$RwUhI4UxVEVRMxjrWKRa4eIIM.KOUqZNgYuBorTDEIGtu45lvc5lm',
#         'client6@example.com', '444333222', CURRENT_TIMESTAMP, false,
#         'CLIENT',
#         'userAddress6' ),
#        ('Admin', 'LastName1', 'admin1', '$2a$10$RwUhI4UxVEVRMxjrWKRa4eIIM.KOUqZNgYuBorTDEIGtu45lvc5lm',
#         'admin@example.com', '123456789', '2022-01-01', false,
#         'ADMIN',
#         'Address1' );
#
# -- /*
# --
# -- */
# -- # DELETE
# -- # FROM breeds;
# -- # ALTER TABLE breeds
# -- #     AUTO_INCREMENT = 1;
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
# --
# -- # ALTER TABLE pets
# -- #     AUTO_INCREMENT = 1;
# INSERT INTO pets (name, owner_id, pet_type, breed_id, photo_Url, special_notes)
# VALUES ('Joy', 3, 'DOG', 1, 'joy.jpg', 'Likes to play with toys'),
#        ('Joschy', 3, 'DOG', 2, 'joschy.jpg', 'Enjoys long walks'),
#        ('Fluffy', 4, 'DOG', 1, 'fluffy.jpg', 'Likes to play with toys'),
#        ('Buddy', 5, 'DOG', 2, 'buddy.jpg', 'Enjoys long walks'),
#       ('Mittens', 6, 'DOG', 3, 'mittens.jpg', 'Loves to nap in the sun');
# --
# --
# -- # FROM grooming_services;
# #  ALTER TABLE grooming_services
# #      AUTO_INCREMENT = 1;
# -- -- Вставка данных о услугах грумминг сервисах
# INSERT INTO grooming_services (name, size, description, price, duration_procedure, active)
#  VALUES ('SmallCare: XS', 'bis 2.5 kg (small breed)',
#          'Hunde bis 2.5kg mit kurzem Fell, wie z.B. Mops. Dogs up to 2.5kg with short coat, such as pugs.', 69.00,
#          '01:30:00', true),
#        ('SmallCare: S', '2.5-8 kg (Zwergspitz, Pomeranian)',
#          'Kleine Rassen bis 2.5-8kg, z.B. Zwergspitz, Poms. Small breeds up to 2.5-8kg, e.g. Miniature Spitz, Poms.',
#         69.00, '01:30:00', true),
#         ('SmallCare: M', '9-15 kg (medium breed)',
#          'Mittlere Rassen bis 8-15kg, z.B. Doodles.. Medium breeds up to 8-15kg, e.g. doodles....', 79.00, '01:30:00',
#          true),
#         ('SmallCare: L', 'ab 16 kg (large breed)',
#          'Große Rassen ab 16kg, z.B. Labrador, golden Retriever.. Large breeds from 16kg, e.g. Labrador, golden Retriever....',
#         119.00, '01:30:00', true),
#         ('Completely: XS short', 'Bis 2.5 kg (short hair, Mops etc.)',
#          'Hunde bis 2.5kg mit kurzem Fell, wie z.B. Mops. Dogs up to 2.5kg with short coat, such as pugs.', 89.00,
#          '02:00:00', true),
#         ('Completely: XS long', 'Bis 2.5kg (long hair)',
#          'Hunde bis 2.5kg mit längerem. voluminösem Fell wie York, Maltipoo etc. Dogs up to 2.5kg with short coat, such as pugs.',
#         99.00, '02:00:00', true),
#         ('Completely: S', '2.5-8kg',
#          'Hunde, die zwischen 2.5-8kg wiegen, z.B. ZWERGSPITZ, POMERANIAN etc. Dogs weighing between 2.5-8kg, e.g. ZWERGSPITZ, POMERANIAN etc.',
#          109.00, '02:00:00', true),
#         ('Completely: M', '9-15kg',
#          'Hunde mit 9-15 kg Gewicht (Doodles, Shepard etc.) Dogs weighing 9-15 kg (Doodles, Shepard etc.)', 125.00,
#         '02:00:00', true),
#         ('Completely: L', '16-40kg', 'Große Rassen 16-40kg (z.B. Labrador) Large breeds 16-40kg (e...MORE', 189.00,
#          '02:00:00', true),
#         ('Completely: XL', 'ab 40kg',
#          'XL Rassen ab 40kg. z.B. Chow Chow, Sennenhund, Neufundländer, American Akita, Königspudel.. XL breeds from 40kg. e.g. Chow Chow, Mountain Dog, Newfoundland, American Akita, Roya',
#          209.00, '02:00:00', true);
# --
# --
# -- # DELETE
# -- # FROM appointments;
# #  ALTER TABLE appointments
# #   AUTO_INCREMENT = 1;
#  INSERT INTO appointments (client_id, service_id, pet_id, date_time_start, date_time_end, status)
#  VALUES (3, 1, 1, '2023-12-10T10:00:00', '2023-12-10T12:00:00', 'scheduled'), -- клиент 1, мастер 1, собака 1
#         (4, 2, 2, '2023-12-10T12:00:00', '2023-12-10T14:00:00', 'scheduled'), -- клиент 1, мастер 2, собака 2
#         (5, 1, 3, '2023-12-11T10:00:00', '2023-12-11T12:00:00', 'scheduled'), -- клиент 2, мастер 1, собака 3
#         (6, 2, 4, '2023-12-11T12:00:00', '2023-12-11T14:00:00', 'scheduled'), -- клиент 2, мастер 2, собака 4
#         (7, 3, 5, '2023-12-11T16:00:00', '2023-12-11T18:00:00', 'scheduled');
# --  клиет 3, мастер 1, собака 5
# --
# -- # DELETE
# -- # FROM ratings;
# #  ALTER TABLE review
# #    AUTO_INCREMENT = 1;
#  INSERT INTO review  (rating, review)
#  VALUES (4.5, 'Great service and friendly staff!'),
#         (5.0, 'Excellent grooming, very satisfied!'),
#         (4.0, 'Good service and friendly staff!'),
#         (5.0, 'Excellent grooming, very satisfied!'),
#         (3.5, 'Great service and not friendly staff!');
