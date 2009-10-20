-- Team: Data Junkies
-- Employee Database Application

prompt
prompt $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
prompt
prompt *** SeeJobMgr Test
prompt 
prompt This tests the enforcement of SeeJobMgr:
prompt Users who are not executives can only find out the job or mgr of employees in their department,
prompt  or that they directly or indirectly manage
prompt
prompt The basic tests show that this is already true of managers who are not dept managers
prompt We'll make sure that ShowDirect and ShowBelow don't provide info to MILLER,
prompt  an ordinary employee in a dept with managers
prompt
pause Hit Return to test SeeJobMgr

prompt
prompt ==> Logout if necessary (may fail)
@&u\Logout

prompt
prompt ==> Login as 7934:MILLER
@&u\Login 7934 MILLER

prompt
prompt ==> No info shown by ShowDirect
@&u\ShowDirect

prompt
prompt ==> No info shown by ShowBelow
@&u\ShowBelow

prompt
prompt *** SeeJobMgr Test Complete


