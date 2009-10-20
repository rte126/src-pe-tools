-- ShowDeptLoc
-- &1  employee name
-- Show the department and location of an employee, given their name

set &startset

DECLARE
   knt int := 0;
BEGIN
   FOR rec IN (SELECT * FROM (Emps NATURAL JOIN Depts) 
                 WHERE ename = '&1') LOOP
      pl( 'Department ' || rec.deptno || ', ' || rec.loc );
      knt := knt + 1; 
   END LOOP;
   IF knt = 0 THEN
      pl( 'No such employee' );
   END IF;
EXCEPTION WHEN OTHERS THEN plerr();
END;
/

set &finishset


