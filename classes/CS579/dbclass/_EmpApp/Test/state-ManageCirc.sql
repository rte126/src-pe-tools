-- Team: Data Junkies
-- Employee Database Application

prompt
prompt $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
prompt
prompt *** ManageCirc Test
prompt 
prompt This tests the enforcement of ManageCirc:
prompt No one manages themselves, their manager, their manager's manager, etc.
prompt
prompt Login as 7839:KING
prompt FAIL to create EGON, a PEON in dept 20 managed by himself
prompt FAIL to make SMITH managed by himself
prompt FAIL to make SMITH managed by EGON (a new PEON manged by SMITH)
prompt FAIL to make FORD (SMITH's mgr) managed by EGON
prompt 
pause Hit Return to test ManageCirc 

prompt
prompt ==> Logout if necessary (may fail)
@&u\Logout

prompt
prompt ==> Login as 7839:KING
@&u\Login 7839 KING

prompt
prompt ==> FAIL to create EGON, a PEON in dept 20 managed by himself
@&u\AddEmp 4321 EGON 2000 PEON 4321 20

prompt
prompt ==> FAIL to use ChangeMgr to make 7369:SMITH managed by himself
@&u\ChangeMgr 7369 7369

prompt
prompt ==> FAIL to use ChangePosition to make 7369:SMITH managed by himself
@&u\ChangePosition 7369 7369 20 CLERK

prompt
prompt ==> Create EGON, a PEON in dept 20 managed by 7369:SMITH
@&u\AddEmp 4321 EGON 2000 PEON 7369 20

prompt
prompt ==> FAIL to use ChangeMgr to make 7369:SMITH managed by EGON
@&u\ChangeMgr 7369 4321

prompt
prompt ==> FAIL to use ChangePosition to make 7369:SMITH managed by EGON
@&u\ChangePosition 7369 4321 20 CLERK

prompt
prompt ==> FAIL to use ChangeMgr to make 7902:FORD managed by EGON
@&u\ChangeMgr 7902 4321

prompt
prompt ==> FAIL to use ChangePosition to make 7902:FORD managed by EGON
@&u\ChangePosition 7902 4321 20 ANALYST

prompt
prompt ==> Terminate EGON
@&u\TerminateEmp 4321

prompt
prompt *** ManageCirc Test Complete


