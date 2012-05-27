package wftac.testws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(name = "TestWs")

@SOAPBinding
(
      style = SOAPBinding.Style.DOCUMENT,
      use = SOAPBinding.Use.LITERAL,
      parameterStyle = SOAPBinding.ParameterStyle.WRAPPED
 )


public class TestWs {
	@WebMethod
	public String greet( @WebParam(name = "name") String name )
	   {
	      return "Hello" + name;
	   }
}
