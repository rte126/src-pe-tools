-- Team: Data Junkies
-- Employee Database Application

prompt
prompt $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
prompt
prompt *** Basic President Test
prompt
prompt Login as the president
prompt    Employee #7839, KING, PRESIDENT in dept 10
prompt Then test operations that the President can do
prompt This includes
prompt -- operations assigned to the User role
prompt -- operations assigned to the Employee role
prompt -- operations assigned to the Manager role
prompt -- operations assigned to the Executive role
prompt -- operations assigned to the President role
prompt 
pause Hit Return to start the Basic President Test

-- define information about the President who will be logged in
define me = KING
define myempno = 7839
define mydeptno = 10

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

-- then test operations assigned to the Executive role
@@assigned-Executive

-- then test operations assigned to the President role
@@assigned-President

prompt
prompt  *** Completed Basic President Test
