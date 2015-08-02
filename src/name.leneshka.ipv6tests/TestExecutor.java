package name.leneshka.ipv6tests;


import java.net.SocketException;

public class TestExecutor {

    public static void main(String[] args) throws SocketException {
        NetworkUtils.listInterfaces();
    }
}
