/**

http://juniper.net/techpubs/software/management/sdx/sdx51x/sw-sdx-components-vol1/html/sae-external-control4.html

Configure SAEFeature.java.scanInterval to a non-zero value.

 */

import java.lang.Integer;
import java.lang.Long;
import java.lang.System;
import java.util.List;
import net.juniper.smgt.sae.portal.*;

public class getUserIpByInterface {

    public static String exec(String[] args)
    {
        try {
            List ips = Ssp.getUserIpByInterface(args[0], args[1]);
            for (int j = 0; j < ips.size(); j++) {
                User u = new User();
                u.setUserIp((String)ips.get(j));
                if (u.getUserDn().equalsIgnoreCase(args[2])) {
                    return (String)ips.get(j);
                }
            }

            for (int i = 1; i < 1000; i++) {
                ips = Ssp.getUserIpByInterface(args[0] + "."
                                               + new Integer(i).toString(),
                                               args[1]);

                for (int j = 0; j < ips.size(); j++) {
                    User u = new User();
                    u.setUserIp((String)ips.get(j));
                    if (u.getUserDn().equalsIgnoreCase(args[2])) {
                        return (String)ips.get(j);
                    }
                }
            }

            return "";
        }
        catch (Throwable e) {
            return "";
        }
    }
}

