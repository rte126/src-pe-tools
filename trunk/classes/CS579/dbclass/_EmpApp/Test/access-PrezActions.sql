-- Team: Data Junkies
-- Employee Database Application

prompt
prompt $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
prompt
prompt *** PrezActions Test
prompt 
prompt This tests the enforcement of PrezActions:
prompt A department can only be created, destroyed or moved by the President
prompt
prompt Login as as 7566:JONES, DeptMgr of dept 20
prompt  and show that Jones cannot create a new dept
prompt  or move or destroy dept 20
prompt
pause Hit Return to test PrezActions

prompt
prompt ==> Logout if necessary (may fail)
@&u\Logout

prompt
prompt ==> Login as 7566:JONES
@&u\Login 7566 JONES

prompt
prompt ==> FAIL to create dept 77, the party dept
@&u\CreateDept 77 PARTY CANCUN

prompt
prompt ==> FAIL to move dept 20 to PARIS
@&u\MoveDept 20 PARIS

prompt
prompt ==> FAIL to destroy dept 77
@&u\DestroyDept 20 NULL

prompt
prompt *** PrezActions Test Complete


