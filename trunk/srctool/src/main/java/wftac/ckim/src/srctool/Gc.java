package wftac.ckim.src.srctool;

import net.juniper.smgt.sae.sae.ServiceActivationEngine;
import net.juniper.smgt.sae.sae.ServiceActivationEngineHelper;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.omg.CORBA.ORB;

public class Gc implements CommandRunner {

	public void perform(Options options, CommandLine cmd) {
		 String saeIp = cmd.getOptionValue("gc");
		 String saeUrl = "corbaloc:iiop:" + saeIp + ":8801/SAE" ;
		 System.out.println("saeUrl = " + saeUrl);
		 String[] args = new String[1];
		 args[0] = "gc.java" ;
		 try {
			 ORB orb = ORB.init(args,null);
			 // sun corba had problem of BufferManagerReadGrow underflow when servant name is less than 4 characters
			 // http://bugs.sun.com/view_bug.do;jsessionid=56ffc6b80fd4f1c1f962f1601ed9?bug_id=6548437
			 org.omg.CORBA.Object obj = orb.string_to_object(saeUrl);
			 ServiceActivationEngine sae = ServiceActivationEngineHelper.narrow(obj);
		 } catch (Exception e) {
			 System.out.println("ERROR : " + e);
			 e.printStackTrace(System.out);
		 }
	}

}
