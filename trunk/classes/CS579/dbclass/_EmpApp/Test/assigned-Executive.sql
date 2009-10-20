-- Team: Data Junkies
-- Employee Database Application

prompt
prompt =====================================================
prompt 
prompt This tests operations assigned to the Executive Role
prompt but not assigned to any of its super-roles
prompt   Queries: ShowCtrlEmps
prompt   Actions: AddEmp, ChangeJob, ChangeMgr, ChangePosition, ChangeSal, TerminateEmp
prompt
pause Hit Return to test Executive role operations

-- make definitions needed for new employee in executive test
define newmgr = 7902
define newdept = 20

prompt
prompt ==> Show info of employees who I control
prompt -- all other users if I'm the president
prompt -- all other users in my dept if I'm a deptmgr
@&u\ShowCtrlEmps

prompt
prompt ==> Add BOZO the CLOWN as an employee to dept &newdept, mgr &newmgr
@&u\AddEmp 9090 BOZO 3333 CLOWN &newmgr &newdept

prompt
prompt ==> Show that BOZO has been added
@&u\ShowCtrlEmps

prompt
prompt ==> Change BOZO's salary to 2222
@&u\ChangeSal 9090 2222

prompt
prompt ==> Show that BOZO's sal is now 2222
@&u\ShowCtrlEmps

prompt
prompt ==> Change BOZO's job to JESTER
@&u\ChangeJob 9090 JESTER

prompt
prompt ==> Show that BOZO's job is now JESTER
@&u\ShowCtrlEmps

prompt
prompt ==> Change BOZO's mgr to be me
@&u\ChangeMgr 9090 &myempno

prompt
prompt ==> Show that BOZO's mgr is now me
@&u\ShowCtrlEmps

prompt
prompt ==> Change back BOZO's job and mgr, and make sure BOZO is in my dept
@&u\ChangePosition 9090 &newmgr &mydeptno CLOWN

prompt
prompt ==> Show that BOZO's job, mgr and deptno are correct
@&u\ShowCtrlEmps

prompt
prompt ==> Terminate BOZO
@&u\TerminateEmp 9090

prompt
prompt ==> Show that BOZO is now gone
@&u\ShowCtrlEmps







