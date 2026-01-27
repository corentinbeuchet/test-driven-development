INSERT INTO student (id, name, surname, birthdate)
VALUES (1, 'Test', 'Student', '2001-01-26'),
       (2, 'Test2', 'Student2', '2003-06-12');
SELECT setval(pg_get_serial_sequence('student', 'id'), 2);