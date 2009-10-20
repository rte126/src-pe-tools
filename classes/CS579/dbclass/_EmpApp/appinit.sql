-- Application Initialiation Script

prompt *** Application Initialization Starting

-- Set up user path definitions
-- Assumes a is defined as the path to the root application directory
define u = &a./UserOps

-- User operations that execute PL/SQL code start by setting feedback
-- off to avoid noise about commits and correct execution
-- and then finish by setting feedback back on
-- That is controlled by the following definitions
define startset = 'feedback off'
define finishset = 'feedback on'

-- initialize the middle tier (e.g. session variables)
@&u/_init

prompt *** Application Initialization Complete
