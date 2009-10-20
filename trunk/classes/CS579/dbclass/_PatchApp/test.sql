-- Prompt for the test name and spool prefix
-- and then run the corresponding test
-- spooling the result

-- Assumes a is defined as the path to the root application directory
define t = &a./Test
define sp = &a./Spool

prompt enter test name and spool prefix
undefine test_name
undefine spool_prefix
define tn = &test_name
spool &sp\&spool_prefix.&tn
@&t\&tn
spool off
prompt
prompt

