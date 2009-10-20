-- Team: Data Junkies
-- Employee Database Application

prompt
prompt $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
prompt
prompt *** EmployeeQueries Test
prompt 
prompt This tests the enforcement of the EmployeeQueries constraint
prompt All queries except ShowAllDepts require that the user be logged in
prompt Test by logging out and trying all queries
prompt
pause Hit Return to test EmployeeQueries

prompt
prompt ==> Logout if necessary (may fail)
@&u\Logout

prompt
prompt ==> FAIL to see ADAMS address
@&u\ShowAddr 7876

prompt
prompt ==> FAIL to see my info
@&u\ShowMyInfo

prompt
prompt ==> FAIL to see Dept Mgt
@&u\ShowDeptMgt

prompt
prompt ==> FAIL to see Dept Info for ADAMS
@&u\ShowDeptLoc 7876

prompt
prompt ==> FAIL to see employees in my dept
@&u\ShowDeptEmps

prompt
prompt ==> FAIL to see employees I manage
@&u\ShowDirect

prompt
prompt ==> FAIL to see employees below me
@&u\ShowBelow

prompt
prompt ==> FAIL to see employees I control
@&u\ShowCtrlEmps

prompt
prompt *** EmployeeQueries Test Complete


