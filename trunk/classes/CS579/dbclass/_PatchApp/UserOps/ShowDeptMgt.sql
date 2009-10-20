-- ShowDeptMgt
-- Show the numbers, names, locations & 
--    current and acting mgrs of all departments

SELECT deptno, dname, loc, ename FROM (Emps NATURAL JOIN Depts)
  WHERE job = 'DEPTMGR' OR empno = actmgr
  ORDER BY dname;




