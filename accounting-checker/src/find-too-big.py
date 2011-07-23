#!/usr/bin/python

"""
2011/7/16 ckim - started working
2011/7/19 ckim - header adaption added

Accounging columns;
Accounting Status,SessionId,Calling Station ID,Timestamp,User IP Address,NAS IP,NAS port,User ID,Input Octets,Input Packets,Output Octets,Output Packets,Session Time,Terminate Cause
interim,Acct1:g019yiww:1307944801035:30450,CAF1147234419,Fri Jul 01 00:00:01 JST 2011,180.0.123.120,118.23.8.91,0,g019yiww@bff.ocn.ne.jp,88874781,1088111,631342565,1208743,1501200,

Track by Calling Station ID and SessionId

Warning:
  if interim count drops
  stop is smaller than interim
  stop missing
  first interim larger than last interim (or stop)
  first interim larget than PRESET value 
  interim without start

readline and add
  start
  stop
  the first interim
  the last interim

read stdin witout args
"""

import logging
logger = None

def timestampFromAsctime(asctime):
    """
    Fri Jul 01 00:00:01 JST 2011
    """
    import time
    t1 = asctime.split()
    t2 = " ".join([ t1[5], t1[1], t1[2], t1[3] ])
    ts = time.mktime(time.strptime(t2,"%Y %b %d %H:%M:%S"))
    return ts

class Callable:
  def __init__(self,a):
    self.__call__ = a

class AccountingSession:
  """
  data[1]
  id, timestamp, start, interim0, interim1, stop
  """
  sessions = {}
  def __init__(self,msg):
    self.id = msg.sessionId
    self.data = { "start":None, "stop":None, "interim0":None, "interim1":None }
  def addMessage(self,msg):
    logger.debug( "status = " + msg.status )
    if(msg.isStatus("start")):
      logger.debug( "adding start message..." )
      self.data["start"] = msg
      self.timestamp = timestampFromAsctime(msg.timestamp)
    elif (msg.isStatus("stop")):
      logger.debug(  "adding stop message..." )
      self.data["stop"] = msg
      # CHECKER: ERROR if stop is less than last interim
      # lastInterim = self.data["interim1"] or self.data["interim0"]
      # if(lastInterim and self.data["stop"].isSmallerThan(lastInterim) ):
      #  logger.error( "stop: %s at %s: smaller accounting (%s) than interim (%s)" % (self.id,self.data["stop"].timestamp,self.data["stop"].getInOutOctets(), lastInterim.getInOutOctets()))
      # CHECKER : ERROR if stop has all zero
      # if( ( msg.inOctets == 0 ) and ( msg.outOctets == 0 ) ):
      #   logger.error( "stop has all zero: NAS_IP=%s, sessionId=%s at %s" % (msg.nasIp, msg.sessionId,msg.timestamp) )
    elif (msg.isStatus("interim")):
      # CHECKER: ERROR if the first interim is too big
      # if( msg.sessionTime < 4000 ):
      #  logger.debug("interim: ts=%d, in/out=%s" % ( msg.sessionTime, msg.getInOutOctets()))
      #  if( ( msg.inOctets > 450000000 ) or ( msg.outOctets > 450000000 )):
      #    logger.error("first-interim: %s at %s: too big first interim %s" % (self.id,msg.sessionTime,msg.getInOutOctets()))
      if( self.data["interim0"] ):
        logger.debug( "adding interim1..." )
        self.data["interim1"] = msg
      else:
        logger.debug( "adding interim0..." )
        self.data["interim0"] = msg
    logger.debug(  "added " + self.__str__() )
  def __str__(self):
    returnstr = "Session " + self.id
    for i in [ "start", "interim0", "interim1", "stop"]:
        if self.data[i]:
          returnstr += "    %s\n" % self.data[i].print1()
    return returnstr
  def find(msg):
    # AccountingMessage
    id = msg.sessionId
    if( AccountingSession.sessions.has_key(id) ):
      return AccountingSession.sessions[id]
    session = AccountingSession(msg)
    AccountingSession.sessions[id] = session
    return session
  find = Callable(find)
  def listall():
    global logger
    for i in AccountingSession.sessions:
      logger.info( i )
  listall = Callable(listall)

class CallingStation:
  """
  data[2]
  id, accountingsessions[]
  """
  stations = {}
  def __init__(self,msg):
    self.id = msg.stationId
    self.sessions = []
  def addSession(self,session):
    if not session in self.sessions: 
      self.sessions.append( session )
    logger.debug( "sessions = " % self.sessions )
  def __str__(self):
    return "(%s): %s" % ( self.id,self.sessions )
  def find(msg):
    # AccountingMessage
    id = msg.stationId
    if( CallingStation.stations.has_key(id) ):
      return CallingStation.stations[id]
    myStation = CallingStation(msg)
    CallingStation.stations[id] = myStation
    return myStation
  find = Callable(find)
  def list_all():
    global logger
    for k,v in CallingStation.stations.iteritems():
      logger.debug("station: %s has" % k )
      for s in v.sessions:
        logger.debug("    session: %s" % s.id)
    logger.info("total %d stations, %d sessions" % (len(CallingStation.stations),len(AccountingSession.sessions)))
  list_all = Callable(list_all)
	
	
