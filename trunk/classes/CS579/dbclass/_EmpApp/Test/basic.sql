prompt
prompt Team: Data Junkies
prompt Basic Operation Tests for Employee Database Application
prompt Tests all operations, role by role
prompt These tests are designed to work even if all constraints are enforced

prompt
prompt First, test a logged out user, 
prompt then logs in a typical user having each CONCRETE role
prompt In each case, tests all operations that user can do:
prompt (all operations associated just with their role
prompt as well as all operations associated with all of their super-roles)

@@concrete-User
@@concrete-Employee
@@concrete-Manager
@@concrete-DeptManager
@@concrete-President
