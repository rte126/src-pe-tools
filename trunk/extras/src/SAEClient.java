//
// sample for case 2008-1117-0006
// using sun orb
// idlj sae.idl, saeaccess.idl
//

package client;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.Binding;
import org.omg.CosNaming.BindingIteratorHolder;
import org.omg.CosNaming.BindingListHolder;
import org.omg.CosNaming.BindingType;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import sae.*;

public class SAEClient {
	
	public static void main(String[] args) {
		
		try {
			// create and initialize the ORB
			ORB orb = ORB.init(args, null);
			String corbalocURL = "corbaloc::10.3.1.67:8801/SAE";
//			String corbalocURL = "IOR:000000000000003549444C3A736D67742E6A756E697065722E6E65742F7361652F5365727669636541637469766174696F6E456E67696E653A312E3000000000000000010000000000000060000102000000000A31302E332E312E36370022610000001063346B2D312F736165504F412F534145000000020000000000000008000000004A414300000000010000001C00000000000100010000000105010001000101090000000105010001";
			System.out.println("Getting ServiceActivationEngine...");
			org.omg.CORBA.Object obj = orb.string_to_object(corbalocURL);
			ServiceActivationEngine sae = ServiceActivationEngineHelper.narrow(obj);
			System.out.println("Obtaining the SAEAccess object...");
			SAEFeature saeFeature = sae.getFeature("SAEAccess");
			SAEAccess saeAccess = SAEAccessHelper.narrow(saeFeature);
			
			//saeFeature;
			System.out.println("Obtained the SAEAccess object, do your job now.");
			String filter = "*";
			int howmany = 1;
			sae.SubscriberIteratorHolder iter = new sae.SubscriberIteratorHolder();
			org.omg.CORBA.IntHolder total = new org.omg.CORBA.IntHolder();
			sae.Subscriber[] subs;
			subs = saeAccess.findSubscriber(filter, howmany, iter, total);
			String[] sels = new String[1];
			sels[0] = "*";
			sae.Attr[] attrs = subs[0].readSubscriber(sels);
			System.out.println(attrs[0].name + ":" + attrs[0].values[0]);
		} catch (Exception e) {
			System.out.println("ERROR : " + e);
			e.printStackTrace(System.out);
		}
	}

	private static void showBindings(ORB orb, NamingContextExt ncRef) throws NotFound,
			CannotProceed, InvalidName {
		// ////////////////////////////////////////////////////////////////////
		System.out.println("getting binding list...");
		BindingListHolder bl = new BindingListHolder();
		BindingIteratorHolder blIt = new BindingIteratorHolder();
		ncRef.list(1000, bl, blIt);
		Binding bindings[] = bl.value;
		if (bindings.length == 0)
			return;

		for (int i = 0; i < bindings.length; i++) {
			// get the object reference for each binding
			org.omg.CORBA.Object obj = ncRef.resolve(bindings[i].binding_name);
			String objStr = orb.object_to_string(obj);

			System.out.println("objStr:" + objStr);
			int lastIx = bindings[i].binding_name.length - 1;

			// check to see if this is a naming context
			if (bindings[i].binding_type == BindingType.ncontext) {
				System.out.println("Context: "
						+ bindings[i].binding_name[lastIx].id);
			} else {
				System.out.println("Object: "
						+ bindings[i].binding_name[lastIx].id);
			}
		}
	}

}
