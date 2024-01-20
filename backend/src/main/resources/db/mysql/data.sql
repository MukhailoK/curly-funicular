INSERT INTO users (name, lastname, username, password, email, phone, registration_date, role, address)
VALUES ('Master1', 'LastName1', 'master1', '$2a$10$RwUhI4UxVEVRMxjrWKRa4eIIM.KOUqZNgYuBorTDEIGtu45lvc5lm',
        'master1@example.com', '123456789', '2023-01-01',
        'MASTER',
        'Address1'),
       ('Master2', 'LastName2', 'master2', '$2a$10$RwUhI4UxVEVRMxjrWKRa4eIIM.KOUqZNgYuBorTDEIGtu45lvc5lm',
        'master2@example.com', '987654321', '2023-01-01',
        'MASTER',
        'Address2'),
       ('Name1', 'LastName1', 'username1', '$2a$10$RwUhI4UxVEVRMxjrWKRa4eIIM.KOUqZNgYuBorTDEIGtu45lvc5lm',
        'client1@example.com', '123456789', CURRENT_TIMESTAMP,
        'CLIENT',
        'userAddress1'),
       ('Name2', 'LastName2', 'username2', '$2a$10$RwUhI4UxVEVRMxjrWKRa4eIIM.KOUqZNgYuBorTDEIGtu45lvc5lm',
        'client2@example.com', '987654321', CURRENT_TIMESTAMP,
        'CLIENT',
        'userAddress2'),
       ('Name3', 'LastName3', 'username3', '$2a$10$RwUhI4UxVEVRMxjrWKRa4eIIM.KOUqZNgYuBorTDEIGtu45lvc5lm',
        'client3@example.com', '111223344', CURRENT_TIMESTAMP,
        'CLIENT',
        'userAddress3' ),
       ('Name4', 'LastName4', 'username4', '$2a$10$RwUhI4UxVEVRMxjrWKRa4eIIM.KOUqZNgYuBorTDEIGtu45lvc5lm',
        'client4@example.com', '555666777', CURRENT_TIMESTAMP,
        'CLIENT',
        'userAddress4'),
       ('Name5', 'LastName5', 'username5', '$2a$10$RwUhI4UxVEVRMxjrWKRa4eIIM.KOUqZNgYuBorTDEIGtu45lvc5lm',
        'client5@example.com', '999000111', CURRENT_TIMESTAMP,
        'CLIENT',
        'userAddress5' ),
       ('Name6', 'LastName6', 'username6', '$2a$10$RwUhI4UxVEVRMxjrWKRa4eIIM.KOUqZNgYuBorTDEIGtu45lvc5lm',
        'client6@example.com', '444333222', CURRENT_TIMESTAMP,
        'CLIENT',
        'userAddress6' ),
       ('Admin', 'LastName1', 'admin1', '$2a$10$RwUhI4UxVEVRMxjrWKRa4eIIM.KOUqZNgYuBorTDEIGtu45lvc5lm',
        'admin@example.com', '123456789', '2022-01-01',
        'ADMIN',
        'Address1' );

INSERT INTO breeds (name)
VALUES ('Labrador'),
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

ALTER TABLE appointments
    AUTO_INCREMENT = 1;
INSERT INTO pets (name, owner_id, breed_id, special_notes)
VALUES ('Joy', 1, 1, 'Likes to play with toys'),
       ('Joschy', 1, 2, 'Enjoys long walks'),
       ('Fluffy', 2, 1, 'Likes to play with toys'),
       ('Buddy', 2, 2, 'Enjoys long walks'),
       ('Mittens', 3, 3, 'Loves to nap in the sun'),
       ('Max', 4, 2,  'Enjoys long walks'),
       ('Tuzik', 5,  1, 'Likes to play with toys'),
       ('Bobik', 5, 2, 'Enjoys long walks'),
       ('Scharik', 6,  3, 'Loves to nap in the sun');

