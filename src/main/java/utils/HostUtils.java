package utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class HostUtils {
    public static String getHostName() {
        InetAddress addr = null;
        try {
            addr = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return addr.getHostName();
    }
}
