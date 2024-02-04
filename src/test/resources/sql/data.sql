INSERT INTO users (name, lastname, username, password, email, phone, registration_date, role, address)
VALUES ('Name1', 'LastName1', 'username1', '$2a$10$RwUhI4UxVEVRMxjrWKRa4eIIM.KOUqZNgYuBorTDEIGtu45lvc5lm',
        'client1@example.com', '123456789', '2023-04-12',
        'CLIENT',
        'userAddress1'),
       ('Name2', 'LastName2', 'username2', '$2a$10$RwUhI4UxVEVRMxjrWKRa4eIIM.KOUqZNgYuBorTDEIGtu45lvc5lm',
        'client2@example.com', '987654321', '2023-04-10',
        'ADMIN',
        'userAddress2');

INSERT INTO breeds (name)
VALUES ('Golden');


INSERT INTO pets (name, owner_id, breed_id, special_notes)
VALUES ('Joy', 1, 1, 'Likes to play with toys'),
       ('Joschy', 2, 1, 'Enjoys long walks');

INSERT INTO grooming_services (name, size, description, price, duration_procedure)
VALUES ('SmallCare: XS', 'up to 2.5 kg (small breed)',
        'Dogs up to 2.5 kg with short coat, such as pugs.', 69.00, '01:30:00'),
       ('SmallCare: S', '2.5-8 kg (Miniature Spitz, Pomeranian)',
        'Small breeds from 2.5-8 kg, e.g. Miniature Spitz, Poms.', 69.00, '01:30:00');

INSERT INTO appointments (client_id, service_id, pet_id, date_time_start, date_time_end, status)
VALUES (1, 1, 1, TIMESTAMP '2024-02-02 10:00:00', TIMESTAMP '2024-02-02 12:00:00',
        'scheduled'),
       (1, 2, 2, TIMESTAMP '2024-02-02 12:00:00', TIMESTAMP '2024-02-02 14:00:00',
        'scheduled');
