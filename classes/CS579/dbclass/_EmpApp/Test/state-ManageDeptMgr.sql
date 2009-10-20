-- Team: Data Junkies
-- Employee Database Application

prompt
prompt $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
prompt
prompt *** ManageDeptMgr Test
prompt 
prompt This tests the enforcement of ManageDeptMgr:
prompt All department managers are directly managed by the President
prompt or by no one if there is no President

prompt
prompt Just tests case where there is a President
prompt Login as 7839:KING
prompt
prompt To test AddEmp:
prompt Creates a new dept 77 in CHICAGO (where dept 30 is located)
prompt FAIL to create a new DEPTMGR in dept 77,
prompt   managed by BLAKE (dept 30's manager)
prompt
prompt To test ChangeJob (and ChangePosition)
prompt Creates EGON, a new PEON in dept 77 managed by BLAKE
prompt FAIL to change EGON's job to DEPTMGR
prompt
prompt To test ChangeMgr (and ChangePosition)
prompt Change EGON's mgr to KING and job to DEPTMGR
prompt FAIL to change EGON's mgr to BLAKE
prompt 
pause Hit Return to test ManageDeptMgr

prompt
prompt ==> Logout if necessary (may fail)
@&u\Logout

prompt
prompt ==> Login as 7839:KING
@&u\Login 7839 KING

prompt 
prompt Creates a new dept 77 in CHICAGO (where dept 30 is located)
@&u\CreateDept 77 PARTY CHICAGO

prompt
prompt FAIL to create a new DEPTMGR in dept 77 managed by 7698:BLAKE
@&u\AddEmp 4321 EGON 2000 DEPTMGR 7698 77

prompt
prompt Creates EGON, a new PEON in dept 77 managed by BLAKE
@&u\AddEmp 4321 EGON 2000 PEON 7698 77

prompt
prompt FAIL to change EGON's job to DEPTMGR via ChangeMgr
@&u\ChangeJob 4321 DEPTMGR

prompt
prompt FAIL to change EGON's job to DEPTMGR via ChangePosition
@&u\ChangePosition 4321 7698 77 DEPTMGR

prompt
prompt Change EGON's mgr to KING and job to DEPTMGR
@&u\ChangePosition 4321 7839 77 DEPTMGR

prompt
prompt FAIL to change EGON's mgr to BLAKE via ChangeMgr
@&u\ChangeMgr 4321 7698

prompt
prompt FAIL to change EGON's mgr to BLAKE via ChangeMgr
@&u\ChangePosition 4321 7698 77 DEPTMGR

prompt
prompt Cleanup: Terminate EGON
@&u\TerminateEmp 4321

prompt
prompt Cleanup: Destroy Dept 77
@&u\DestroyDept 77 NULL

prompt
prompt *** ManageDeptMgr Test Complete


