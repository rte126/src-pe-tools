-- ShowMyInfo
-- Show the user their personal information

set &startset

BEGIN
   FOR rec IN (SELECT * FROM (Depts NATURAL JOIN Emps)
               WHERE empno = :curuser)
   LOOP
      pl( rec.empno || '  ' || rec.ename );
      pl( 'Department ' || rec.deptno || ':' || rec.dname || ', ' || rec.job ); 
      pl( 'Address: ' || rec.street || ', ' || rec.city || ', ' || rec.state || ' ' || rec.zip );
   END LOOP;
EXCEPTION WHEN OTHERS THEN plerr();
END;
/

set &finishset


