-- Team: Data Junkies
-- Employee Database Application

prompt
prompt $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
prompt
prompt *** UpdateMgr Test
prompt 
prompt This tests the enforcement of UpdateMgr:
prompt An employee's direct manager can only be changed by a department manager
prompt if both the old and new direct manager are in the department manager's department
prompt
prompt Login as 7782:CLARK,the dept mgr of dept 10
prompt and fail to change the mgr of MILLER (in dept 10)
prompt to BLAKE (in dept 30)
prompt then have the president change MILLER's mgr to BLAKE
prompt and CLARK will then fail to change it back, but the prez can
prompt
pause Hit Return to test UpdateMgr

prompt
prompt ==> Logout if necessary (may fail)
@&u\Logout

prompt
prompt ==> Login as 7782:CLARK, dept mgr of dept 10
@&u\Login 7782 CLARK

prompt
prompt ==> FAIL to change MILLER's mgr to BLAKE w ChangeMgr
@&u\ChangeMgr 7934 7698

prompt
prompt ==> FAIL to change MILLER's mgr to BLAKE w ChangePosition
@&u\ChangePosition 7934 7698 10 CLERK

prompt
prompt ==> Logout and Login as the president
@&u\Logout
@&u\Login 7839 KING

prompt
prompt ==> Move dept 30 to NEW YORK (so it's in same loc as dept 10)
@&u\MoveDept 30 'NEW YORK'

prompt
prompt ==> Change MILLER's mgr to BLAKE
@&u\ChangeMgr 7934 7698

prompt
prompt ==> Logout and Login as CLARK again
@&u\Logout
@&u\Login 7782 CLARK

prompt
prompt ==> FAIL to change MILLER's mgr back to me w ChangeMgr
@&u\ChangeMgr 7934 7782

prompt
prompt ==> FAIL to change MILLER's mgr back to me w ChangePosition
@&u\ChangePosition 7934 7782 10 CLERK

prompt
prompt ==> Logout and Login as the president again
@&u\Logout
@&u\Login 7839 KING

prompt
prompt ==> Change MILLER's mgr back to CLARK
@&u\ChangeMgr 7934 7782

prompt
prompt ==> Move dept 30 back to CHICAGO
@&u\MoveDept 30 CHICAGO

prompt
prompt *** UpdateMgr Test Complete


