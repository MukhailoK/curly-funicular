INSERT INTO users (name, lastname, username, password, email, phone, registration_date, role, address)
VALUES ('ADMIN', 'AIT', 'Mykhailo', '$2a$10$RwUhI4UxVEVRMxjrWKRa4eIIM.KOUqZNgYuBorTDEIGtu45lvc5lm',
        'admin@mail.com', '123456789', '2023-01-01',
        'ADMIN',
        'Address1'),
       ('Master2', 'LastName2', 'master2', '$2a$10$RwUhI4UxVEVRMxjrWKRa4eIIM.KOUqZNgYuBorTDEIGtu45lvc5lm',
        'master2@example.com', '987654321', '2023-01-01',
        'MASTER',
        'Address2'),
       ('Name1', 'LastName1', 'username1', '$2a$10$RwUhI4UxVEVRMxjrWKRa4eIIM.KOUqZNgYuBorTDEIGtu45lvc5lm',
        'client1@example.com', '123456789', '2023-04-12',
        'CLIENT',
        'userAddress1'),
       ('Name2', 'LastName2', 'username2', '$2a$10$RwUhI4UxVEVRMxjrWKRa4eIIM.KOUqZNgYuBorTDEIGtu45lvc5lm',
        'client2@example.com', '987654321', CURRENT_TIMESTAMP,
        'CLIENT',
        'userAddress2'),
       ('Name3', 'LastName3', 'username3', '$2a$10$vytP7ZHpIRRJx6HknSeTv.J/AIIZD3Hqk6.DtG2fOz6rk9vgNGJX6',
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
VALUES
    ('SmallCare: XS', 'up to 2.5 kg (small breed)',
     'Dogs up to 2.5 kg with short coat, such as pugs.', 69.00, '01:30:00'),
    ('SmallCare: S', '2.5-8 kg (Miniature Spitz, Pomeranian)',
     'Small breeds from 2.5-8 kg, e.g. Miniature Spitz, Poms.', 69.00, '01:30:00'),
    ('SmallCare: M', '9-15 kg (medium breed)',
     'Medium breeds from 9-15 kg, e.g. Doodles.', 79.00, '01:30:00'),
    ('SmallCare: L', '16 kg and above (large breed)',
     'Large breeds from 16 kg, e.g. Labrador, Golden Retriever.', 119.00, '01:30:00'),
    ('Completely: XS short', 'Up to 2.5 kg (short hair, Mops etc.)',
     'Dogs up to 2.5 kg with short coat, such as pugs.', 89.00, '02:00:00'),
    ('Completely: XS long', 'Up to 2.5 kg (long hair)',
     'Dogs up to 2.5 kg with long and voluminous coat like York, Maltipoo etc.', 99.00, '02:00:00'),
    ('Completely: S', '2.5-8 kg',
     'Dogs weighing between 2.5-8 kg, e.g. Miniature Spitz, Pomeranian etc.', 109.00, '02:00:00'),
    ('Completely: M', '9-15 kg',
     'Dogs weighing 9-15 kg (Doodles, Shepherd etc.)', 125.00, '02:00:00'),
    ('Completely: L', '16-40 kg',
     'Large breeds weighing 16-40 kg (e.g. Labrador)', 189.00, '02:00:00'),
    ('Completely: XL', '40 kg and above',
     'XL breeds from 40 kg, e.g. Chow Chow, Mountain Dog, Newfoundland, American Akita, Royal Poodle.', 209.00, '02:00:00');

 INSERT INTO appointments (client_id, service_id, pet_id, date_time_start, date_time_end, status)
 VALUES (1,  1, 1, '2024-02-09T10:00:00', '2024-02-09T12:00:00', 'scheduled'), -- клиент 1, мастер 1, собака 1
        (1,  2, 2, '2024-02-09T12:00:00', '2024-02-09T14:00:00', 'scheduled'), -- клиент 1, мастер 2, собака 2
        (2,  1, 3, '2024-02-09T14:00:00', '2024-02-09T16:00:00', 'scheduled'), -- клиент 2, мастер 1, собака 3
        (2,  2, 4, '2024-02-09T16:00:00', '2024-02-09T18:00:00', 'scheduled'), -- клиент 2, мастер 2, собака 4
        (3, 3, 5, '2024-02-12T10:00:00', '2024-02-12T12:00:00', 'scheduled'),-- клиет 3, мастер 1, собака 5
        (1,  1, 1, '2024-02-12T12:00:00', '2024-02-12T14:00:00', 'scheduled'), -- клиент 1, мастер 2, собака 2
        (1,  2, 2, '2024-02-13T14:00:00', '2024-02-13T16:00:00', 'scheduled'), -- клиент 2, мастер 1, собака 3
        (2,  2, 3, '2024-02-13T16:00:00', '2024-02-13T18:00:00', 'scheduled'), -- клиент 2, мастер 2, собака 4
        (6,  2, 9, '2024-02-14T10:00:00', '2024-02-14T12:00:00', 'scheduled');-- клиет 3, мастер 1, собака 5