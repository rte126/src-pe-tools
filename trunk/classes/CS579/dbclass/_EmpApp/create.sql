-- Application Build Script

prompt *** Application Build Starting

-- Set up system path definitions
-- Assumes a is defined as the path to the root application directory
define sc = &a./CoreSubsystems
define sa = &a./AppSubsystems
define sp = &a./Spool

spool &sp\create.LST

-- Turn feedback off during core subsystem build
-- to limit output noise
set feedback off
set autoprint off

-- Initialize and build the core subsystems
@&sc/DefineCoreSystem

-- turn feedback back on, and echo too
set feedback on
set echo on

-- Initialize and build the application subsystems
@&sa/DefineAppSystem

set echo off
prompt *** Application Build Complete
spool off
prompt



