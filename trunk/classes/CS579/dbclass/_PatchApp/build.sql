-- Application Build and Initialization Script

prompt *** Application Build Starting

-- Set up system path definitions
-- Assumes a is defined as the path to the root application directory
define sc = &a./CoreSubsystems
define sd = &a./DisplaySubsystems
define sa = &a./AppSubsystems
define u = &a./UserOps
define p = &a./Populate

-- User operations that execute PL/SQL code
-- start by setting &startset and finish by executing &finishset
-- Ordinarily, these turn feedback on and off during operations
-- But during populate, we just want to leave feedback off
define startset = 'feedback off'
define finishset = 'feedback off'

-- Turn feedback off during build & populate
-- to limit output noise
set feedback off
set autoprint off

-- Initialize and build the core subsystems
@&sc/DefineCoreSystem

-- Initialize and build the application subsystems
@&sa/DefineAppSystem

prompt *** Application Build Complete
prompt
prompt *** Application Initialization Starting

-- Initialize Display Subsystem
@&sd/DefineDisplaySystem

-- Initialize middle-tier
@&u/_init

prompt *** Application Initialization Complete
prompt
prompt *** Application Populate Starting

-- Turn non-error printing off during populate operations
execute display_control.disable_print();

-- Populate the database
@&p/Populate

-- Turn non-error printing back on
execute display_control.enable_print();

-- turn feedback back on
set feedback on
define finishset = 'feedback on'

prompt *** Application Populate Complete
prompt

