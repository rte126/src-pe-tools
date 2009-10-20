-- Team: Data Junkies
-- Employee Database Application

prompt
prompt $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
prompt
prompt *** AffectDept Test
prompt 
prompt This tests the enforcement of AffectDept:
prompt An employee's department can only be changed by the President
prompt
prompt Login as 7782:CLARK,the dept mgr of dept 10 (where KING is)
prompt and try to change MILLER (in dept 10) to dept 20
prompt and JAMES (in dept 30) to dept 10
prompt
pause Hit Return to test AffectDept

prompt
prompt ==> Logout if necessary (may fail)
@&u\Logout

prompt
prompt ==> Login as 7782:CLARK, dept mgr of dept 10
@&u\Login 7782 CLARK

prompt
prompt ==> FAIL to move 7934:MILLER to dept 20
@&u\ChangePosition 7934 7782 20 CLERK

prompt
prompt ==> FAIL to move 7900:JAMES to dept 10
@&u\ChangePosition 7900 7698 10 CLERK

prompt
prompt *** AffectDept Test Complete


