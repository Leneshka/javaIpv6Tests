package name.leneshka.ipv6tests;

import org.junit.Test;

import java.net.InetAddress;

import static org.junit.Assert.*;


public class IPv6ConnectionTest {

    /**
     * On provided setup throws java.net.NoRouteToHostException: No route to host
     * both with enabled and disabled -Djava.net.preferIPv6Addresses=true
     * <p/>
     * Should start working once Wi-Fi disabled, but currently I can't test it.
     */
    @Test
    public void testTestPingFromDefaults() throws Exception {
        NetworkUtils.pingFromDefaults();
    }

    /**
     * When provided exact network interface, java manages to connect. Note that only those interface
     * with ipv6 configured work.
     */
    @Test
    public void testConectionFromProvidedInterface() throws Exception {
        boolean connected = NetworkUtils.pingFromInterface(NetworkUtils.MAC_OS_WI_FI_INTERFACE);
        assertTrue("Failed to connect", connected);
    }

    /**
     * Works!
     */
    @Test
    public void testConnectWithExtendedIPv6LocalAddress() throws Exception {
        InetAddress localAddress = InetAddress.getByName(NetworkUtils.MAC_HOST_IP + "%" + NetworkUtils.MAC_OS_WI_FI_INTERFACE);
        boolean connected = NetworkUtils.pingFromLocalAddress(localAddress);
        assertTrue("Failed to connect", connected);
    }

    /**
     * Works!
     */
    @Test
    public void testConnectWithSimpleIPv6LocalAddress() throws Exception {
        InetAddress localAddress = InetAddress.getByName(NetworkUtils.MAC_HOST_IP);
        boolean connected = NetworkUtils.pingFromLocalAddress(localAddress);
        assertTrue("Failed to connect", connected);
    }

    /**
     * Does not work.
     */
    @Test
    public void testConnectWithIPv6LocalhostAddress() throws Exception {
        InetAddress localAddress = InetAddress.getByName("::1");
        boolean connected = NetworkUtils.pingFromLocalAddress(localAddress);
        assertTrue("Failed to connect", connected);
    }

    /**
     * Does not work.
     */
    @Test
    public void testConnectWithLocalhostAddress() throws Exception {
        InetAddress localAddress = InetAddress.getLocalHost();
        boolean connected = NetworkUtils.pingFromLocalAddress(localAddress);
        assertTrue("Failed to connect", connected);
    }
}