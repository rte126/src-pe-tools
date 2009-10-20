-- Util Subsystem
-- Copyright © Ellis Cohen 2002-2005

-- procedures/functions are not application-specific
-- they are for general utility use so are not packaged

--------------------

-- adds blanks to the left of the string to make it the specified
-- number of characters.
-- Uses chr(160) instead of actual blanks to ensure it is not trimmed
-- when displayed at the beginning of a line
create or replace function blpad( str varchar, len int ) return varchar is
begin
   return lpad( str, len, chr(160) );
end;
/

--------------------

-- raises the specified error if tst is true
create or replace procedure condraise( tst boolean, num int, msg varchar ) is
begin
   if tst then
      raise_application_error( num, msg );
   end if;
end;
/

--------------------

-- raises the specified error if num is not null
create or replace function tstraise( num int, msg varchar ) return varchar is
begin
   if num is not null then
      raise_application_error( num, msg );
   end if;
   return NULL;
end;
/

--------------------

-- if tst is true, rolls back & raises the error, else commits
create or replace procedure cond_autonomous_raise( tst boolean, num int, msg varchar ) is
begin
   if tst then
      rollback;
      raise_application_error( num, msg );
   end if;
   commit;
end;
/

--------------------

-- returns num as a string; returning 'NULL' if num is NULL
create or replace function to_str( num int ) return varchar is
begin
  if num is null then
     return 'NULL';
  end if;
  return to_char( num );
end;
/

--------------------

-- returns the identity of the current database user
create or replace function sysuser return varchar is
   usr varchar(128);
begin
   select USER into usr FROM DUAL;
   return usr;
end;
/

--------------------

-- returns a refcursor with one column named " " and no rows
create or replace function emptycursor return sys_refcursor is
   rc sys_refcursor;
begin
   open rc for select '' AS " " from dual where 1 = 2;
   return rc;
end;
/



