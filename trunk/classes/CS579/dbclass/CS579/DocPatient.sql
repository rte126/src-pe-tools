set feedback off
set document off

/*
a) Write and test the SQL to create the three tables which correspond to this relational schema.  Make sure that it includes all the appropriate
¡¤	primary key constraints 
(NOTE: There are primary key constraints, but no explicit UNIQUE constraints)
¡¤	not null constraints
¡¤	and foreign key constraints (using references)
which are shown in the relational schema above.

? To get full credit, you must include the output from the spool file, showing successful creation of the tables.

? You must create a table before you refer to it or else you will get errors!

For extra credit, provide names for the constraints.

It is very useful to provide names for constraints, especially foreign key and check constraints.  If there's an error, this name is reported in error messages. (Primary key and null constraints can be named as well, but they're less likely to be violated).  

*/

-- hw3 2a

drop table patients;
drop table doctors;
drop table assistants;

create table assistants (
  ssno		number(10)		primary key,
  aname		varchar2(32)	not null
);


create table doctors (
  licenseauth	number(10),
  licenseno		number(10),
  docnam		varchar2(32)	not null,
  yrlicensed	number(2)		not null,
  ssno		number(10)		references assistants,
  primary key ( licenseauth, licenseno )
);

create table patients (
  patid		number(10)		primary key,
  name		varchar2(32)	not null,
  birthdate		date			not null,
  licenseauth	number(10)		not null,
  licenseno		number(10)		not null,
  assno		number(10)		references assistants(ssno),
  foreign key ( licenseauth, licenseno ) references doctors
);


-- hw3 2c
insert into assistants values(6001,'Assist A');
insert into doctors values(3030,8703001,'Dr. Nobody', 15, 6001);
insert into patients values(10003,'Nice Pati','3-Sep-1999',3030,8703001,6001);



/*
insert into dept( deptno, dname, loc )
values( 10, 'ACCOUNTING', 'NEW YORK' );
insert into dept( deptno, dname, loc )
values( 20, 'RESEARCH', 'DALLAS' );
insert into dept( deptno, dname, loc )
values( 30, 'SALES', 'CHICAGO' );
insert into dept( deptno, dname, loc )
values( 40, 'OPERATIONS', 'BOSTON' );

create table emp (
  empno	number(4)  primary key,
  ename	varchar2(10)  not null,
  job		varchar2(9),
  mgr		number(4)  references emp,
  hiredate	date,
  sal		number(7,2),
  comm		number(7,2),
  deptno	number(2)  references dept );

insert into emp( empno, ename, job, mgr, hiredate, sal, comm, deptno )
values( 7839, 'KING', 'PRESIDENT', null, '17-NOV-81', 5000, null, 10 );

insert into emp( empno, ename, job, mgr, hiredate, sal, comm, deptno )
values( 7566, 'JONES', 'MANAGER', 7839, '02-APR-81', 2975, null, 20 );
insert into emp( empno, ename, job, mgr, hiredate, sal, comm, deptno )
values( 7698, 'BLAKE', 'MANAGER', 7839, '01-MAY-81', 2850, null, 30 );
insert into emp( empno, ename, job, mgr, hiredate, sal, comm, deptno )
values( 7782, 'CLARK', 'MANAGER', 7839, '09-JUN-81', 2450, null, 10 );

insert into emp( empno, ename, job, mgr, hiredate, sal, comm, deptno )
values( 7788, 'SCOTT', 'ANALYST', 7566, '19-APR-87', 3000, null, 20 );
insert into emp( empno, ename, job, mgr, hiredate, sal, comm, deptno )
values( 7902, 'FORD', 'ANALYST', 7566, '03-DEC-81', 3000, null, 20 );

insert into emp( empno, ename, job, mgr, hiredate, sal, comm, deptno )
values( 7499, 'ALLEN', 'SALESMAN', 7698, '20-FEB-81', 1600, 300, 30 );
insert into emp( empno, ename, job, mgr, hiredate, sal, comm, deptno )
values( 7521, 'WARD', 'SALESMAN', 7698, '22-FEB-81', 1250, 500, 30 );
insert into emp( empno, ename, job, mgr, hiredate, sal, comm, deptno )
values( 7654, 'MARTIN', 'SALESMAN', 7698, '28-SEP-81', 1250, 1400, 30 );
insert into emp( empno, ename, job, mgr, hiredate, sal, comm, deptno )
values( 7844, 'TURNER', 'SALESMAN', 7698, '08-SEP-81', 1500, 0, 30 );

insert into emp( empno, ename, job, mgr, hiredate, sal, comm, deptno )
values( 7369, 'SMITH', 'CLERK', 7902, '17-DEC-80', 800, null, 20 );
insert into emp( empno, ename, job, mgr, hiredate, sal, comm, deptno )
values( 7876, 'ADAMS', 'CLERK', 7788, '23-MAY-87', 1100, null, 20 );
insert into emp( empno, ename, job, mgr, hiredate, sal, comm, deptno )
values( 7900, 'JAMES', 'CLERK', 7698, '03-DEC-81', 950, null, 30 );
insert into emp( empno, ename, job, mgr, hiredate, sal, comm, deptno )
values( 7934, 'MILLER', 'CLERK', 7782, '23-JAN-82', 1300, null, 10 );
*/

set feedback on

prompt *** Table Setup Completed



