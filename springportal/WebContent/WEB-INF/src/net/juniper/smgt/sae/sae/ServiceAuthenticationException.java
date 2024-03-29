package net.juniper.smgt.sae.sae;


/**
* net/juniper/smgt/sae/sae/ServiceAuthenticationException.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from saeaccess.idl
* Sunday, February 1, 2009 10:24:16 PM EST
*/

public final class ServiceAuthenticationException extends org.omg.CORBA.UserException
{
  public net.juniper.smgt.sae.sae.SAET reason = null;
  public String userIp = null;
  public String serviceName = null;
  public String sessionName = null;
  public String message = null;

  public ServiceAuthenticationException ()
  {
    super(ServiceAuthenticationExceptionHelper.id());
  } // ctor

  public ServiceAuthenticationException (net.juniper.smgt.sae.sae.SAET _reason, String _userIp, String _serviceName, String _sessionName, String _message)
  {
    super(ServiceAuthenticationExceptionHelper.id());
    reason = _reason;
    userIp = _userIp;
    serviceName = _serviceName;
    sessionName = _sessionName;
    message = _message;
  } // ctor


  public ServiceAuthenticationException (String $reason, net.juniper.smgt.sae.sae.SAET _reason, String _userIp, String _serviceName, String _sessionName, String _message)
  {
    super(ServiceAuthenticationExceptionHelper.id() + "  " + $reason);
    reason = _reason;
    userIp = _userIp;
    serviceName = _serviceName;
    sessionName = _sessionName;
    message = _message;
  } // ctor

} // class ServiceAuthenticationException
