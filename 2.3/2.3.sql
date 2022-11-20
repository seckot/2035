CREATE TABLE IF NOT EXISTS employees
(
    emp_id INT NOT NULL,
    fio VARCHAR(255) NOT NULL,
    birthday date,
    startdate date,
    job_title VARCHAR(255),
    levels VARCHAR(10) NOT NULL,
    salary INT not null,
    dep_id INT,
    driver BOOL DEFAULT false,
    PRIMARY KEY (emp_id));

CREATE TABLE IF NOT EXISTS departments
(
    dep_id INT NOT NULL,
    tittle_dep varchar(255) not NULL,
	boss_name varchar(255) NOT NULL,
	num_employees INT,
	CONSTRAINT dep_id PRIMARY KEY (dep_id));

CREATE TABLE bonuses
(
    bon_id INT NOT NULL,
    quartal integer NOT NULL,
	raiting varchar(70),
	grade integer NOT NULL,
	PRIMARY KEY (bon_id));

INSERT INTO employees (emp_id, fio, birthday, startdate, job_title, levels, salary, dep_id, driver) 
VALUES (107, 'Subotin A.A.', '1990.01.21', '2020.11.06', 'sec', 'senior', 45000.00, 2, true);
INSERT INTO employees (emp_id, fio, birthday, startdate, job_title, levels, salary, dep_id, driver) 
VALUES (102, 'Ivanov A.P.', '1950.01.22', '2020.11.06', 'sec', 'senior', 15000.00, 2, false);
INSERT INTO employees (emp_id, fio, birthday, startdate, job_title, levels, salary, dep_id, driver) 
VALUES (103, 'Petrov A.S.', '1960.01.10', '2020.11.06', 'sec', 'junior', 25000.00, 2, true);
INSERT INTO employees (emp_id, fio, birthday, startdate, job_title, levels, salary, dep_id, driver) 
VALUES (104, 'Alexandrov S.A.', '1996.11.20', '2020.11.06', 'sec', 'midle', 35000.00, 2, false);
INSERT INTO employees (emp_id, fio, birthday, startdate, job_title, levels, salary, dep_id, driver) 
VALUES (105, 'Shahov T.A.', '1995.03.11', '2020.11.06', 'sec', 'senior', 45000.00, 2, true);
INSERT INTO employees (emp_id, fio, birthday, startdate, job_title, levels, salary, dep_id, driver) 
VALUES (106, 'Krotov A.A.', '1994.04.20', '2020.11.06', 'sec', 'junior', 45000.00, 2, false);
INSERT INTO employees (emp_id, fio, birthday, startdate, job_title, levels, salary, dep_id, driver) 
VALUES (109, 'Shahova T.A.', '1995.03.11', '2020.11.06', 'sec', 'senior', 45000.00, 2, true);
INSERT INTO employees (emp_id, fio, birthday, startdate, job_title, levels, salary, dep_id, driver) 
VALUES (108, 'Krotova A.A.', '1994.04.20', '2020.11.06', 'sec', 'junior', 45000.00, 2, false);


INSERT INTO departments (dep_id, tittle_dep, boss_name, num_employees) 
VALUES (1,'Managment', 'Krotova A.A.',2);
INSERT INTO departments (dep_id, tittle_dep, boss_name, num_employees) 
VALUES (2,'CEO','Shahova T.A',2);

INSERT INTO bonuses (bon_id, quartal, raiting, grade) 
VALUES (1, 1, 'A', 1);
INSERT INTO bonuses (bon_id, quartal, raiting, grade) 
VALUES (2, 1, 'B', 2);
INSERT INTO bonuses (bon_id, quartal, raiting, grade) 
VALUES (3, 1, 'C', 3);
INSERT INTO bonuses (bon_id, quartal, raiting, grade)   
VALUES (4, 1, 'A', 4);
INSERT INTO bonuses (bon_id, quartal, raiting, grade) 
VALUES (5, 1, 'A', 1);
INSERT INTO bonuses (bon_id, quartal, raiting, grade) 
VALUES (6, 1, 'B', 2);
INSERT INTO bonuses (bon_id, quartal, raiting, grade) 
VALUES (7, 1, 'A', 3);
INSERT INTO bonuses (bon_id, quartal, raiting, grade)  
VALUES (8, 1, 'C', 4);
INSERT INTO bonuses (bon_id, quartal, raiting, grade) 
VALUES (9, 1, 'A', 1);
INSERT INTO bonuses (bon_id, quartal, raiting, grade)  
VALUES (10, 1, 'C', 2);
INSERT INTO bonuses (bon_id, quartal, raiting, grade) 
VALUES (11, 1, 'A', 3);
INSERT INTO bonuses (bon_id, quartal, raiting, grade) 
VALUES (12, 1, 'B', 4);

INSERT INTO employees (emp_id, fio, birthday, startdate, job_title, levels, salary, dep_id, driver) 
VALUES (110, 'Averin T.A.', '1990.01.21', '2020.11.06', 'data scientist', 'CEO', 85000.00, 7, true);
INSERT INTO employees (emp_id, fio, birthday, startdate, job_title, levels, salary, dep_id, driver) 
VALUES (111, 'Nigmatullin I.A.', '1994.05.21', '2020.11.06', 'data scientist', 'senior', 40000.00, 7, true);
INSERT INTO employees (emp_id, fio, birthday, startdate, job_title, levels, salary, dep_id, driver) 
VALUES (112, 'Kotin R.A.', '1991.02.21', '2020.11.06', 'data scientist', 'senior', 35000.00, 7, true);
INSERT INTO departments (dep_id, tittle_dep, boss_name, num_employees) 
VALUES (7,'CEO','Averin T.A.',3);

SELECT emp_id, fio, date_part('day', '2022.11.20'::date) - date_part('day', startdate::date) as expdays FROM employees;
SELECT emp_id, fio, date_part('day', '2022.11.20'::date) - date_part('day', startdate::date) as expdays FROM employees LIMIT 3;;
SELECT emp_id, job_title, driver FROM employees WHERE driver = 'true'
SELECT bon_id, raiting FROM bonuses WHERE raiting = 'D' or raiting = 'E';
SELECT MAX(salary) as maxsalary FROM employees;
