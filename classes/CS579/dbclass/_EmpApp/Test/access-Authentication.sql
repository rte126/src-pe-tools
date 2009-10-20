-- Team: Data Junkies
-- Employee Database Application

prompt
prompt $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
prompt
prompt *** Authentication Test
prompt 
prompt This tests user authetication:
prompt Login (including GoodLogin), Logout and SetPwd
prompt
prompt
pause Hit Return to test Authentication 

prompt
prompt ==> Logout if necessary (may fail)
@&u\Logout

prompt
prompt ==> FAIL to Logout; already logged out
@&u\Logout

prompt
prompt ==> FAIL to Login as 7369:SMITH with wrong password
@&u\Login 7369 YABADABA

prompt
prompt ==> Login as 7369:SMITH
@&u\Login 7369 SMITH

prompt
prompt ==> FAIL to SetPwd beacuse new passwords don't match
@&u\SetPwd SMITH KLAATU KARASS

prompt
prompt ==> Change password to KARASS
@&u\SetPwd SMITH KARASS KARASS

prompt
prompt ==> Logout
@&u\Logout

prompt
prompt ==> Login as 7369:SMITH with new password KARASS
@&u\Login 7369 KARASS

prompt
prompt ==> Change password back to SMITH
@&u\SetPwd KARASS SMITH SMITH

prompt
prompt ==> Logout
@&u\Logout

prompt
prompt ==> Login as 7369:SMITH with old password SMITH
@&u\Login 7369 SMITH

prompt
prompt ==> FAIL to login as CLARK; already logged in
@&u\Login 7782 CLARK  

prompt
prompt *** Authentication Test Complete


