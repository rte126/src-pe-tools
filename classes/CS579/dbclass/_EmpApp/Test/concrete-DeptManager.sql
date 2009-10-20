-- Team: Data Junkies
-- Employee Database Application

prompt
prompt $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
prompt
prompt *** Basic Dept Manager Test
prompt
prompt Login as a dept mgr 
prompt    Employee #7566, JONES, DEPTMGR in dept 20
prompt Then test operations that a Dept Manager can do
prompt This includes
prompt -- operations assigned to the User role
prompt -- operations assigned to the Employee role
prompt -- operations assigned to the Manager role
prompt -- operations assigned to the Executive role
prompt 
pause Hit Return to start the Basic Dept Manager Test

-- define information about the Dept Manager who will be logged in
define me = JONES
define myempno = 7566
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

-- first test operations assigned to the User role
@@assigned-User

-- then test operations assigned to the Employee role
@@assigned-Employee

-- then test operations assigned to the Manager role
@@assigned-Manager

-- then test operations assigned to the Executive role
@@assigned-Executive

prompt
prompt  *** Completed Basic Dept Manager Test
