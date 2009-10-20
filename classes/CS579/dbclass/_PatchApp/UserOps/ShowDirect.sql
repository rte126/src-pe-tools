-- ShowDirect
-- Show the employee numer & name, department, and job of all the employees who report directly to the user

SELECT empno, ename, job, dname FROM (Depts NATURAL JOIN Emps)
   WHERE mgr = :curuser
   ORDER BY dname, ename;


