/**

http://juniper.net/techpubs/software/management/sdx/sdx51x/sw-sdx-components-vol1/html/sae-external-control4.html

Configure SAEFeature.java.scanInterval to a non-zero value.

$Id: customerStatusByIp.java,v 1.3 2006/05/08 13:21:25 bmork Exp $
 */

import java.lang.Integer;
import java.lang.Long;
import java.lang.System;
import java.util.List;
import java.util.regex.*;

import net.juniper.smgt.sae.portal.*;

public class customerStatusByIp {

    public static String exec(String[] args)
    {
        try {
            User u = new User();
            u.setUserIp(args[1]);
            String puid = (String)u.getAttribute("sn");
            Pattern p = Pattern.compile("^xdsl_([0-9]*).*");
            Matcher m = p.matcher((String)u.getAttribute("uniqueId"));
            if (m.matches()) {
                return "|ok|puid=" + puid + ", xdsl=" + m.group(1);
            }
            else {
                return "|nok|false|7001|Malformed user object";
            }
        }
        catch (Throwable e) {
            return "|nok|true|7999|System error: " +
                e.getClass().getName() + ": " + e.getMessage();
        }
    }
}

