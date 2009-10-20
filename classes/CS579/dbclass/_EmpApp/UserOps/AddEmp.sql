-- AddEmp
-- &1  new employee number
-- &2  employee name
-- &3  salary
-- &4  job
-- &5  manager
-- &6  dept number
-- Add a new employee with the specified characteristics

set &startset

BEGIN
   INSERT INTO Emps( empno, ename, sal, job, mgr, deptno )
      VALUES ( &1, '&2', &3, '&4', &5, &6 );
  pl( 'Employee Added' );
EXCEPTION WHEN OTHERS THEN doerr();
END;
/

set &finishset
