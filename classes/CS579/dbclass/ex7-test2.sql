prompt
prompt Group by &1

select &1, count(*) from emp group by &1;

prompt Group by &1, with salary > &2

select &1, count(*) from emp where sal > &2 
group by &1;
