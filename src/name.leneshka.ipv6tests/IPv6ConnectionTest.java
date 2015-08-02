package name.leneshka.ipv6tests;

import org.junit.Test;

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
}