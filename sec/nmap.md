# Scan types

## TCP Connect

- St flag.
- used by default if ran without sudo

performs a full tcp hand shake with the host on the specified ports.  
port is marked as :

- open if the syn, syn/ack, ack handshake is successful.
- closed if the server responds with a rst to the initial syn.
- filtered by a firewall if no response

That being said, we're not always sure about the state of the port if it was either closed or filtered, because for example we can configure iptables to respond to filtered packets with a rst flag and indicate that the port is closed even tho it's open but filtered

```shell
iptables -I INPUT -p tcp --dport <port> -j REJECT --reject-with tcp-reset
```

## SYN scans

- -sS flag
- AKA half-open or stealth scans.
- Instead of completing the 3 way handshake, a rst segment is sent after receiving the syn/ack from the server.

- Can be used to bypass older intrusion detection systems (IDS) since they only log established connections.
- are way faster than connect scans

- but have some disadvantages :

  - require sudo permissions, since it needs to create raw packets (raw packets are created in the application layer, and have a flag set that indicates to the underlying layers that the packet does not need further encapsulation )
  - unstable services are sometimes brought down by syn scans.

Port is marked as :

- open if the server responds to the initial syn with a syn/ack
- closed | filtered : same rules as a tcp connect scan.

## UDP scan

- -sU flag
- very slow in comparison the other scans.
- good practice to target well known ports only
- ports marked as :
  - open | filtered : if no response is received to the first request and to the double-check that is sent afterwards
  - open : if it gets a udp response (very unusual tho)
  - closed : if the server responds with a icmp ping containing a message that the port is unreachable.

## NULL, FIN, XMAS scans

- null
  - -sN flag
  - tcp request is sent without any set flags
  - per the rfc the server should respond with a RST if the port is closed
- FIN
  - -sF flag
  - tcp request is sent with the FIN flag set only
  - RST response if port is closed
- XMAS

  - sX flag
  - malformed tcp request is sent (PSH,URG,FIN flags are set)
  - RST response if port closed

- The expected response for open ports is also identical, similar of that of a UDP scan. Ports are marked as open|filtered if the requests get no response, and as filtered if the server responds with a icmp ping.
- in practice some systems respond to malformed packets with RST instead of no response as specified in the rfc, so no we're still not sure about the state of the ports
- these scans are used mainly for firewall evasion, many firewalls are configured to drop incoming tcp packets to blocked ports that have the syn flag set, by sending requests that do not have this flag set, the firewall is bypassed, but most modern ISDs are savvy to these scan types so its not always effective.

# ICMP Network scanning

aka a ping sweep, nmap sends icmp packets to all possible hosts on a given network to see which hosts are up

```shell
nmap -sn 192.168.20.1-254
```

or

```shell
nmap -sn 192.168.20.20.0/24
```

if ran as root, arp requests are also sent if the network is local.
the -sn switch will also send tcp syn to 443 and an ack to 80 (or syn if ran as root)

# NSE : nmap scripting engine

- written in LUA
- can be used for scanning vulnerabilities, automating exploits for them, but particularly for reconnaissance.

- there are many categories :
  - safe : won't affect the target
  - intrusive : will likely affect the target
  - vuln : scan for vulnerabilities
  - exploit : attempt to exploit a vulnerability
  - auth : attempt to bypass auth for services
  - brute : attempt to brute force credentials for services.
  - discovery: Attempt to query running services for further information about the network (e.g. query an SNMP server)

```shell
nmap 192.124.12.43 --script=<script name | script category> --script-args {args}
```

multiple scripts can be loaded if separated with commas.

example

```shell
nmap -p 80 --script http-put --script-args http-put.url='/dav/shell.php',http-put.file='./shell.php'

```

args are separated by commas.

Scripts can be found either in the nmap website.
They are locally found on /usr/share/nmap/scripts/

that folder contains also a text file scripts.db that contains greppable info about the present scripts in the machine.

## Firewall evasion techniques

other than the stealth, fin, xmas .. scans, there are other methods to evade firewalls.
found [here](https://nmap.org/book/man-bypass-firewalls-ids.html).

examples :

- -pN : do not ping host before scanning it (bypassing icmp block at the cost of longer scans in case the host was not alive since nmap will retry)
- -f fragment packets
- --scan-delay=<n>ms
- --badsum : generate invalid checksum for packets, Any real TCP/IP stack would drop this packet, however, firewalls may potentially respond automatically, without bothering to check the checksum of the packet. As such, this switch can be used to determine the presence of a firewall/IDS
- --data-length <num>: Append random data to sent packets
