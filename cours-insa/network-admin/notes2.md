# VMS 

## VMS types
A hypervisor is a type of computer software, firmware or hardware that creates and runs virtual machines. A computer on which a hypervisor runs one or more virtual machines is called a host machine, and each virtual machine is called a guest machine

#### Bare metal 
real phyiscal machines that are concurrently sharing the same resource, xmpl : 2 machines sharing the same screen

#### au dessus de l'os
Simple ol vms that run on vmware or virtual box

### Vmware workstation
- it creates :
	- switches :
	- NAT 
	- DHCP server
	- dns server ( a server that only forwards requests to the host machine )

- types of cnx :
	- bridged : uses the network interface card of the host, as if the vm was in the same network as the host machine  
	- host only : the machine is connected to a virtual lan, and it is attributed an address via DHCP,`is it the DHCP server that the host uses or the or the virtual ` NAT is translating the addresses in the network requests of the guest as if they were coming from the host
	- NAT : the vm is connected to the NAT that attributes an ip to it via dhcp. i did not the difference between this and host only.


# DHCP

- A machine needs an ip, and it also needs to know the mask in order to know in which network it's located
- uses UDP because the communication is brief, and does not go too far away, no need for all the tcp handshake overhead
- server uses port 67, the client uses 68  
- multiplexage impossible because the client port is fixed (we don't even need to make multiple requests for ip addies, unless we had multipe NICs)
- extension de BOOTP : bootstrap protocol which was used historically for unix-like systems to get the network location of their boot image
- DHCP Handshake :
	- DORA to remember, discover, o, r, acknowledge
	- Discover : le client ne connait rien a part from his mac addy, he sends a broadcoast of type Discover ***Ethernet FF:FF:FF:FF:FF:FF, IP 255.255.255*** to the machines of his direct subnet, the router does not route the request unless it was configured to do so (possible dhcp server in outside server..) 
	- the client can specify an optional preffered ip addy, and can optionnaly send the req in unicast if he knew the sever beforehand (dhcp.conf)

	- offer :
		- the servers reply to discover
		- replies with some suggestions of ips
		- arp is used to determine wether an ip is free or not
	
	- Request :
		- the client chooses an ip from the requested ips, and `broadcasts` it to the whole network

	- Acknowledgment :
		- 


# DHCP server conf
- package isc-dhcp-server, conf in /etc/dhcpd.conf, 
- available ips /var/lib/dhcp/dhcpd.leases
 