INSERT INTO grooming_services (name, size, description, price, duration_procedure)
 VALUES ('SmallCare: XS', 'bis 2.5 kg (small breed)',
         'Hunde bis 2.5kg mit kurzem Fell, wie z.B. Mops. Dogs up to 2.5kg with short coat, such as pugs.', 69.00,
         '01:30:00'),
       ('SmallCare: S', '2.5-8 kg (Zwergspitz, Pomeranian)',
         'Kleine Rassen bis 2.5-8kg, z.B. Zwergspitz, Poms. Small breeds up to 2.5-8kg, e.g. Miniature Spitz, Poms.',
        69.00, '01:30:00'),
        ('SmallCare: M', '9-15 kg (medium breed)',
         'Mittlere Rassen bis 8-15kg, z.B. Doodles.. Medium breeds up to 8-15kg, e.g. doodles....', 79.00, '01:30:00'),
        ('SmallCare: L', 'ab 16 kg (large breed)',
         'Große Rassen ab 16kg, z.B. Labrador, golden Retriever.. Large breeds from 16kg, e.g. Labrador, golden Retriever....',
        119.00, '01:30:00'),
        ('Completely: XS short', 'Bis 2.5 kg (short hair, Mops etc.)',
         'Hunde bis 2.5kg mit kurzem Fell, wie z.B. Mops. Dogs up to 2.5kg with short coat, such as pugs.', 89.00,
         '02:00:00'),
        ('Completely: XS long', 'Bis 2.5kg (long hair)',
         'Hunde bis 2.5kg mit längerem. voluminösem Fell wie York, Maltipoo etc. Dogs up to 2.5kg with short coat, such as pugs.',
        99.00, '02:00:00'),
        ('Completely: S', '2.5-8kg',
         'Hunde, die zwischen 2.5-8kg wiegen, z.B. ZWERGSPITZ, POMERANIAN etc. Dogs weighing between 2.5-8kg, e.g. ZWERGSPITZ, POMERANIAN etc.',
         109.00, '02:00:00'),
        ('Completely: M', '9-15kg',
         'Hunde mit 9-15 kg Gewicht (Doodles, Shepard etc.) Dogs weighing 9-15 kg (Doodles, Shepard etc.)', 125.00,
        '02:00:00'),
        ('Completely: L', '16-40kg', 'Große Rassen 16-40kg (z.B. Labrador) Large breeds 16-40kg (e...MORE', 189.00,
         '02:00:00'),
        ('Completely: XL', 'ab 40kg',
         'XL Rassen ab 40kg. z.B. Chow Chow, Sennenhund, Neufundländer, American Akita, Königspudel.. XL breeds from 40kg. e.g. Chow Chow, Mountain Dog, Newfoundland, American Akita, Roya',
         209.00, '02:00:00');

 INSERT INTO appointments (client_id, service_id, pet_id, date_time_start, date_time_end, status)
 VALUES (1,  1, 1, '2024-02-02T10:00:00', '2024-02-02T12:00:00', 'scheduled'), -- клиент 1, мастер 1, собака 1
        (1,  2, 2, '2024-02-02T12:00:00', '2024-02-02T14:00:00', 'scheduled'), -- клиент 1, мастер 2, собака 2
        (2,  1, 3, '2024-02-02T14:00:00', '2024-02-02T16:00:00', 'scheduled'), -- клиент 2, мастер 1, собака 3
        (2,  2, 4, '2024-02-02T16:00:00', '2024-02-02T18:00:00', 'scheduled'), -- клиент 2, мастер 2, собака 4
        (3, 3, 5, '2024-02-05T10:00:00', '2024-02-02T12:00:00', 'scheduled'),-- клиет 3, мастер 1, собака 5
        (1,  1, 1, '2024-02-05T12:00:00', '2024-02-02T14:00:00', 'scheduled'), -- клиент 1, мастер 2, собака 2
        (1,  2, 2, '2024-02-06T14:00:00', '2024-02-02T16:00:00', 'scheduled'), -- клиент 2, мастер 1, собака 3
        (2,  2, 2, '2024-02-06T16:00:00', '2024-02-02T18:00:00', 'scheduled'), -- клиент 2, мастер 2, собака 4
        (2,  2, 5, '2024-02-07T10:00:00', '2024-02-02T12:00:00', 'scheduled');-- клиет 3, мастер 1, собака 5