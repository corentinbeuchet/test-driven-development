INSERT INTO student (id, name, surname)
VALUES (1, 'Test', 'Student'),
       (2, 'Test2', 'Student2');
SELECT setval(pg_get_serial_sequence('student', 'id'), 2);