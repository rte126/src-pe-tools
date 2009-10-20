-- Team: Data Junkies
-- Employee Database Application

prompt
prompt $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
prompt
prompt *** EmployeeActions Test
prompt 
prompt This tests the enforcement of EmployeeActions:
prompt All actions except Login require that the user be logged in
prompt
prompt Logout, then try out all the non-president actions
prompt
pause Hit Return to test EmployeeActions

prompt
prompt ==> Logout if necessary (may fail)
@&u\Logout

prompt
prompt ==> FAIL to change address
@&u\ChangeAddr '100 NO Way' Boston MA 01234 T

prompt
prompt ==> FAIL to change password
@&u\SetPwd NULL new new

prompt
prompt ==> FAIL to hire an employee in dept 30 w mgr BLAKE
@&u\AddEmp 9090 BOZO 3333 CLOWN 7698 30

prompt
prompt ==> FAIL to terminate JAMES
@&u\TerminateEmp 7900

prompt
prompt ==> FAIL to change JAMES salary
@&u\ChangeSal 7900 3333

prompt
prompt ==> FAIL to change JAMES job
@&u\ChangeJob 7900 PEON

prompt
prompt ==> FAIL to change JAMES mgr to BLAKE
@&u\ChangeMgr 7900 7698

prompt
prompt ==> FAIL to change JAMES mgr and job
@&u\ChangePosition 7900 7698 30 PEON

prompt
prompt *** EmployeeActions Test Complete


