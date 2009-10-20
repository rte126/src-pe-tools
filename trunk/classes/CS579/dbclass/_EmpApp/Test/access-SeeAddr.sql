-- Team: Data Junkies
-- Employee Database Application

prompt
prompt $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
prompt
prompt *** SeeAddr Test
prompt 
prompt This tests the enforcement of SeeAddr:
prompt An employee can see another employee's address only if they have made it public
prompt
prompt Show that even the president KING can only see WARD's address when it is public
prompt
pause Hit Return to test SeeAddr

prompt
prompt ==> Logout if necessary (may fail)
@&u\Logout

prompt
prompt ==> Login as 7521:WARD
@&u\Login 7521 WARD

prompt
prompt ==> Set my address and make it visible
@&u\ChangeAddr '100 WARD Way' Boston MA 01234 T

prompt
prompt ==> Show My information
@&u\ShowMyInfo

prompt
prompt ==> Now, logout and login as 7839:KING
@&u\Logout
prompt
@&u\Login 7839 KING

prompt
prompt ==> KING can see WARD's address
@&u\ShowAddr WARD

prompt
prompt ==> Now, logout and login as 7521:WARD again
@&u\Logout
prompt
@&u\Login 7521 WARD

prompt
prompt ==> Set my address but now make it invisible
@&u\ChangeAddr '100 WARD Way' Boston MA 01234 F

prompt
prompt ==> Show My information
@&u\ShowMyInfo

prompt
prompt ==> Now, logout and login as 7839:KING again
@&u\Logout
prompt
@&u\Login 7839 KING

prompt
prompt ==> FAIL to now see WARD's address
@&u\ShowAddr WARD

prompt
prompt *** SeeAddr Test Complete


