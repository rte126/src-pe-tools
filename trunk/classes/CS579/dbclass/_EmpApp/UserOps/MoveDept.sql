-- MoveDept
-- &1  dept number
-- &2  new dept location
-- moves department

set &startset

BEGIN
   UPDATE Depts SET loc = '&2' WHERE deptno = &1;
   pl( 'Department Moved' );
EXCEPTION WHEN OTHERS THEN doerr();
END;
/

set &finishset

