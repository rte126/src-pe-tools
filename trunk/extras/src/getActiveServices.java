/**

http://juniper.net/techpubs/software/management/sdx/sdx51x/sw-sdx-components-vol1/html/sae-external-control4.html

Configure SAEFeature.java.scanInterval to a non-zero value.

 */

import java.lang.Integer;
import java.lang.Long;
import java.lang.System;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import net.juniper.smgt.sae.portal.*;

public class getActiveServices {

    public static String exec(String[] args)
    {
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date now            = new Date();
            String res = "";
            User user = new User();
            if (args[0].equals("")) {
                user.setUserDn(args[1]);
            } else {
                user.setUserIp(args[0]);
            }
            String[] slist = user.getActiveSubscriptionNames();
            Subscription subs = new Subscription(user);

            for (int i = 0; i < slist.length; i++) {
                subs.setServiceName(slist[i]);
                if (i > 0) {
                    res = res + ", ";
                }
                res = res + slist[i];
                if (subs.getRemainingSessionTime() < 0) {
                    res = res + ":forever";
                }
                else {
                    Date end = new Date(now.getTime() +
                                        subs.getRemainingSessionTime() * 1000);
                    res = res + ":" + formatter.format(end);
                }
            }

            return res;
        }
        catch (Throwable e) {
            return "";
        }
    }
}

