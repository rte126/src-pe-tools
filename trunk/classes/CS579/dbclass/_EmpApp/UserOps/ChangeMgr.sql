-- ChangeMgr
-- &1  employee number
-- &2  new manager's employee number
-- Change the manager of an employee 

set &startset

BEGIN
  UPDATE Emps SET mgr = &2 WHERE empno = &1;
  pl( 'Manager Changed' );
EXCEPTION WHEN OTHERS THEN doerr();
END;
/

set &finishset


