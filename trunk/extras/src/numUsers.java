/**

http://juniper.net/techpubs/software/management/sdx/sdx51x/sw-sdx-components-vol1/html/sae-external-control4.html

Configure SAEFeature.java.scanInterval to a non-zero value.

 */

import net.juniper.smgt.sae.util.Admin;
public class numUsers {
    public static String exec(String[] args) {
            Admin admin = Admin.getInstance();
            int numRouters = admin.getNumRouters();
            int numInterfaces = admin.getNumIntfs();
            int numIpUsers = admin.getNumIpUsers();
            int numIntfUsers = admin.getNumIntfUsers();
            int totalHeap = (int) ((Runtime.getRuntime().totalMemory())/1024);
            int heapInUse = (int) ((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())/1024);
            return "numRouters=" + numRouters +
                "\nnumInterfaces=" + numInterfaces +
                "\nnumIpUsers=" + numIpUsers +
                "\nnumIntfUsers=" + numIntfUsers +
                "\ntotalHeap=" + totalHeap +
                "\nheapInUse=" + heapInUse + "\n";
    }
}

