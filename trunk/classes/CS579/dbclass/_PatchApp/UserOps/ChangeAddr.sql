-- ChangeAddr
-- &1  new street
-- &2  new city
-- &3  new state
-- &4  new zip
-- &5  is it public?
-- Set/change the user's address, and mark it public or not

set &startset

BEGIN
   UPDATE Emps
      SET street = '&1', city = '&2', state = '&3', zip = '&4', ispublic = '&5'
      WHERE empno = :curuser;
  pl( 'Address Changed' );
EXCEPTION WHEN OTHERS THEN doerr();
END;
/

set &finishset

