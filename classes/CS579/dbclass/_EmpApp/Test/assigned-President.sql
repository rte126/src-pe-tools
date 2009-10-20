-- Team: Data Junkies
-- Employee Database Application

prompt
prompt =====================================================
prompt 
prompt This tests operations assigned to the President Role
prompt but not assigned to any of its super-roles
prompt   Actions: CreateDept, MoveDept, DestroyDept
prompt
pause Hit Return to test President role operations

prompt
prompt ==> Create dept 77, the party dept
@&u\CreateDept 77 PARTY CANCUN

prompt
prompt ==> Show that dept 77 has been created
@&u\ShowAllDepts

prompt
prompt ==> Move dept 77 to PARIS
@&u\MoveDept 77 PARIS

prompt
prompt ==> Show that dept 77 has been moved to PARIS
@&u\ShowAllDepts

prompt
prompt ==> Destroy dept 77
@&u\DestroyDept 77 NULL

prompt
prompt ==> Show that dept 77 has been destroyed
@&u\ShowAllDepts


