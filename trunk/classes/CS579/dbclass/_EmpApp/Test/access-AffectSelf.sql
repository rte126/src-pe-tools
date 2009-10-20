-- Team: Data Junkies
-- Employee Database Application

prompt
prompt $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
prompt
prompt *** AffectSelf Test
prompt 
prompt This tests the enforcement of AffectSelf:
prompt An employee cannot change their own salary, job, manager or dept
prompt
prompt Only dept mgrs or the prez can do these things anyway,
prompt and AffectPrez tests this for the president
prompt So, login as 7566:JONES, a deptmgr
prompt and show he can't fire himself or change his salary, job or mgr
prompt (only the president can change depts)
prompt
pause Hit Return to test AffectSelf

prompt
prompt ==> Logout if necessary (may fail)
@&u\Logout

prompt
prompt ==> Login as 7566:JONES
@&u\Login 7566 JONES

prompt
prompt ==> FAIL to terminate himself
@&u\TerminateEmp 7566

prompt
prompt ==> FAIL to change his salary
@&u\ChangeSal 7566 8000

prompt
prompt ==> FAIL to change his job to CHIEF
@&u\ChangeJob 7566 CHIEF

prompt
prompt ==> FAIL to change his manager to 7839:KING (it already is!)
@&u\ChangeMgr 7566 7839

prompt
prompt ==> FAIL to change his job/manager
@&u\ChangePosition 7566 7839 20 CHIEF

prompt
prompt *** AffectSelf Test Complete


