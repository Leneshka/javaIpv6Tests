package name.leneshka.ipv6tests;

import org.junit.Test;

import static org.junit.Assert.*;


public class IPv6ConnectionTest {

    /**
     * On provided setup throws java.net.NoRouteToHostException: No route to host
     * both with enabled and disabled -Djava.net.preferIPv6Addresses=true
     *
     * Should start working once Wi-Fi disabled, but currently I can't test it.
     */
    @Test
    public void testTestPingFromDefaults() throws Exception {
        NetworkUtils.pingFromDefaults();
    }
}