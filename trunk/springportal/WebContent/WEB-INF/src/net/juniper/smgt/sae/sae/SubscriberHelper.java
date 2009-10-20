package net.juniper.smgt.sae.sae;


/**
* net/juniper/smgt/sae/sae/SubscriberHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from saeaccess.idl
* Sunday, February 1, 2009 10:24:16 PM EST
*/


/**
   * Interface that is used to manage an active subscriber session.
   * Lets you get information about the session, make service schedules
   * available, add and remove subscriptions, set the session timeout,
   * manage session login, and activate and deactivate services.
   *
   * \b NOTE: All methods raise an UnknownUserException if the subscriber
   * session is no longer active.
   */
abstract public class SubscriberHelper
{
  private static String  _id = "IDL:smgt.juniper.net/sae/Subscriber:1.0";

  public static void insert (org.omg.CORBA.Any a, net.juniper.smgt.sae.sae.Subscriber that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static net.juniper.smgt.sae.sae.Subscriber extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (net.juniper.smgt.sae.sae.SubscriberHelper.id (), "Subscriber");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static net.juniper.smgt.sae.sae.Subscriber read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_SubscriberStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, net.juniper.smgt.sae.sae.Subscriber value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static net.juniper.smgt.sae.sae.Subscriber narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof net.juniper.smgt.sae.sae.Subscriber)
      return (net.juniper.smgt.sae.sae.Subscriber)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      net.juniper.smgt.sae.sae._SubscriberStub stub = new net.juniper.smgt.sae.sae._SubscriberStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static net.juniper.smgt.sae.sae.Subscriber unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof net.juniper.smgt.sae.sae.Subscriber)
      return (net.juniper.smgt.sae.sae.Subscriber)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      net.juniper.smgt.sae.sae._SubscriberStub stub = new net.juniper.smgt.sae.sae._SubscriberStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}