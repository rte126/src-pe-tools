-- Team: Data Junkies
-- Employee Database Application

prompt
prompt $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
prompt
prompt *** OnLoseMgr Test
prompt 
prompt This tests the enforcement of OnLoseMgr :
prompt When a manager is terminated, 
prompt all employees who directly reported to them 
prompt end up directly reporting to their manager
prompt
prompt Login as 7839:KING
prompt Create a new dept, 88, the TEMP dept
prompt Create four new employees:
prompt 4441:JOE1 in dept 88, managed by KING
prompt 4442:JOE2 in dept 88, managed by JOE1
prompt 4443:JOE3 in dept 88, managed by JOE2
prompt 4444:JOE4 in dept 10, KING's dept, managed by JOE3
prompt Then, terminate JOE3, so JOE4 is now managed by JOE2.
prompt Then, destroy dept 88.
prompt JOE1 and JOE2 will be destroyed, 
prompt and JOE4 should be managed by KING
prompt
pause Hit Return to test OnLoseMgr 

prompt
prompt ==> Logout if necessary (may fail)
@&u\Logout

prompt
prompt ==> Login as 7839:KING
@&u\Login 7839 KING

prompt ==> Create dept 88, the TEMP dept
@&u\CreateDept 88 TEMP 'NEW YORK'

prompt
prompt ==> Show that dept 88 has been created
@&u\ShowAllDepts

prompt
prompt ==> Add 4441:JOE1 the PEON to dept 88, managed by KING
@&u\AddEmp 4441 JOE1 1200 PEON 7839 88

prompt
prompt ==> Add 4442:JOE2 the PEON to dept 88, managed by JOE1
@&u\AddEmp 4442 JOE2 1200 PEON 4441 88

prompt
prompt ==> Add 4443:JOE3 the PEON to dept 88, managed by JOE2
@&u\AddEmp 4443 JOE3 1200 PEON 4442 88

prompt
prompt ==> Add 4444:JOE4 the PEON to dept 10, managed by JOE3
@&u\AddEmp 4444 JOE4 1200 PEON 4443 10

prompt
prompt ==> Show that these employees have been added
@&u\ShowCtrlEmps

prompt
prompt ==> Terminate JOE3
@&u\TerminateEmp 4443

prompt
prompt ==> Show that JOE3 is gone; JOE4 is managed by JOE2
@&u\ShowCtrlEmps

prompt
prompt ==> Destroy dept 88
@&u\DestroyDept 88 NULL

prompt
prompt ==> Show that JOE1 and JOE2 are gone, w JOE4 managed by KING 
@&u\ShowCtrlEmps

prompt
prompt *** OnLoseMgr Test Complete


