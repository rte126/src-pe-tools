-- Team: Smiling Solo
-- Project: Patch Request Application


prompt *** Building Employee Database System

-- drop tables

EXECUTE DropTable( 'Emps' )
EXECUTE DropTable( 'Depts' )

-- create tables

CREATE TABLE Emps (
   empno  number(4)  PRIMARY KEY  CHECK( empno > 1 ),
   ename  varchar(30) NOT NULL,
   sal  number(8,2)
       CONSTRAINT Emps_Sal_Check CHECK( sal >= 100 ),
   job  varchar(12)  NOT NULL,
   street  varchar(40),
   city  varchar(20),
   state  char(2),
   zip  char(5),
   deptno  number(3)  NOT NULL,
       -- CONSTRAINT Emps_DeptnoRef REFERENCES Depts 
       -- Referential circularity
       -- Depts not yet defined; add later after Depts is defined
   mgr  number(4)  CONSTRAINT Emps_MgrRef REFERENCES Emps,
   ispublic char(1) DEFAULT 'F'  CHECK( isPublic IN ('T', 'F') ), 
   CONSTRAINT Emps_NoManageSelf CHECK( empno <> mgr ),
      -- NoManageSelf is not necessary if ManageCirc is enforced 
   CONSTRAINT Emps_NoPrezMgr CHECK( job <> 'PRESIDENT' OR mgr IS NULL ),
      -- NoPrezMgr: enforces that President doesn't have a manager
   CONSTRAINT Emps_EmpMgr CHECK( job = 'PRESIDENT' OR mgr IS NOT NULL ) );
      -- EmpMgr: enforces that every employee other than the President
      -- does have a manager

CREATE TABLE Depts(
   deptno NUMBER(3)  PRIMARY KEY  CHECK(deptno > 9),
   dname  VARCHAR(20)  UNIQUE  NOT NULL,
   loc    VARCHAR(15),
   actmgr NUMBER(4) CONSTRAINT Depts_ActmgrRef REFERENCES Emps
);

-- In some cases, there are circular references.  For example
-- The Emps table has deptno which references Depts
-- The Depts table has actmgr which references Emps.
-- The only solution is to *COMMENT OUT* one of the REFERENCES clauses
-- and then add it here after the tables are all built

ALTER TABLE Emps ADD CONSTRAINT Emps_DeptnoRef
   FOREIGN KEY(deptno) REFERENCES Depts;
     
----------------------------------------------------------

-- drop all triggers (IMPORTANT)

execute DropAllTriggers();

-- drop all sequences & define any sequences here
     
----------------------------------------------------------

-- now, define any views, procedures, functions, packages, ...

-- Returns the employee's role based on their job
-- Returns NULL if there is no such employee or they don't have a job

CREATE OR REPLACE FUNCTION GetRole( anEmpno int ) RETURN varchar IS
   theJob varchar(30);
   theRole varchar(30);
   knt int;
BEGIN
   SELECT job INTO theJob FROM Emps WHERE empno = anEmpno;
   CASE theJob
      WHEN 'PRESIDENT' THEN theRole := 'President';
      WHEN 'DEPTMGR' THEN theRole := 'DeptMgr';
      ELSE
        SELECT count(*) INTO knt FROM Emps
            WHERE mgr = anEmpno;
        IF knt > 0 THEN theRole := 'Manager';
        ELSIF theJob IS NOT NULL THEN theRole := 'Employee';
        END IF;
      END CASE;
   RETURN theRole;
EXCEPTION WHEN OTHERS THEN
   RETURN NULL;
END;
/
-- Manifest view for OneDeptMgr
-- For each department, provides count of #employees, dept actmgrs & deptmgrs

CREATE OR REPLACE VIEW DeptMgrsView AS 
   SELECT deptno, 
     (SELECT count(*) FROM Emps e
         WHERE e.deptno = d.deptno) AS eknt,
     (SELECT count(*) FROM Emps e
         WHERE e.deptno = d.deptno AND e.empno = d.actmgr) AS aknt,
     (SELECT count(*) FROM Emps e
         WHERE e.deptno = d.deptno AND e.job = 'DEPTMGR') AS mknt
   FROM Depts d;

-- Manifest view for DeptMgrClerks
-- For each dept, has # of clerks (cknt)  & # of deptmgrs (mknt)

CREATE OR REPLACE VIEW DeptMgrClerksView AS 
   SELECT deptno,
      (SELECT count(*) FROM Emps e
         WHERE e.deptno = d.deptno AND e.job = 'CLERK') AS cknt,
      (SELECT count(*) FROM Emps e
         WHERE e.deptno = d.deptno AND e.job = 'DEPTMGR') AS mknt
   FROM Depts d;

-- Manifest view for ManageLoc
-- for each employee with a mgr, indicates
-- their job, dept and their dept location
-- their mgr, and their mgr's job, dept, and dept location

CREATE OR REPLACE VIEW ManageLocView AS
   WITH EmpView AS (
      SELECT empno, job, mgr, deptno, loc FROM Emps NATURAL JOIN Depts)
   SELECT e.empno, e.job AS ejob, e.deptno AS edno, e.loc AS eloc,
      e.mgr, m.job AS mjob, m.deptno AS mdno, m.loc AS mloc
      FROM EmpView e JOIN EmpView m ON e.mgr = m.empno;

-- Manifest View for SetActMgr
-- For each dept, determines 
--   current acting mgr (NULL if actmgr in a different dept)
--   # of dept mgrs
--   one employee in the department with the highest salary

CREATE OR REPLACE VIEW DeptActMgrView AS
   SELECT deptno, actmgr,
      (SELECT empno FROM Emps e
          WHERE e.empno = actmgr AND e.deptno = d.deptno) AS curactmgr,
      (SELECT count(*) FROM Emps e
          WHERE e.deptno = d.deptno AND e.job = 'DEPTMGR') AS mknt,
      (SELECT max(empno) FROM Emps e
          WHERE e.deptno = d.deptno AND
                e.sal = 
                  (SELECT max(sal) FROM Emps ee WHERE ee.deptno = d.deptno))
          AS ehighsal
   FROM Depts d;

prompt *** Employee Database System Built
