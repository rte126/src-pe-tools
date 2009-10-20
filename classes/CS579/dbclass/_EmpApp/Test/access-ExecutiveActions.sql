-- Team: Data Junkies
-- Employee Database Application

prompt
prompt $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
prompt
pause Hit Return to test ExecutiveActions
prompt
prompt *** ExecutiveActions Test
prompt 
prompt This tests the enforcement of ExecutiveActions:
prompt An employee can only be hired, fired 
prompt or have their job, salary or manager changed by the President, 
prompt or by the manager of their department
prompt
prompt ====================================================
prompt
pause Hit Return to start ExecutiveActions
prompt
prompt First, login as as 7566:JONES, DeptMgr of dept 20
prompt  and show that he cannot hire an employee in dept 30
prompt     (with manager BLAKE, deptmgr of dept 30)
prompt  or fire JAMES (a CLERK in a dept 30)
prompt  or change JAMES' salary, job, or manager
prompt   (to BLAKE, DEPTMGR of dept 30) 

prompt
prompt ==> Logout if necessary (may fail)
@&u\Logout

prompt
prompt ==> Login as 7566:JONES
@&u\Login 7566 JONES

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
prompt ==> Logout if necessary (may fail)
@&u\Logout

prompt
prompt ====================================================
prompt
pause Hit Return to continue ExecutiveActions
prompt
prompt Now, login as as 7844:TURNER, Salesman in dept 30
prompt  and show that he cannot hire an employee in dept 30
prompt  or fire JAMES (who is in a dept 30)
prompt  or change JAMES' salary, job or manager (to BLAKE) 
prompt

prompt ==> Login as 7844:TURNER
@&u\Login 7844 TURNER

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
prompt *** ExecutiveActions Test Complete


