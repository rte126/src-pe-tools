-- CreateDept
-- &1  new dept number
-- &2  dept name
-- &3  dept location
-- creates new department

set &startset

BEGIN
  INSERT INTO Depts VALUES( &1, '&2', '&3', NULL );
  pl( 'Department Created' );
EXCEPTION WHEN OTHERS THEN doerr();
END;
/

set &finishset

