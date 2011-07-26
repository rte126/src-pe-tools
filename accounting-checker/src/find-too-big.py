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
    ts = int(time.mktime(time.strptime(t2,"%Y %b %d %H:%M:%S")))
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
    self.id = msg.getSessionId()
    self.timestamp = None
    self.data = { "start":None, "stop":None, "interim0":None, "interim1":None }
  def addMessage(self,msg):
    #logger.debug( "status = " + msg.getStatus() )
    if(msg.isStatus("start")):
      logger.debug( "adding start message..." )
      self.data["start"] = msg
      # session time stamp is the time stamp of the start message
      self.timestamp = msg.getSessionTimestamp()
    else:
      if( not self.timestamp ):
        self.timestamp = msg.getSessionTimestamp()
      if (msg.isStatus("stop")):
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
  def getLastInterim(self):
      # there could be stop only session
      return ( self.data["interim1"] or self.data["interim0"] or None )
  def __str__(self):
    returnstr = "Session %s at %d" % ( self.id, self.timestamp )
    for i in [ "start", "interim0", "interim1", "stop"]:
        if self.data[i]:
          returnstr += "    %s" % self.data[i].print1()
    return returnstr
  def find(msg):
    # AccountingMessage
    id = msg.getSessionId()
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
  id, sessions[]
  """
  stations = {}
  def __init__(self,msg):
    self.id = msg.getStationId()
    self.sessions = []
  def addSession(self,session):
    if not session in self.sessions: 
      self.sessions.append( session )
    logger.debug( "sessions = " % self.sessions )
  def __str__(self):
    return "(%s): %s" % ( self.id,self.sessions )
  def find(msg):
    # AccountingMessage
    id = msg.getStationId()
    if( CallingStation.stations.has_key(id) ):
      return CallingStation.stations[id]
    myStation = CallingStation(msg)
    CallingStation.stations[id] = myStation
    return myStation
  find = Callable(find)
  def list_all():
    global logger
    from operator import itemgetter
    for k,v in CallingStation.stations.iteritems():
      n = len(v.sessions)
      logger.debug("station: %s has %d sessions:" % (k,n ))
      newlist = sorted(v.sessions, key=lambda aaa: aaa.timestamp )
      session_0 = newlist[0]
      for s in newlist[1:]:
          lastinterim = session_0.getLastInterim()
          firstinterim = s.data["interim0"]
          if( lastinterim and firstinterim and lastinterim.isSmallerNonzeroThan( firstinterim ) ):
              logger.info("WARNING: bigger first-interim %s than last-interim %s" % (firstinterim.getInOutOctets(),lastinterim.getInOutOctets()))
          session_0 = s    
          
      #for s in v.sessions:
      #  logger.debug("    %s" % s)
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
    self.d = {}
    for i in range(len(items)):
        self.d[AccountingMessage.header[i]] = items[i]
  def associateSession(self):
    session = AccountingSession.find(self)
    session.addMessage(self)
    # INFO print session
    station = CallingStation.find(self)
    station.addSession(session)
  def getSessionId(self):
      return self.d["SessionId"]
  def getStationId(self):
      return self.d["Calling Station ID"]
  def getStatus(self):
      return self.d["Accounting Status"]
  def isStatus(self,givenStr):
    return self.getStatus() == givenStr
  def getTimestamp(self):
      return timestampFromAsctime(self.d["Timestamp"])
  def getSessionTime(self):
      return int(self.d["Session Time"])
  def getSessionTimestamp(self):
      if(self.getStatus() == "start"):
          return self.getTimestamp()
      return ( self.getTimestamp() - self.getSessionTime() ) 
  def getInOctets(self):
      return int(self.d["Input Octets"])
  def getOutOctets(self):
      return int(self.d["Output Octets"])
  def isSmallerThan(self,another):
    if(self.getOutOctets() <= another.getOutOctets() ):
      return True
    if(self.getInOctets() <= another.getInOctets() ):
      return True
    return False
  def isSmallerNonzeroThan(self,another):
    if( ( self.getOutOctets() != 0 ) and ( self.getOutOctets() <= another.getOutOctets() ) ):
      return True
    if( ( self.getInOctets() != 0 ) and ( self.getInOctets() <= another.getInOctets() ) ):
      return True
    return False
  def getInOutOctets(self):
    return "%d/%d" % ( self.getInOctets(), self.getOutOctets())
  def __str__(self):
    return "AccountingMessage: status=%s, time=%s" % ( self.getStatus(), self.getTimestamp() )
  def print1(self):
    if self.isStatus("start") :
        return "started at %s" % self.getTimestamp()
    if self.isStatus("interim"):
        return "interim at %s with in/out %d/%d" % ( self.getSessionTime(), self.getInOctets(), self.getOutOctets() )
    if self.isStatus("stop"):
        return "stopped at %s with in/out %d/%d" % ( self.getSessionTime(), self.getInOctets(), self.getOutOctets() )
    return "WARNING - unknown status (%s)" % self.getStatus()
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
