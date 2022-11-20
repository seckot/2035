/* a Попробуйте вывести не просто самую высокую зарплату во всей команде, а вывести именно фамилию сотрудника с самой высокой зарплатой.*/
SELECT fio FROM employees WHERE salary = (SELECT MAX(salary) FROM employees);


/* b Попробуйте вывести фамилии сотрудников в алфавитном порядке */
SELECT fio FROM employees order by fio asc; 

/* c Рассчитайте средний стаж для каждого уровня сотрудников */
SELECT levels, AVG(date_part('day', '2022.11.20'::date) - date_part('day', startdate::date)) as avgexp FROM employees group by levels;


/* d Выведите фамилию сотрудника и название отдела, в котором он работает */
SELECT fio,tittle_dep FROM employees AS emps INNER JOIN departments AS deps ON emps.dep_id = deps.dep_id;

/* e Выведите название отдела и фамилию сотрудника с самой высокой зарплатой в данном отделе и саму зарплату также.*/
SELECT departments, fio, maxsalary FROM (SELECT tittle_dep as departments, MAX(salary) as maxsalary FROM employees AS emps INNER JOIN departments AS deps ON emps.dep_id = deps.dep_id group by tittle_dep) AS tmptbl INNER JOIN employees AS emp ON tmptbl.maxsalary = emp.salary