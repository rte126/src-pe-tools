-- ChangeSal
-- &1  employee number
-- &2  new salary
-- Change the salary of an employee 

set &startset

BEGIN
  UPDATE Emps SET sal = &2 WHERE empno = &1;
  pl( 'Salary Changed' );
EXCEPTION WHEN OTHERS THEN doerr();
END;
/

set &finishset

