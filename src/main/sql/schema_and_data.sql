create table course
(
    id serial primary key,
    name varchar(20),
    beginning_date varchar(20),
    ending_date varchar(20),
    teacher_id integer,
    foreign key (teacher_id) references teacher (id)
);

create table course_student
(
    course_id integer,
    student_id integer,
    foreign key (course_id) references course (id),
    foreign key (student_id) references student (id)
);

create table lesson
(
    id serial primary key,
    name varchar(20),
    date varchar(30),
    course_id integer,
    foreign key (course_id) references course (id)
);

create table teacher
(
    id serial primary key,
    first_name varchar(20),
    last_name varchar(20),
    experience integer
);

create table teacher_course
(
    teacher_id integer,
    course_id integer,
    foreign key (teacher_id) references teacher (id),
    foreign key (course_id) references course (id)
);

create table student
(
    id serial primary key,
    first_name varchar(20),
    last_name varchar(20),
    study_group integer
);

insert into student(first_name, last_name, study_group)
values ('Юлий', 'Киприянов', 11001);
insert into student(first_name, last_name, study_group)
values ('Анастасия', 'Грабарь', 11002);
insert into student(first_name, last_name, study_group)
values ('Денис', 'Цирульников', 11002);
insert into student(first_name, last_name, study_group)
values ('Адам', 'Заболотный', 11003);
insert into student(first_name, last_name, study_group)
values ('Алина', 'Нестерова', 11008);
insert into student(first_name, last_name, study_group)
values ('Николай', 'Шапиро', 11001);
insert into student(first_name, last_name, study_group)
values ('Константин', 'Кокорев', 11004);

insert into teacher (first_name, last_name, experience)
values ('Юлия', 'Косорукова', 6);
insert into teacher (first_name, last_name, experience)
values ('Алиса', 'Ковалева', 10);
insert into teacher (first_name, last_name, experience)
values ('Кондратий', 'Лебединцев', 3);
insert into teacher (first_name, last_name, experience)
values ('Юрий', 'Шаломенцев', 1);
insert into teacher (first_name, last_name, experience)
values ('Яна', 'Поветникова', 4);

insert into course (name, beginning_date, ending_date, teacher_id)
values ('SQL', '20.07.2020', '04.09.2020', 1);
insert into course (name, beginning_date, ending_date, teacher_id)
values ('Русский язык', '10.06.2021', '11.09.2021', 4);
insert into course (name, beginning_date, ending_date, teacher_id)
values ('Математика', '19.01.2020', '21.04.2020', 3);
insert into course (name, beginning_date, ending_date, teacher_id)
values ('Java', '20.07.2020', '12.08.2020', 1);
insert into course (name, beginning_date, ending_date, teacher_id)
values ('Phyton', '05.02.2020', '02.12.2020', 2);
insert into course (name, beginning_date, ending_date, teacher_id)
values ('Обществоведение', '12.12.202', '08.02.2021', 5);
insert into course (name, beginning_date, ending_date, teacher_id)
values ('История', '19.11.2020', '04.01.2021', 5);

insert into lesson (name, date, course_id)
values ('SQL-запросы', '21.07.2020 14:00', 1);
insert into lesson (name, date, course_id)
values ('Грамматика', '11.06.2021 15:00', 2);
insert into lesson (name, date, course_id)
values ('Дроби', '22.01.2020 16:00', 3);
insert into lesson (name, date, course_id)
values ('Collections', '21.07.2020 14:00', 4);
insert into lesson (name, date, course_id)
values ('Синтаксис', '06.02.2020 11:00', 5);
insert into lesson (name, date, course_id)
values ('Право', '13.12.202 12:00', 6);
insert into lesson (name, date, course_id)
values ('Древняя Греция', '19.11.2020 17:00', 7);

insert into course_student (course_id, student_id)
values (1, 2);
insert into course_student (course_id, student_id)
values (1, 3);
insert into course_student (course_id, student_id)
values (1, 7);
insert into course_student (course_id, student_id)
values (2, 2);
insert into course_student (course_id, student_id)
values (3, 5);
insert into course_student (course_id, student_id)
values (3, 6);
insert into course_student (course_id, student_id)
values (4, 1);
insert into course_student (course_id, student_id)
values (1, 3);
insert into course_student (course_id, student_id)
values (5, 4);
insert into course_student (course_id, student_id)
values (6, 2);
insert into course_student (course_id, student_id)
values (7, 1);
insert into course_student (course_id, student_id)
values (7, 4);
insert into course_student (course_id, student_id)
values (7, 7);

insert into teacher_course (teacher_id, course_id)
values (1, 1);
insert into teacher_course (teacher_id, course_id)
values (1, 4);
insert into teacher_course (teacher_id, course_id)
values (2, 2);
insert into teacher_course (teacher_id, course_id)
values (3, 3);
insert into teacher_course (teacher_id, course_id)
values (4, 5);
insert into teacher_course (teacher_id, course_id)
values (5, 6);
insert into teacher_course (teacher_id, course_id)
values (5, 7);