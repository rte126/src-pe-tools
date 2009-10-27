prompt
prompt Counting Employees who make more than 2900

select deptno, count(*) as "Employee Count" from emp
where sal > 2900 
group by deptno;