class AccountingMessage:
  """
  A line of accounting log
  Accounting Status,SessionId,Calling Station ID,Timestamp,User IP Address,NAS IP,NAS port,User ID,Input Octets,Input Packets,Output Octets,Output Packets,Session Time,Terminate Cause
  status,sesionId,stationId,timestamp,inOctets,outOctets,sessionTime
  STATUS,SESSIONID,STATIONID,TIMESTAMP,INOCTETS,OUTOCTETS,SESSIONTIME
  """
  
  def __init__(self,data):
    # TODO: EOF process
    items = data.split(",");
    logger.debug("AccountingMessage.__init__():items %s" % items)
    for i in range(len(items)):
        self.d[AccountingMessage.header[i]] = items[i]
    #self.status = items[AccountingMessage.STATUS]
    #self.sessionId = items[AccountingMessage.SESSIONID]
    #self.stationId = items[AccountingMessage.CALLINGSTATIONID]
    #self.nasIp = items[AccountingMessage.NASIP]
    #self.timestamp = items[AccountingMessage.TIMESTAMP]
    #if(self.status != "start"):
    #  self.sessionTime = int(items[AccountingMessage.SESSIONTIME])
    #if(self.status in ["stop", "interim"]):
    #  self.inOctets = int(items[AccountingMessage.INPUTOCTETS])
    #  self.outOctets = int(items[AccountingMessage.OUTPUTOCTETS])
    
  def associateSession(self):
    session = AccountingSession.find(self)
    session.addMessage(self)
    # INFO print session
    station = CallingStation.find(self)
    station.addSession(session)
  def isStatus(self,givenStr):
    return self.status == givenStr
  def isSmallerThan(self,another):
    if(self.outOctets < another.outOctets ):
      return True
    if(self.inOctets < another.inOctets ):
      return True
    return False
  def getInOutOctets(self):
    return "%d/%d" % ( self.inOctets, self.outOctets)
  def __str__(self):
    return "AccountingMessage: status=%s, time=%s" % ( self.status, self.timestamp )
  def print1(self):
    if self.isStatus("start") :
        return "started at %s" % self.timestamp
    if self.isStatus("interim"):
        return "interim at %s with in/out %d/%d" % ( self.sessionTime, self.inOctets, self.outOctets )
    if self.isStatus("stop"):
        return "stopped at %s with in/out %d/%d" % ( self.sessionTime, self.inOctets, self.outOctets )
    return "WARNING - unknown status (%s)" % self.status()
  def readline(fd):
    global logger
    aline = fd.readline().rstrip()
    # EOF check
    if( aline == "" ):
      return False
    aMessage = AccountingMessage( aline )
    logger.debug( "message read: %s" % aMessage )
    aMessage.associateSession()
    return True
  readline = Callable(readline)
  def read_header(fd):
    global logger
    # header to variable conversion
    aline = fd.readline().rstrip()
    # EOF check
    if( aline == "" ):
      return False
    AccountingMessage.header = aline.split(",")
    #data = aline.split(",")
    #for i in range(len(data)):
    #  logger.debug( "header[%d]='%s'" % (i,data[i]) )
    #  if(data[i] == "Accounting Status"):
    #      AccountingMessage.STATUS = i
    #  elif(data[i] == "SessionId"):
    #      AccountingMessage.SESSIONID = i
    #  elif(data[i] == "Calling Station ID" ):
    #      AccountingMessage.CALLINGSTATIONID = i
    #  elif(data[i] == "Timestamp" ):
    #      AccountingMessage.TIMESTAMP = i
    #  elif(data[i] == "NAS IP" ):
    #      AccountingMessage.NASIP = i
    #  elif(data[i] == "Input Octets" ):
    #      AccountingMessage.INPUTOCTETS = i
    #  elif(data[i] == "Output Octets" ):
    #      AccountingMessage.OUTPUTOCTETS = i
    #  elif(data[i] == "Session Time" ):
    #      AccountingMessage.SESSIONTIME = i
    #  elif(data[i] == "Terminate Cause" ):
    #      AccountingMessage.TERMINATECAUSE = i
    return True
  read_header = Callable(read_header)

def main():
  import sys
  global logger
  logger = logging.getLogger('accounting-checker')
  #logger.setLevel(logging.DEBUG)
  logger.setLevel(logging.INFO)
  ch = logging.StreamHandler()
  ch.setLevel(logging.DEBUG)
  #formatter = logging.Formatter('%(asctime)s [%(name)s] [%(levelname)s] %(message)s')
  formatter = logging.Formatter('[%(levelname)s] %(message)s')
  ch.setFormatter(formatter)
  logger.addHandler(ch)
  fd = None
  if(len(sys.argv) == 1 ):
    logger.info( "use stdin")
    AccountingMessage.read_header(sys.stdin)
    while 1:
      AccountingMessage.readline(sys.stdin)
  else:
    for afile in sys.argv[1:]:
      logger.info("reading file : %s", afile)
      fd = open(afile,"r")
      #for i in range(0,10):
      AccountingMessage.read_header(fd)
      while True:
        if( AccountingMessage.readline(fd) == False ):
          break
  CallingStation.list_all()
  logger.info("all the data read")
  #AccountingSession.listall()


if __name__ == "__main__":
  main()
