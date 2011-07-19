#!/usr/bin/python

"""
2011/7/16 ckim - started working

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

class Callable:
  def __init__(self,a):
    self.__call__ = a

class AccountingSession:
  """
  data[1]
  id, start, interim0, interim1, stop
  """
  sessions = {}
  def __init__(self,msg):
    self.id = msg.data[1]
    self.data = {}
    self.data["start"] = None
    self.data["stop"] = None
    self.data["interim0"] = None
    self.data["interim1"] = None
  def addMessage(self,msg):
    logger.debug( "status = " + msg.getStatus() )
    if(msg.isStatus("start")):
      logger.debug( "adding start message..." )
      self.data["start"] = msg
    elif (msg.isStatus("stop")):
      logger.debug(  "adding stop message..." )
      self.data["stop"] = msg
    elif (msg.isStatus("interim")):
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
    id = msg.getSessionId()
    if( AccountingSession.sessions.has_key(id) ):
      return AccountingSession.sessions[id]
    sess = AccountingSession(msg)
    AccountingSession.sessions[id] = sess
    return sess
  find = Callable(find)
  def listall():
    global logger
    for i in AccountingSession.sessions:
      logger.debug( i )
  listall = Callable(listall)

class CallingStation:
  """
  data[2]
  id, accountingsessions[]
  """
  stations = {}
  def __init__(self,msg):
    self.id = msg.getStationId()
    self.sessions = []
  def addSession(self,session):
    self.sessions.append( session )
  def __str__(self):
    return "(" + self.id + ")"
  def find(msg):
    # AccountingMessage
    id = msg.getStationId()
    if( CallingStation.stations.has_key(id) ):
      return CallingStation.stations[id]
    myStation = CallingStation(msg)
    CallingStation.stations[id] = myStation
    return myStation
  find = Callable(find)
	
	
class AccountingMessage:
  """
  A line of accounting log
  """
  def __init__(self,data):
    self.data = data.split(",");
  def associateSession(self):
    session = AccountingSession.find(self)
    session.addMessage(self)
    # INFO print session
    station = CallingStation.find(self)
    station.addSession(session)
  def getStatus(self):
    return self.data[0]
  def isStatus(self,givenStr):
    return self.data[0] == givenStr
  def getSessionId(self):
    return self.data[1]
  def getStationId(self):
    return self.data[2]
  def getTimestamp(self):
    return self.data[3]
  def getInputOctets(self):
    return self.data[8]
  def getOutputOctets(self):
    return self.data[10]
  def getSessionTime(self):
    return self.data[12]
  def __str__(self):
    return "AccountingMessage: status=%s, time=%s" % ( self.getStatus(), self.getTimestamp() )
  def print1(self):
    if self.isStatus("start") :
        return "started at %s" % self.getTimestamp()
    if self.isStatus("interim"):
        return "interim at %s with in/out %s/%s" % ( self.getSessionTime(), self.getInputOctets(), self.getOutputOctets() )
    if self.isStatus("stop"):
        return "stopped at %s with in/out %s/%s" % ( self.getSessionTime(), self.getInputOctets(), self.getOutputOctets() )
    if self.isStatus("Accounting Status"):
        return "header"
    return "WARNING - unknown status (%s)" % self.getStatus()
  def readline(fd):
    global logger
    # TODO: header to variable conversion
    aMessage = AccountingMessage( fd.readline() )
    logger.debug( "message read: %s" % aMessage )
    aMessage.associateSession()
  readline = Callable(readline)



def main():
  import sys
  global logger
  logger = logging.getLogger('accounting-checker')
  #logger.setLevel(logging.DEBUG)
  logger.setLevel(logging.INFO)
  ch = logging.StreamHandler()
  ch.setLevel(logging.DEBUG)
  formatter = logging.Formatter('%(asctime)s [%(name)s] [%(levelname)s] %(message)s')
  ch.setFormatter(formatter)
  logger.addHandler(ch)
  fd = None
  if(len(sys.argv) == 1 ):
    logger.info( "use stdin")
    while 1:
      AccountingMessage.readline(sys.stdin)
  else:
    for afile in sys.argv[1:]:
      logger.info("reading file : %s", afile)
      fd = open(afile,"r")
      for i in range(0,10):
        AccountingMessage.readline(fd)
  AccountingSession.listall()


if __name__ == "__main__":
  main()
