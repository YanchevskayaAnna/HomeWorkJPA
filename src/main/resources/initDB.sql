CREATE DATABASE ACP14JPA;
USE ACP14JPA;

CREATE TABLE groups(
  id int NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  name VARCHAR (20)
);

CREATE TABLE students(
  id int NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  name VARCHAR (20),
  group_id int, FOREIGN KEY (group_id) REFERENCES groups(id)
);

CREATE TABLE subjects(
  id int NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  name VARCHAR (20),
  description VARCHAR(50),
  teacher_id int, FOREIGN KEY (teacher_id) REFERENCES teachers(id)
);

CREATE TABLE teachers(
  id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(20),
  exp DOUBLE,
  subject_id int, FOREIGN KEY (subject_id) REFERENCES subjects(subject_id)
);

CREATE TABLE learning(
  group_id int,
  subject_id int,
  FOREIGN KEY (group_id) REFERENCES groups(id),
  FOREIGN KEY (subject_id) REFERENCES subjects(id)
);

--CREATE TABLE marks(
--  student_id int,
--  subject_id int,
--  mark int,
--  FOREIGN KEY (student_id) REFERENCES students(id),
--  FOREIGN KEY (subject_id) REFERENCES subjects(id)
--);


INSERT INTO groups(name) VALUES ('ACO13');
INSERT INTO groups(name) VALUES ('ACO14');
INSERT INTO groups(name) VALUES ('ACP14');
SELECT * FROM groups;

INSERT INTO students(name, group_id) VALUES ('Anna', 1);
INSERT INTO students(name, group_id) VALUES ('Max', 1);
INSERT INTO students(name, group_id) VALUES ('Dan', 2);
INSERT INTO students(name, group_id) VALUES ('Serg', 2);
INSERT INTO students(student_name, group_id) VALUES ('Bob', 3);
INSERT INTO students(student_name, group_id) VALUES ('Chendler', 3);

INSERT INTO subjects(subject_title, subject_desc) VALUES ('java', 'jaaaaaava');
INSERT INTO subjects(subject_title, subject_desc) VALUES ('1c',   '1c');
SELECT * FROM subjects;

INSERT INTO teachers(teacher_name, teacher_exp, subject_id) VALUES ('Serhii', 10, 1);
INSERT INTO teachers(teacher_name, teacher_exp, subject_id) VALUES ('Anna', 10, 2);
SELECT * FROM teachers;

INSERT INTO learning(group_id, subject_id) VALUES (1, 1);
INSERT INTO learning(group_id, subject_id) VALUES (2, 1);
INSERT INTO learning(group_id, subject_id) VALUES (3, 1);
SELECT * FROM learning;


--INSERT INTO marks(student_id, subject_id, mark) VALUES (1, 1, 5);
--INSERT INTO marks(student_id, subject_id, mark) VALUES (1, 2, 8);
--
--INSERT INTO marks(student_id, subject_id, mark) VALUES (2, 1, 5);
--INSERT INTO marks(student_id, subject_id, mark) VALUES (2, 2, 10);
--
--INSERT INTO marks(student_id, subject_id, mark) VALUES (3, 1, 8);
--INSERT INTO marks(student_id, subject_id, mark) VALUES (3, 2, 8);
--
--INSERT INTO marks(student_id, subject_id, mark) VALUES (4, 1, 12);
--INSERT INTO marks(student_id, subject_id, mark) VALUES (4, 2, 8);

--SELECT * FROM marks;

--SELECT * FROM students;

INSERT INTO students(firstName, birth, salary, address) VALUES ('Ivan', NOW(), 3000.00, 'Kyiv');

INSERT INTO students(firstName, birth, salary, address_id) VALUES ('Ivan', NOW(), 3000.00, 1);

SELECT * FROM students;

SELECT name, birth FROM students;

INSERT INTO addresses(id, city) VALUES (1, 'Kyiv');


