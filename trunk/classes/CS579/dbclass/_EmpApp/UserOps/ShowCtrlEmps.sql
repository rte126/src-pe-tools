-- ShowCtrlEmps
-- Show the employee name & number, department, job, and sal
-- and manager name of all employees whose salary can be changed by the user

WITH CtrlEmpView AS (
   SELECT * FROM Emps
      WHERE (empno <> :curuser)
        AND (  ((SELECT job FROM Emps WHERE empno = :curuser) = 'PRESIDENT')
            OR ((SELECT job FROM Emps WHERE empno = :curuser) = 'DEPTMGR') AND 
                (deptno = (SELECT deptno FROM Emps WHERE empno = :curuser))) ),
CtrlEmpMgrView AS (
   SELECT e.empno AS eno, e.ename, e.job AS ejob, e.deptno AS edno,
          e.sal AS esal,
          m.empno AS mno, m.ename AS mname, m.job AS mjob, m.deptno AS mdno
      FROM CtrlEmpView e LEFT JOIN Emps m ON e.mgr = m.empno),
CtrlDeptEmpMgrView AS (
   SELECT eno, ename, ejob, edno, ed.dname AS ednam, ed.loc AS edloc, esal,
          mno, mname, mjob, mdno, md.dname AS mdnam, md.loc AS mdloc 
      FROM CtrlEmpMgrView e, Depts ed, Depts md
      WHERE edno = ed.deptno AND mdno = md.deptno )
SELECT ednam, ename, eno, ejob, esal, mname 
   FROM CtrlDeptEmpMgrView
   ORDER BY ednam, ename;



