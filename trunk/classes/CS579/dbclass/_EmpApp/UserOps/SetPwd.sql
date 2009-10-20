-- SetPwd
-- &1  old password
-- &2  new password
-- &3  new password again
-- Set/change the user's password

set &startset

BEGIN
    pl( 'Ignore for Now' );
EXCEPTION WHEN OTHERS THEN doerr();
END;
/

set &finishset

