-- set output format sizes
set linesize 125
set pagesize 49999

-- don't display long comments
set document off

-- don't show variable substitution 
set verify off

-- show server-side output
set serveroutput on

-- automatically commit each submitted request
set autocommit on

-- change the prompt based on the database login name
-- yes, this is esoteric ...
--  ... turn off terminal output while doing this
set termout off
column usrval new_value usrval
select user as usrval from dual;
set sqlprompt '&usrval> '
set termout on

prompt Standard Setup Complete
