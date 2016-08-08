package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;

/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
public class NetUtil {

    public static String getExternalIP() {

        String ip = null;
        try {

            BufferedReader in = new BufferedReader(new InputStreamReader(new URL("http://checkip.amazonaws.com").openStream()));

            ip = in.readLine();

            if (ip == null || ip.isEmpty()) {
                throw new IOException("Invalid address: '" + ip + "'");
            }
            try {
                InetAddress.getByName(ip);
            } catch (Exception e) {
                throw new IOException("Invalid address: '" + ip + "'");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ip;


    }
}
