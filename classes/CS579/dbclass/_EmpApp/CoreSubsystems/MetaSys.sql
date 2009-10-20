-- Meta Subsystem
-- Copyright © Ellis Cohen 2002-2005

-- procedures/functions are not application-specific
-- they are for general utility use so are not packaged

-----------------------------------------------------------
-- Meta Information
-----------------------------------------------------------

-- View showing all user meta tables
CREATE OR REPLACE VIEW UserMeta AS
   SELECT table_name, substr(comments,1,90) as description from dict
   WHERE table_name LIKE 'USER_%'
   ORDER BY table_name;

-----------------------------------------------------------
-- All User Objects
-----------------------------------------------------------

-- View showing all user object
CREATE OR REPLACE VIEW UserObjects AS
   SELECT substr(object_name,1,30) as objnam, object_type as objtyp
   FROM user_objects
   ORDER BY objtyp, objnam;

-----------------------------------------------------------
-- Constraints
-----------------------------------------------------------

-----------------------------------------------------------
-- Packages
-----------------------------------------------------------

-----------------------------------------------------------
-- Procedures & Functions
-----------------------------------------------------------

-----------------------------------------------------------
-- Roles
-----------------------------------------------------------

create or replace procedure DropRole( aRole varchar ) is
  sqlstr varchar2(40) := 'drop role ' || aRole;
begin
  execute immediate sqlstr;
exception
  when others then null;
end;
/

-----------------------------------------------------------
-- Sequences
-----------------------------------------------------------

-- View showing all sequences
CREATE OR REPLACE VIEW UserSequences AS
   SELECT sequence_name, last_number FROM User_Sequences;

--------------------

-- Drop a sequence
CREATE OR REPLACE PROCEDURE DropSequence( seq varchar ) IS
  sqlstr varchar(128) := 'drop sequence ' || seq;
BEGIN
  EXECUTE IMMEDIATE sqlstr;
EXCEPTION
  WHEN OTHERS THEN NULL;
END;
/

--------------------

-- Drop all sequences
CREATE OR REPLACE PROCEDURE DropAllSequences IS
BEGIN
   FOR r IN (SELECT * FROM UserSequences) LOOP
      DropSequence( r.sequence_name );
   END LOOP;
END;
/

-----------------------------------------------------------
-- Tables
-----------------------------------------------------------

-- View showing all tables
CREATE OR REPLACE VIEW UserTables AS
   SELECT objnam FROM UserObjects
   WHERE objtyp = 'TABLE'
   ORDER BY objnam;

--------------------

-- Drop a table
CREATE OR REPLACE PROCEDURE DropTable( tbl varchar ) is
  sqlstr varchar(128) := 'drop table ' || tbl || ' cascade constraints';
BEGIN
  EXECUTE IMMEDIATE sqlstr;
EXCEPTION
  WHEN OTHERS THEN NULL;
END;
/

--------------------

-- Drop all tables
CREATE OR REPLACE PROCEDURE DropAllTables IS
BEGIN
   FOR r IN (SELECT * FROM UserTables) LOOP
      DropTable( r.objnam );
   END LOOP;
END;
/

-----------------------------------------------------------
-- Triggers
-----------------------------------------------------------

-- View showing all triggers
CREATE OR REPLACE VIEW UserTriggers AS
   SELECT objnam FROM UserObjects
   WHERE objtyp = 'TRIGGER'
   ORDER BY objnam;

--------------------

-- Drop a trigger
CREATE OR REPLACE PROCEDURE DropTrigger( trgr varchar ) IS
  sqlstr varchar(128) := 'drop trigger ' || trgr;
BEGIN
  EXECUTE IMMEDIATE sqlstr;
EXCEPTION
  WHEN OTHERS THEN NULL;
END;
/

--------------------

-- Drop all triggers
CREATE OR REPLACE PROCEDURE DropAllTriggers IS
BEGIN
   FOR r IN (SELECT * FROM UserTriggers) LOOP
      DropTrigger( r.objnam );
   END LOOP;
END;
/

-----------------------------------------------------------
-- Views
-----------------------------------------------------------

create or replace procedure DropView( vw varchar ) is
  sqlstr varchar2(40) := 'drop view ' || vw;
begin
  execute immediate sqlstr;
exception
  when others then null;
end;
/


-----------------------------------------------------------
-- ALL 
-----------------------------------------------------------

-- Drop all user objects (triggers & tables for now)
CREATE OR REPLACE PROCEDURE DropAll IS
BEGIN
   DropAllTriggers();
   DropAllTables();
   DropAllSequences();
END;
/

