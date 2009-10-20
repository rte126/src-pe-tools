-- ChangePosition
-- &1  employee number
-- &2  new manager's employee number
-- &3  new department number
-- &4  new job
-- Change the manager and/or dept and/or job of an employee 

set &startset

BEGIN
  UPDATE Emps SET mgr = &2, deptno = &3, job = '&4'
      WHERE empno = &1;
  pl( 'Position Changed' );
EXCEPTION WHEN OTHERS THEN doerr();
END;
/

set &finishset

