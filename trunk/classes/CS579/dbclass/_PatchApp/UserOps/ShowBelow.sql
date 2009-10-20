-- ShowBelow
-- Show the employee name & number, department, job, and manager's name & dept of all the employees who are hierarchically under the user

WITH BelowEmpView AS (
   SELECT empno AS eno, ename, job AS ejob, deptno AS edno, mgr AS mno, 
          PRIOR ename AS mname, PRIOR job AS mjob, PRIOR deptno AS mdno
      FROM Emps
      WHERE empno <> :curuser
      START WITH empno = :curuser
      CONNECT BY mgr = PRIOR empno),
BelowDeptEmpView AS (
   SELECT eno, ename, ejob, edno, ed.dname AS ednam, ed.loc AS edloc,
          mno, mname, mjob, mdno, md.dname AS mdnam, md.loc AS mdloc 
      FROM BelowEmpView e, Depts ed, Depts md
      WHERE edno = ed.deptno AND mdno = md.deptno)
SELECT ename, eno, ednam, ejob, mname  FROM BelowDeptEmpView
   ORDER BY ename;


