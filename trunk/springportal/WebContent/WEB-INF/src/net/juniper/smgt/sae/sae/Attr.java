package net.juniper.smgt.sae.sae;


/**
* net/juniper/smgt/sae/sae/Attr.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from saeaccess.idl
* Sunday, February 1, 2009 10:24:16 PM EST
*/

public final class Attr implements org.omg.CORBA.portable.IDLEntity
{

  /// Name of the attribute.
  public String name = null;

  /// Value of the attribute.
  public String values[] = null;

  public Attr ()
  {
  } // ctor

  public Attr (String _name, String[] _values)
  {
    name = _name;
    values = _values;
  } // ctor

} // class Attr