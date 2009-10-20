-- Team: Data Junkies
-- Employee Database Application

prompt
prompt $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
prompt
prompt *** NewMgr Test
prompt 
prompt This tests the enforcement of NewMgr:
prompt An employee can be hired by a dept manager only
prompt if their new direct manager is in the department manager's department
prompt
prompt Login as 7698:BLAKE,the dept mgr of dept 30
prompt and try to hire a new employee for that dept 
prompt whose mgr is 7566:JONES (deptmgr of dept 20)
prompt
pause Hit Return to test NewMgr

prompt
prompt ==> Logout if necessary (may fail)
@&u\Logout

prompt
prompt ==> Login as 7698:BLAKE, dept mgr of dept 30
@&u\Login 7698 BLAKE

prompt
prompt ==> FAIL to hire a new employee for dept 30 w JONES as mgr
@&u\AddEmp 5678 LUNA 2000 PEON 7566 30

prompt
prompt *** NewMgr Test Complete


