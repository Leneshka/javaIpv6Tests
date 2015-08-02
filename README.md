# Java IPv6 tests

It happens so that connection to IPv6-only hosts from java on Mac OS X Yosemite
has some issues.
This project is meant to provide instrumentation to examine those issues.


Some related links:
- Reported java [bug](https://bugs.openjdk.java.net/browse/JDK-8076571).
- Useful [thread](http://stackoverflow.com/questions/29103828/cant-connect-to-ipv6-only-host-from-java) on stackoverflow.
- Useful [idea](https://youtrack.jetbrains.com/issue/WI-26878#comment=27-1069340)
 on how to connect without providing exact interface.



This is test setup:
* host computer with Mac OS X 10.10.4 aka Yosemite, IPv6 enabled,
and IP `2001::13` (see `NetworkUtils.MAC_HOST_IP`)
* IPv6-only server with IP `2001::7` listening connections on `22` port
(obviously in my case it's ssh server). See them in `NetworkUtils.IPv6_ONLY_SERVER_IP` and
`NetworkUtils.IPv6_ONLY_SERVER_PORT`.


###### Tell me more about setup! ######
Well, IPv6-only server is a VirtualBox with Linux Mint on board.
Network settings configured via UI, in `Networking | <Connection name, 'Wired' for me> | Settings |
IPv6`. `IPv6: ON`, `Addresses: Manual`, `Address: 2001::7`, `Prefix : 64`. That's it. Do not forget to restart connection
back at Networking dialog. Also do not forget that for IPv6 you need `ping6`, not usual
`ping`.

Network connection on VirtualBox is Bridged based on Wi-Fi. As turning Wi-Fi off makes difference, note that you'd need
some other connected connection to test it. For example, connect Mac with cable, and base bridged connection on that one.

Mac OS X computer also needs IPv6 enabled and IP provided at `Network Preferences | Advanced |
 TCP/IP` . Set up `Configure IPv6: Manually`, `IPv6 Address: 2001::13`, `Prefix Length: 64`.
