-- Mid-Tier Application Build and Initialization Script

prompt *** Schema Build Starting

-- Set up system path definitions
-- Assumes a is defined as the path to the root application directory
define sc = &a./CoreSubsystems
define sd = &a./DisplaySubsystems

-- Turn feedback off during build
-- to limit output noise
set feedback off
set autoprint off

-- Initialize and build the core subsystems
@&sc/DefineCoreSystem

-- Initialize Display Subsystem
@&sd/DefineDisplaySystem

-- turn feedback back on
set feedback on

prompt *** Schema Build Complete
prompt

-- Initialize Application
@@appinit
