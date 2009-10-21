-- Team: Smiling Solo
-- Project: Patch Request Application

prompt *** Preparing to Populate

prompt *** Populating Depts

@&u/CreateDept 10 ACCOUNTING 'NEW YORK'
@&u/CreateDept 20 RESEARCH DALLAS
@&u/CreateDept 30 SALES CHICAGO
@&u/CreateDept 40 OPERATIONS BOSTON

prompt *** Populating Emps

-- disable mgr FK constraints so employees can be inserted in any order
ALTER TABLE Emps MODIFY CONSTRAINT Emps_MgrRef DISABLE;

@&u/AddEmp 7369 SMITH 800 CLERK 7902 20
@&u/AddEmp 7499 ALLEN 1600 SALESMAN 7698 30
@&u/AddEmp 7521 WARD 1250 SALESMAN 7698 30
@&u/AddEmp 7566 JONES 2975 DEPTMGR 7839 20
@&u/AddEmp 7654 MARTIN 1250 SALESMAN 7698 30
@&u/AddEmp 7698 BLAKE 2850 DEPTMGR 7839 30
@&u/AddEmp 7782 CLARK 2450 DEPTMGR 7839 10
@&u/AddEmp 7788 SCOTT 3000 ANALYST 7566 20
@&u/AddEmp 7839 KING 5000 PRESIDENT NULL 10
@&u/AddEmp 7844 TURNER 1500 SALESMAN 7698 30
@&u/AddEmp 7876 ADAMS 1100 CLERK 7788 20
@&u/AddEmp 7900 JAMES 950 CLERK 7698 30
@&u/AddEmp 7902 FORD 3000 ANALYST 7566 20
@&u/AddEmp 7934 MILLER 1300 CLERK 7782 10

-- re-enable mgr FK constraints which checks all mgr references
ALTER TABLE Emps MODIFY CONSTRAINT Emps_MgrRef ENABLE;

prompt *** Finished Populating




