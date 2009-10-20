-- DestroyDept
-- &1  dept number
-- &2  relocation dept (department where its employees are moved
--                      NULL means terminate employees)
-- destroys department

set &startset

BEGIN
   IF &2 IS NULL THEN
      DELETE FROM Emps WHERE deptno = &1;
   ELSE
      UPDATE Emps SET deptno = &2 WHERE deptno = &1;
   END IF;
   DELETE FROM Depts WHERE deptno = &1;
  pl( 'Department Destroyed' );
EXCEPTION WHEN OTHERS THEN doerr();
END;
/

set &finishset
