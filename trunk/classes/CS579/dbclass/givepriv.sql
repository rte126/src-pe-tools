-- you must connect as the super user

set feedback off

grant create indextype to &1;
grant create materialized view to &1;
grant create procedure to &1;
grant create role to &1;
grant create session to &1;
grant create sequence to &1;
grant create session to &1;
grant create table to &1;
grant create tablespace to &1;
grant unlimited tablespace to &1;
grant create trigger to &1;
grant create type to &1;
grant create view to &1;
grant create any directory to &1;

set feedback on






