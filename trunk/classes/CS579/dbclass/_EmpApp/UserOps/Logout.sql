-- Logout
-- Logs out the currently logged in user

set &startset

BEGIN
  :curuser := NULL;
  pl( 'Logged Out' );
EXCEPTION WHEN OTHERS THEN doerr();
END;
/

set &finishset

