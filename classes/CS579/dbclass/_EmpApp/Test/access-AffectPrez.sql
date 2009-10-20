-- Team: Data Junkies
-- Employee Database Application

prompt
prompt $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
prompt
prompt *** AffectPrez Test
prompt 
prompt This tests the enforcement of AffectPrez:
prompt No operation may be used to hire, terminate or change the salary, 
prompt direct manager, dept or job (new or old) of the president
prompt
prompt ====================================================
prompt
pause Hit Return to start AffectPrez
prompt
prompt First Login as 7839:KING, the President
prompt Show that he can't do any of the proscribed operations to himself

prompt
prompt ==> Logout if necessary (may fail)
@&u\Logout

prompt
prompt ==> Login as 7839:KING
@&u\Login 7839 KING

prompt
prompt ==> FAIL to hire a new President (no mgr)
@&u\AddEmp 7654 SHRUB 8000 PRESIDENT NULL 20

prompt
prompt ==> FAIL to terminate me
@&u\TerminateEmp 7839

prompt
prompt ==> FAIL to change my job to CHIEF
@&u\ChangeJob 7839 CHIEF

prompt
prompt ==> FAIL to change MILLER's job to PRESIDENT
@&u\ChangeJob 7934 PRESIDENT

prompt
prompt ==> FAIL to change my manager to NULL (it already is!)
@&u\ChangeMgr 7839 NULL

prompt
prompt ==> FAIL to change my job/manager
@&u\ChangePosition 7839 NULL 10 CHIEF

prompt
prompt ====================================================
prompt
pause Hit Return to continue AffectPrez
prompt
prompt Login as 7782:CLARK, the dept mgr of the dept with the President
prompt Show that he can't do any of the proscribed operations to KING

prompt
prompt ==> Logout and Login as 7782:CLARK
@&u\Logout
@&u\Login 7782 CLARK

prompt
prompt ==> FAIL to hire a new President in the same dept (no mgr)
@&u\AddEmp 7654 SHRUB 8000 PRESIDENT NULL 10

prompt
prompt ==> FAIL to terminate 7839:KING
@&u\TerminateEmp 7839

prompt
prompt ==> FAIL to change KING's job to CHIEF
@&u\ChangeJob 7839 CHIEF

prompt
prompt ==> FAIL to change MILLER's job to PRESIDENT
@&u\ChangeJob 7934 PRESIDENT

prompt
prompt ==> FAIL to change KING's manager to NULL (it already is!)
@&u\ChangeMgr 7839 NULL

prompt
prompt ==> FAIL to change his job/manager
@&u\ChangePosition 7839 NULL 10 CHIEF

prompt
prompt *** AffectPrez Test Complete
