package wftac.example.wss001;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public class Hello {
	@WebMethod
    public String greet(@WebParam(name = "name") String name)
    {
        return "Hello " + name + "!";
    }
}
