prompt
prompt Team: Data Junkies
prompt Access Constraint Tests for Employee Database Application
prompt
prompt First tests Authentication (Login, Logout and SetPwd)
prompt Then tests each access constraint separately
prompt Each constraint is checked by making sure failures occurs where expected
prompt
prompt Note: Generally no need to test cases where operations succeed;
prompt That should have been tested in the basic tests
prompt
prompt Note: to the extent possible, the operations tested only 
prompt violate access constraints; not any state constraints
prompt

-- Test Login, Logout and SetPwd
@@access-Authentication

-- Test Implicit Informational Access Constraints
@@access-EmployeeQueries

-- Test Explicit Informational Access Constraints
@@access-SeeAddr
@@access-SeeSal
@@access-SeeJobMgr

-- Test Implicit Operational Access Constraints
@@access-EmployeeActions
@@access-ExecutiveActions
@@access-PrezActions

-- Test Explicit Operational Access Constraints
@@access-AffectSelf
@@access-AffectPrez
@@access-AffectDept
@@access-NewMgr
@@access-UpdateMgr


