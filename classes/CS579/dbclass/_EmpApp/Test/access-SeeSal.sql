-- Team: Data Junkies
-- Employee Database Application

prompt
prompt $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
prompt
prompt *** SeeSal Test
prompt 
prompt This tests the enforcement of SeeSal:
prompt An employee's salary can only be seen by the President, or by their department manager
prompt
prompt The basic tests show that a department manager can only see their own employees
prompt We'll make sure that ShowCtrlEmps doesn't provide any information to FORD,
prompt a manager, but not a dept mgr
prompt
pause Hit Return to test SeeSal

prompt
prompt ==> Logout if necessary (may fail)
@&u\Logout

prompt
prompt ==> Login as 7902:FORD
@&u\Login 7902 FORD

prompt
prompt ==> No info shown by ShowCtrlEmps
@&u\ShowCtrlEmps

prompt
prompt *** SeeSal Test Complete


