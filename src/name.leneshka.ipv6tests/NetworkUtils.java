package name.leneshka.ipv6tests;

import java.io.IOException;
import java.net.*;
import java.nio.channels.SocketChannel;
import java.util.Collections;
import java.util.Enumeration;

import static java.lang.System.out;

public class NetworkUtils {
    public static final String MAC_HOST_IP = "2001::13";
    public static final String MAC_OS_WI_FI_INTERFACE = "en0";

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

    public static void pingFromDefaults() throws IOException {
        Socket socket = null;
        try {
            socket = new Socket(IPv6_ONLY_SERVER_IP, IPv6_ONLY_SERVER_PORT);
            out.println("Connected: " + socket.isConnected());
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static boolean pingFromInterface(String name) throws IOException {
        out.println("Pinging from interface " + name);

        boolean connected = false;

        NetworkInterface networkInterface = NetworkInterface.getByName(name);
        if (networkInterface == null) {
            out.println("No network interface for " + name);
            return false;
        }

        Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();

        while (inetAddresses.hasMoreElements()) {
            InetAddress localAddr = inetAddresses.nextElement();
            out.println("Using address " + localAddr);

            connected |= pingFromLocalAddress(localAddr);
        }
        return connected;
    }

    public static boolean pingFromLocalAddress(InetAddress localAddr) throws IOException {
        boolean connected = false;
        Socket socket = null;
        try {
            socket = new Socket(IPv6_ONLY_SERVER_IP, IPv6_ONLY_SERVER_PORT, localAddr, 0);
            out.println("Created!");
            connected = true;
        } catch (IOException e) {
            out.println("Failed to connect");
            e.printStackTrace(out);
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
        return connected;
    }

    public static boolean pingWithSocketChannel() throws IOException {
        boolean connected = false;
        Socket socket = null;
        try {
            SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(IPv6_ONLY_SERVER_IP, IPv6_ONLY_SERVER_PORT));
            socket = socketChannel.socket();
            out.println("Created!");
            connected = true;
        } catch (IOException e) {
            out.println("Failed to connect");
            e.printStackTrace(out);
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
        return connected;
    }
}
