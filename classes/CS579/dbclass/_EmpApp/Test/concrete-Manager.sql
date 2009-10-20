-- Team: Data Junkies
-- Employee Database Application

prompt
prompt $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
prompt
prompt *** Basic Manager Test
prompt
prompt Login as a manager (but not a dept mgr)
prompt    Employee #7902, FORD, ANALYST in dept 20
prompt Then test operations that a Manager can do
prompt This includes
prompt -- operations assigned to the User role
prompt -- operations assigned to the Employee role
prompt -- operations assigned to the Manager role
prompt 
pause Hit Return to start the Basic Manager Test

-- define information about the Manager who will be logged in
define me = FORD
define myempno = 7902
define mydeptno = 20

-- define information about another Employee (used in the tests)
define other = ADAMS

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

-- then test operations assigned to the Manager role
@@assigned-Manager

prompt
prompt  *** Completed Basic Manager Test
