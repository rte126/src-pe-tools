-- ShowDeptEmps
-- Show the names & jobs of all employees who work in the user's department

SELECT ename, job FROM Emps
   WHERE deptno = (SELECT deptno FROM Emps WHERE empno = :curuser)
   ORDER BY ename;


