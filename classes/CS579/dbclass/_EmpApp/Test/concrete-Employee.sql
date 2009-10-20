-- Team: Data Junkies
-- Employee Database Application

prompt
prompt $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
prompt
prompt *** Basic Employee Test
prompt
prompt Login as an ordinary employee:
prompt    Employee #7876, ADAMS, CLERK in dept 20
prompt Then test operations that any Employee can do
prompt This includes
prompt -- operations assigned to the User role
prompt -- operations assigned to the Employee role
prompt
pause Hit Return to start the Basic Employee Test

-- define information about the ordinary Employee who will be logged in
define me = ADAMS
define myempno = 7876
define mydeptno = 20

-- define information about another Employee (used in the tests)
define other = MARTIN

prompt
prompt ==> First Logout
@&u\Logout

prompt
prompt ==> Note that we conveniently use a user's name as their initial password
prompt
prompt ==> Login as &myempno:&me
@&u\Login &myempno &me

-- first test operations assignd to the User role
@@assigned-User

-- then test operations assigned to the Employee role
@@assigned-Employee

prompt
prompt  *** Completed Basic Employee Role Test
