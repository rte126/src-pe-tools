-- Team: Smiling Solo
-- Project: Patch Request Application


prompt *** Building Patch Request System

-- drop tables

EXECUTE DropTable( 'PatchRequests' )
EXECUTE DropTable( 'Customers' )
EXECUTE DropTable( 'Engineers' )
EXECUTE DropTable( 'TacSupports' )
EXECUTE DropTable( 'Developers' )
EXECUTE DropTable( 'Patches' )
EXECUTE DropTable( 'Branches' )

-- create tables

CREATE TABLE Branches (
   bid    number(4)  PRIMARY KEY CHECK( bid > 1 ),
   bname  varchar(30) NOT NULL,
   frs    date NOT NULL,
   eoe    date NOT NULL,
   eol    date NOT NULL,
   CONSTRAINT Eoe_After_Frs CHECK( eoe > frs ),
      -- EOE should be later than FRS
   CONSTRAINT Eol_After_Eoe CHECK( eol > eoe )
      -- EOL should be later than EOE
);

CREATE TABLE Patches (
   pid    number(4),
   bid    number(4)  CONSTRAINT Patches_BranchesRef REFERENCES Branches,
   pname  varchar(30) NOT NULL,
   tdate    date,
   rdate    date,
   PRIMARY KEY( bid, pid )
);

CREATE TABLE Customers (
   cid    number(4)  PRIMARY KEY CHECK( cid > 1 ),
   cname  varchar(30) NOT NULL,
   revenue number(10)
);

CREATE TABLE Engineers (
   engid    number(4)  PRIMARY KEY CHECK( engid > 1 ),
   ename  varchar(30) NOT NULL,
   erole  char(1)    CHECK( erole in ( 'T', 'D' ) )
);

CREATE TABLE Developers (
   engid    number(4)  PRIMARY KEY CONSTRAINT Developers_EngineersRef REFERENCES Engineers,
   specialty  varchar(30) NOT NULL
);

CREATE TABLE TacSupports (
   engid    number(4)  PRIMARY KEY CONSTRAINT TacSupports_EngineersRef REFERENCES Engineers,
   region  varchar(30) NOT NULL
);


CREATE TABLE PatchRequests (
   prid    number(4)  PRIMARY KEY CHECK( prid > 1 ),
   priority number(4),
   bugid   varchar(10),
      -- this might have external linke in the future
   tdate   date,
   edate   date,
   cid     number(4) CONSTRAINT PatchReq_CustomersRef REFERENCES Customers,
   pengid  number(4) CONSTRAINT PatchReq_TacSupportsRef REFERENCES TacSupports(engid),
   dengid  number(4) CONSTRAINT PatchReq_DevelopersRef REFERENCES Developers(engid),
   bid  number(4),
   pid  number(4),
   CONSTRAINT PatchReq_PatchesRef FOREIGN KEY(bid,pid) REFERENCES Patches(bid,pid)
);



prompt *** Patch Request System Built
