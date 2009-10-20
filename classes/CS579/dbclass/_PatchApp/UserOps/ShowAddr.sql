-- ShowAddr
-- &1  employee name
-- Show the address of an employee, if public, given their name

set &startset

DECLARE
   knt int := 0;
BEGIN
   FOR rec IN (
      SELECT * FROM Emps
         WHERE ename = '&1' AND isPublic = 'T') 
   LOOP
      pl( rec.street );
      pl( rec.city || ', ' || rec.state || ' ' || rec.zip );
      pl( '' );
      knt := knt + 1;
   END LOOP;
   IF knt = 0 THEN
      pl( 'No employee info to be shown' );
   END IF;
EXCEPTION WHEN OTHERS THEN plerr();
END;
/

set &finishset



