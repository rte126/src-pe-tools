package wftac.example.ldap;

import java.util.Hashtable;

import javax.naming.*;
import javax.naming.directory.*;

public class LdapSearch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Hashtable<String,String> env = new Hashtable<String,String>();
	    String sp = "com.sun.jndi.ldap.LdapCtxFactory";
	    String ldapUrl = "ldap://c2000-5.wftac.jnpr.net/o=users,o=umc";
	    String baseDn =  "retailerName=sql";
	    String userCn = "cn=umcadmin,o=umc";
	    String userPw = "admin123" ;
	    DirContext dctx;
	    SearchControls sc;
        final String attrMac = "description";
        final String attrSvcLevel = "sn" ;
        final String attrMSISDN = "businessCategory" ;
        String[] attributesReturn = { attrSvcLevel, attrMSISDN } ;
        String valueMac = "aa:bb:cc:00:00:00";
        String searchFilter = "(" + attrMac + "=" + valueMac + ")";
	    
	    env.put(Context.INITIAL_CONTEXT_FACTORY, sp);
	    env.put(Context.PROVIDER_URL, ldapUrl);
	    env.put(Context.SECURITY_AUTHENTICATION, "simple");
	    env.put(Context.SECURITY_PRINCIPAL, userCn);
	    env.put(Context.SECURITY_CREDENTIALS, userPw);


	    try {
	    	dctx = new InitialDirContext(env);
	    	sc = new SearchControls();
	        sc.setReturningAttributes(attributesReturn);
	        sc.setSearchScope(SearchControls.SUBTREE_SCOPE);
	        NamingEnumeration<SearchResult> results = dctx.search(baseDn, searchFilter, sc);
	        while (results.hasMore()) {
	            SearchResult sr = (SearchResult) results.next();
	            Attributes attrs = sr.getAttributes();

	            Attribute attr = attrs.get(attrMSISDN);
	            System.out.print(attr.get() + ": ");
	            attr = attrs.get(attrSvcLevel);
	            System.out.println(attr.get());
	          	}
	        dctx.close();
	        Thread.sleep(5000);
	    } catch (NameNotFoundException e){
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    

	}

}
