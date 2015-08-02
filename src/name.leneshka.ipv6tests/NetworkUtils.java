package name.leneshka.ipv6tests;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;

import static java.lang.System.out;

public class NetworkUtils {
    public static final String MAC_HOST_IP = "2001::13";
    public static final String MAC_OS_WI_FI_INTERFACE = "eth0";

    public static final String IPv6_ONLY_SERVER_IP = "2001::7";
    public static final int IPv6_ONLY_SERVER_PORT = 22;


    private NetworkUtils() {
    }


    public static void listInterfaces() throws SocketException {
        Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();

        for (NetworkInterface netIf : Collections.list(nets)) {
            printInterface(netIf);
        }
    }

    private static void printInterface(NetworkInterface netIf) throws SocketException {
        out.printf("Display name: %s\n", netIf.getDisplayName());
        out.printf("Name: %s\n", netIf.getName());
        displaySubInterfaces(netIf);
        out.printf("\n");
    }

    private static void displaySubInterfaces(NetworkInterface netIf) throws SocketException {
        Enumeration<NetworkInterface> subIfs = netIf.getSubInterfaces();

        for (NetworkInterface subIf : Collections.list(subIfs)) {
            out.printf("\tSub Interface Display name: %s\n", subIf.getDisplayName());
            out.printf("\tSub Interface Name: %s\n", subIf.getName());
        }
    }
}
