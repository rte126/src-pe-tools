-- ChangeJob
-- &1  employee number
-- &2  new job
-- Change the job of an employee 

set &startset

BEGIN
  UPDATE Emps SET job = '&2' WHERE empno = &1;
  pl( 'Job Changed' );
EXCEPTION WHEN OTHERS THEN doerr();
END;
/

set &finishset

