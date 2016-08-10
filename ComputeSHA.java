import java.io.*;
import java.security.*;

public class ComputeSHA {
    public static String convertSHA(String inputfile) throws IOException, NoSuchAlgorithmException {
        FileInputStream in = null;
        try {
            in = new FileInputStream(inputfile);
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] buf = new byte[1024];
            int temp;
            while ((temp = in.read(buf)) != -1) {
                md.update(buf, 0, temp);
            }
            String ans = "";
            byte[] h = md.digest();
            for (int i = 0; i < h.length ; i++ ) {
                ans += Integer.toString((h[i] & 0xff) + 0x100, 16).substring(1);
            }
            return ans;
        } finally {
            if (in != null) {
                in.close();
            }
        }

    }
     public static void main(String[] args) {
        try {
            String infile = "";
            if (args.length == 1) {
                infile = args[0];
            } else {
                System.out.println("Invalid Input File number of " + args.length);
            }
            System.out.println(convertSHA(infile));
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(0);
        }
    }
}