package net.juniper.smgt.sae.sae;


/**
* net/juniper/smgt/sae/sae/UnknownServiceException.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from saeaccess.idl
* Sunday, February 1, 2009 10:24:16 PM EST
*/

public final class UnknownServiceException extends org.omg.CORBA.UserException
{
  public String serviceName = null;
  public String message = null;

  public UnknownServiceException ()
  {
    super(UnknownServiceExceptionHelper.id());
  } // ctor

  public UnknownServiceException (String _serviceName, String _message)
  {
    super(UnknownServiceExceptionHelper.id());
    serviceName = _serviceName;
    message = _message;
  } // ctor


  public UnknownServiceException (String $reason, String _serviceName, String _message)
  {
    super(UnknownServiceExceptionHelper.id() + "  " + $reason);
    serviceName = _serviceName;
    message = _message;
  } // ctor

} // class UnknownServiceException