set feedback off
set document off

/*
In general, you should NOT need to run this script

When Oracle is installed,
it creates EMP and DEPT tables in user SCOTT's schema.

ONLY Run this script ONLY if
a) You are not using Oracle, or 
b) SCOTT does not have the tables EMP and DEPT, or
c) the database already existed,
     and some other user damaged the tables so they have incorrect values, or
d) You are not using SCOTT

How can you find out if SCOTT's tables are incorrect?
If you type      SELECT * FROM EMP;
you should see the tuples

7369 SMITH      CLERK       7902 17-DEC-80        800                    20
7499 ALLEN      SALESMAN    7698 20-FEB-81       1600        300         30
7521 WARD       SALESMAN    7698 22-FEB-81       1250        500         30
7566 JONES      MANAGER     7839 02-APR-81       2975                    20
7654 MARTIN     SALESMAN    7698 28-SEP-81       1250       1400         30
7698 BLAKE      MANAGER     7839 01-MAY-81       2850                    30
7782 CLARK      MANAGER     7839 09-JUN-81       2450                    10
7788 SCOTT      ANALYST     7566 19-APR-87       3000                    20
7839 KING       PRESIDENT        17-NOV-81       5000                    10
7844 TURNER     SALESMAN    7698 08-SEP-81       1500          0         30
7876 ADAMS      CLERK       7788 23-MAY-87       1100                    20
7900 JAMES      CLERK       7698 03-DEC-81        950                    30
7902 FORD       ANALYST     7566 03-DEC-81       3000                    20
7934 MILLER     CLERK       7782 23-JAN-82       1300                    10

If you type      SELECT * FROM DEPT;
you should see the tuples

10 ACCOUNTING     NEW YORK
20 RESEARCH       DALLAS
30 SALES          CHICAGO
40 OPERATIONS     BOSTON

You DO NOT NEED to run this script if the tables have the correct values
*/

-- This may indicate an error if there is no EMP table currently.  IGNORE IT
drop table emp;

-- This may indicate an error if there is no DEPT table currently.  IGNORE IT
drop table dept;

create table dept (
  deptno	number(2)   primary key,
  dname	varchar2(14)     unique not null,
  loc		varchar2(13) );

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

set feedback on

prompt *** Table Setup Completed



