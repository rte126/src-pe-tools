-- Team: Data Junkies
-- Employee Database Application

prompt
prompt $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
prompt
prompt *** OnePrez Test
prompt 
prompt This tests the enforcement of OnePrez :
prompt Only one employee has the job of president
prompt
prompt Login as 7839:KING
prompt and try to hire a new President
prompt or use ChangeJob or ChangePosition to make someone president
prompt
pause Hit Return to test OnePrez 

prompt
prompt ==> Logout if necessary (may fail)
@&u\Logout

prompt
prompt ==> Login as 7839:KING
@&u\Login 7839 KING

prompt
prompt ==> FAIL to hire a new president
@&u\AddEmp 4321 SHRUB 8000 PRESIDENT NULL 20

prompt
prompt ==> FAIL to use ChangeJob to make MILLER President
prompt ==> (Will actually violate NoPrezMgr)
@&u\ChangeJob 7934 PRESIDENT

prompt
prompt ==> FAIL to use ChangePosition to make MILLER President
@&u\ChangePosition 7934 NULL 10 PRESIDENT

prompt
prompt *** OnePrez Test Complete


