/**
   A sample Java script that echos any arguments it is called with.
 */
public class Echo {

    public static String exec(String[] args) {
        StringBuffer sb = new StringBuffer();
        sb.append("Java script \"Echo\" was invoked with arguments:");
        for (int i=0; i<args.length; i++) {
            sb.append(" ");
            sb.append(args[i]);
        }
        return sb.toString();
    }


}
