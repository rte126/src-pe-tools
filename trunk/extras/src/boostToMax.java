/**

http://juniper.net/techpubs/software/management/sdx/sdx51x/sw-sdx-components-vol1/html/sae-external-control4.html

Configure SAEFeature.java.scanInterval to a non-zero value.

 */

import java.lang.Integer;
import java.lang.Long;
import java.lang.System;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.net.InetAddress;
import net.juniper.smgt.sae.portal.*;

public class boostToMax {

    private static int serial = 1;
    private static String hostname;

    static {
        try {
            hostname = InetAddress.getLocalHost().getHostName();
        }
        catch (Throwable e) {
            hostname = "unknown";
        }
    }

    /* Arguments:
       + client id string
       + customer ip
       + isp id
       + dsa client id
       + dsa client passwd
       + service id
       + duration
     */
    public static String exec(String[] args)
        throws net.juniper.smgt.sae.portal.UnknownUserException,
               net.juniper.smgt.sae.portal.NonUniqueUserException,
               net.juniper.smgt.sae.portal.UnknownServiceException,
               net.juniper.smgt.sae.portal.ServiceAuthenticationException,
               net.juniper.smgt.sae.portal.UnknownSubscriptionException,
               net.juniper.smgt.sae.portal.OverloadException,
               net.juniper.smgt.sae.portal.SspException,
               /* net.juniper.smgt.sae.datamgr.DataMgrException, */
               net.juniper.smgt.sae.datamgr.b
    {
        StringBuffer sb = new StringBuffer();
        User u = new User();

        if (args.length != 7) {
            return "nok|false|6001|Bad number of arguments";
        }
        if (!args[5].equals("GENERAL_BOOST")) {
            return "nok|false|6002|Unrecognized service name given";
        }

        try {
            String boost_accid;
            int boost_acctime;
            int boost_duration = new Integer(args[6].trim()).intValue();
            boost_duration = boost_duration * 60;

            if (boost_duration <= 12*60*60) {
                boost_accid = "B012";
                boost_acctime = 12*60;
            }
            else if (boost_duration <= 24*60*60) {
                boost_accid = "B024";
                boost_acctime = 24*60;
            }
            else {
                return "nok|false|6003|Bad boost duration given";
            }

            u.setUserIp(args[1]);

            String services[] = u.getPersonalSubscriptionNames();
            for (int i = 0; i < services.length; i++) {
                if (services[i].substring(0, 2).equals("B_")) {
                    Subscription s = new Subscription();
                    s.setUserIp(args[1]);
                    s.setServiceName(services[i]);

                    int now = new Long(System.currentTimeMillis()
                                       / 1000).intValue();
                    int boost_start = now;

                    if (s.isActive()) {
                        boost_start = boost_start
                            + s.getRemainingSessionTime();

                        s.setPersistentSession(false);
                        s.setActive(false);
                    }

                    int boost_end = boost_start + boost_duration;

                    s.setSessionTag(Integer.toString(boost_end));
                    s.setPersistentSession(true);
                    s.setPersistentActivation(true);
                    s.setSessionTimeout(boost_end - now);
                    s.setActive(true);

                    Date timenow = new Date();
                    DateFormat logstampformat =
                        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    DateFormat retstampformat =
                        new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    DateFormat monthformat =
                        new SimpleDateFormat("yyyy-MM");

                    String puid = (String)u.getAttribute("sn");
                    String stamp = hostname +
                        "%" + Long.toString(timenow.getTime()) +
                        new DecimalFormat("0000").format(serial);
                    String ack = "ok" +
                        "|" + stamp +
                        "|" + retstampformat.format(timenow) +
                        "|" + "general boost" +
                        "|" + Integer.toString(boost_duration / 60) +
                        "|" + Integer.toString(boost_acctime);

                    String accfilename =
                        "/local/UMC/local/microorder/var/log/boost_accounting_";

                    if (u.getUserDn().matches(".*,retailername=wholesale,.*")) {
                        accfilename = accfilename + "ipws";
                    }
                    else {
                        accfilename = accfilename + "privat";
                    }

                    accfilename = accfilename + "_" +
                        monthformat.format(timenow) + ".csv";

                    BufferedWriter out =
                        new BufferedWriter(new FileWriter(accfilename, true));
                    out.write(stamp +
                              ";" + puid +
                              ";" + logstampformat.format(timenow) +
                              ";" + boost_accid +
                              ";" + Integer.toString(boost_duration / 60) +
                              "\n");
                    out.close();

                    serial = serial + 1 % 10000;
                    return ack;
                }
            }
            return "nok|false|6004|User has no boost service provisioned";

        }
        catch (UnknownUserException e) {
            return "nok|false|6005|No user identified by this IP";
        }
        catch (Throwable e) {
            return "nok|false|6999|" +
                e.getClass().getName() + ": " + e.getMessage();
        }
    }
}

