-- Display Subsystem
-- Copyright © Ellis Cohen 2002-2005

-- procedures/functions are not application-specific
-- they are for general utility use so are not packaged

--------------------

create or replace package display_control as
  pr_disabled int;
  procedure enable_print;  
  procedure disable_print;
end;
/

create or replace package body display_control as
  procedure enable_print is begin pr_disabled := null; end;  
  procedure disable_print is begin pr_disabled := 1; end;
end;
/

--------------------

-- prints a line of output
create or replace procedure pr( str varchar ) is
begin
  if display_control.pr_disabled is null then
    DBMS_OUTPUT.PUT( str );
  end if;
end;
/

--------------------

-- prints a line of output, without the ending end-of-line
create or replace procedure pl( str varchar ) is
begin
  if display_control.pr_disabled is null then
    DBMS_OUTPUT.PUT_LINE( str );
  end if;
end;
/

--------------------

-- prints a number with specified # of decimals points
create or replace procedure plnum( num number, pts number ) is
begin
  if display_control.pr_disabled is null then
    DBMS_OUTPUT.PUT_LINE( round(num,pts) );
  end if;
end;
/

--------------------

-- prints a number with 2 decimals points
create or replace procedure plnum2( num number ) is
begin
  if display_control.pr_disabled is null then
    DBMS_OUTPUT.PUT_LINE( round(num,2) );
  end if;
end;
/

--------------------

-- prints out an error message

create or replace procedure plerrmsg( errmsg varchar2 ) is
begin
  DBMS_OUTPUT.PUT_LINE( '--> ERROR: ' || errmsg );
end;
/

--------------------

-- prints out the first line of the textual part of the current error message
create or replace procedure plerr is
  endpos int;
  startpos int;
  len int;
  str varchar2(400);
begin
  startpos := 2 + instr( SQLERRM, ':' );
  endpos := instr( SQLERRM, chr(10), startpos );
  len := endpos - startpos;
  if endpos = 0 then
    str := substr( SQLERRM, startpos );
  else
    str := substr( SQLERRM, startpos, len );
  end if;
  plerrmsg( str );
end;
/

--------------------

-- prints out the current error message and rolls back
create or replace procedure doerr is
begin
  plerr();
  rollback;
end;
/


--------------------

-- prints out the current error message and rolls back
create or replace procedure doerrmsg( errmsg varchar ) is
begin
  plerrmsg( errmsg );
  rollback;
end;
/



