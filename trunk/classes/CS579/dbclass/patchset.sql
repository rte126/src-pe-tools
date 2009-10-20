-- run setup.sql, which is in the same folder as this file
@@setup

-- run appset.sql, with Emp as its script parameter
-- that is, when appset.sql runs, &1 will have the value Emp
@@appset Patch

