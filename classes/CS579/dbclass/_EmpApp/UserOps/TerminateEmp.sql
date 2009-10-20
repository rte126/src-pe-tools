-- TerminateEmp
-- &1  employee number
-- Terminates an employee

set &startset

BEGIN
  DELETE FROM Emps WHERE empno = &1;
  pl( 'Employee Terminated' );
EXCEPTION WHEN OTHERS THEN doerr();
END;
/

set &finishset

