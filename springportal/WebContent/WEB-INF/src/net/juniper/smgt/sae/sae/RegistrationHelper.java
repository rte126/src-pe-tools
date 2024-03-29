package net.juniper.smgt.sae.sae;


/**
* net/juniper/smgt/sae/sae/RegistrationHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from saeaccess.idl
* Sunday, February 1, 2009 10:24:16 PM EST
*/

abstract public class RegistrationHelper
{
  private static String  _id = "IDL:smgt.juniper.net/sae/Registration:1.0";

  public static void insert (org.omg.CORBA.Any a, net.juniper.smgt.sae.sae.Registration that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static net.juniper.smgt.sae.sae.Registration extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  private static boolean __active = false;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      synchronized (org.omg.CORBA.TypeCode.class)
      {
        if (__typeCode == null)
        {
          if (__active)
          {
            return org.omg.CORBA.ORB.init().create_recursive_tc ( _id );
          }
          __active = true;
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [6];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[0] = new org.omg.CORBA.StructMember (
            "macAddress",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[1] = new org.omg.CORBA.StructMember (
            "loginName",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[2] = new org.omg.CORBA.StructMember (
            "userDn",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[3] = new org.omg.CORBA.StructMember (
            "intfName",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[4] = new org.omg.CORBA.StructMember (
            "vrName",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_wstring_tc (0);
          _members0[5] = new org.omg.CORBA.StructMember (
            "description",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_struct_tc (net.juniper.smgt.sae.sae.RegistrationHelper.id (), "Registration", _members0);
          __active = false;
        }
      }
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static net.juniper.smgt.sae.sae.Registration read (org.omg.CORBA.portable.InputStream istream)
  {
    net.juniper.smgt.sae.sae.Registration value = new net.juniper.smgt.sae.sae.Registration ();
    value.macAddress = istream.read_string ();
    value.loginName = istream.read_string ();
    value.userDn = istream.read_string ();
    value.intfName = istream.read_string ();
    value.vrName = istream.read_string ();
    value.description = istream.read_wstring ();
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, net.juniper.smgt.sae.sae.Registration value)
  {
    ostream.write_string (value.macAddress);
    ostream.write_string (value.loginName);
    ostream.write_string (value.userDn);
    ostream.write_string (value.intfName);
    ostream.write_string (value.vrName);
    ostream.write_wstring (value.description);
  }

}
