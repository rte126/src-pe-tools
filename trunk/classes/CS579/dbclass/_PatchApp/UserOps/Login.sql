-- Login
-- &1  employee number
-- &2  password
-- Logs in a user; echoes back their user id and role

-- Preconditions:
-- there must be a user wth the specified id and password

set &startset

BEGIN

    -- Note: this implementation of login does not actually check that
    -- the employee number and password are legal.
    -- Of course, the final version will need to do so.
    -- If you want to perform this check now, see the notes
    -- in the homework assignment

    :curuser := &1;

    pl( 'Logged In As ' || :curuser || 
      ' with role of ' || GetRole(:curuser) );

EXCEPTION WHEN OTHERS THEN doerr();
END;
/

set &finishset

