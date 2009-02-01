import net.juniper.smgt.sae.portal.*;
import java.util.*;

//
// test script for DSA invokeScript
// tested in SDX 6.4.0
// 
// DynamicServiceActivation properties
//   /permissions/script2/methodName = Test1
//   /permissions/script2/saeLocatorArg = 1
//   /permissions/script2/client2/clientId = Bob
//

public class Test1 {
	
	//
	// exec( user, arg1, ipAddress, ... )
	//
	// example
	// Test1, "aaaaaa;100.100.29.1; cccccc; dddddd ; eeeeee", Bob, secret 
	// Java script "Tests1 v0.1" was invoked with arguments: Bob aaaaaa 100.100.29.1  cccccc  dddddd   eeeeee getuserIp=100.100.29.1  getUserDn=uniqueID=user1,ou=default,retailername=domain,o=Users,o=UMC  getLoginId=null 
	//

    public static String exec(String[] args) throws PortalException {
        StringBuffer sb = new StringBuffer();
        User myUser = new User();
        myUser.setUserIp(args[2]);
        String myIp = myUser.getUserIp();
        String myDn = myUser.getUserDn() ;
        String myLoginId = myUser.getLoginId() ;
        
        sb.append("Java script \"Tests1 v0.1\" was invoked with arguments:");
        for (int i=0; i<args.length; i++) {
            sb.append(" ");
            sb.append(args[i]);
        }
        sb.append(" getuserIp=" + myIp + " " );
        sb.append(" getUserDn=" + myDn + " " );
        sb.append(" getLoginId=" + myLoginId + " " );
        return sb.toString();
    }
    
    public static void main(String[] args) throws PortalException {
    	String res = exec(args);
    	System.out.println(res);
    }
    	
}
