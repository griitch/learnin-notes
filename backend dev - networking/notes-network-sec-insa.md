# Important stuff and things i did not know before

## States of digital data

- data at rest : on persistent storage
- data in motion : on the network
- in use : being processed

![](https://cdn.ttgtmedia.com/rms/onlineimages/whatis-states_of_digital_data.png)

## Common network attacks :

- eavesdropping : eve listening to what alice and bob are saying
- spoofing : usurpation
- tampering : alteration

## Perfect forward secrecy

- [Hussein nasser video about pfs in tls](https://www.youtube.com/watch?v=zSQtyW_ywZc)
- a feature of some key agreement protocols that gives assurances that past session keys will not be compromised even if long-term secrets used in the session key exchange are compromised, in ssl, the long term secret is an rsa priv key
- traditionally, in tls/ssl, only the server's priv key was used to encrypt the exchanges between the server and the client in the key exchange phase, if an attacker got a hold of the server's priv key, he can decrypt anything, including the past sessions conversations...
- forward secrecy can be guaranteed by not using the same key to encrypt conversations all the time, and to generate ephemeral keys on the fly for each new session
- diffie-hellman, the usual and elliptic curves version both guarantee pfs
- using rsa does not guarantee it

## Zero knowledge proofs protocols

- [Computerphile video](https://www.youtube.com/watch?v=HUs1bH85X9I)
- [Numberphile video](https://www.youtube.com/watch?v=5ovdoxnfFVc)
- Prove that we have a secret without divulging it
- implementation : we're being asked a series of questions, if we somehow reply correctly to a high percentage, there is a high probability that we got the secret
- look at feige-fiat-shamir

## Public key infrastructure

- boring def : set of hardware and software used to implement a chain of trust
- a certificate : a public key + metadata that identifies the owner of the priv key associated with the public key
- to get a x509 certificate for my server :
  - generate a priv/pub key pair
    -generate a csr (certificate signing request) that embeds the pubkey
  - send the csr for the CA (generally not a root CA, a signing CA)
  - the ca validates the csr and issues a certificate that can be deployed
- the clients needs to validate the certificate signatures all the way to the root ca, and they also need to download the CRL to verify if the cert is still valid

# QUESTION TO ASK : DOES EVERY SERVER HAVE IT CRL OR IS IT A CA THING

## Vlans

- [bald guy that does comptia videos on youtube video](https://www.youtube.com/watch?v=933KsYiuugg)
- used to limit the broadcast range
- A trunk port is a network port that is used to connect a switch to another switch or to a router. It allows multiple VLANs to pass through one physical port, allowing for segmentation of a network. It uses tagging to identify the VLANs and it can be configured as either an access port or a trunk port.
- Access ports are used to connect a host to the network, while trunk ports are used to connect switches to other switches or to routers.
- trunk links might have a whitelist of vlans that are allowed to pass thru them
- Vlan on another switch : when a frame gets in the switch, its vlan tag is inspected and the switch sends the frame through the trunk link that corresponds to the vlan tag

- The VLAN tag is added to a frame by a switch when the frame is first received on a port that is configured as an access port and the frame is destined for a device that is connected to a different VLAN

# QUESTION : LES TAGS ARE ONLY ADDED WHEN GOING THRU A TRUNK LINK ?

## Best practices

- put unused ports in a quarantine vlan
- trunk ports used only when necessary
- use whitelists when connected to an untrusted zone
- 1 vlan = 1 ip subnet

## Vlan and qos

- the vlan tag is 4 bytes long, 3 bits are for qos

## Vxlan

- x for extensible
- VXLAN was created to overcome the limitations of traditional VLANs and to provide a more scalable and flexible solution for network virtualization in large-scale cloud computing environments.
- ethernet frames inside udp datagrams

# EAP : extended auth protocol

- a layer 2 protocol to control who can access the physical layer with auth
- is a stateful mechanism : keeps track of the state of the authentication process. It maintains information such as the identity of the device that is trying to connect, the credentials that have been provided, and the current stage of the authentication process

# layer 2 attacks

- arp spoofing : map an attacker's MAC address to the IP address of a legitimate device on the network. netcut basically
- arp cache poisoning : as far as i understood, the target is the switch not the machines that are connected to it, its basically done by flooding the switch w fake requests
- vlan hopping

# Skipped taking notes about layer 3

# Layer 4

## Tunnels

This is so ambiguous, so what i understand is, tunneling is, encapsulating packets, inside of other packets to transmit data over a network for multiple reasons.

- Insecurity : maybe the network is insecure, and we wanted to securely transmit the data, so we first create a secure tunnel, vpn or ssh or whatever, and then pipe the data through it
- Restrictions : maybe the protocol we wanted to use is restricted with a firewall, and we sneak in by wrapping the data inside a packet that is not restricted by the fw
- Heterogenous networks : allow a protocol to run on top of a network that does support that particular protocol, example running ipv6 on top of ipv4

## /dev/net/tun and tap

- Virtual device on linux machines to encapsulate ip packets (tun) or ethernet frames (tap)
- They basically simulate layer 3 (tun) and layer 2 (tap) devices
- Apps from the user space can read and write frames and packets to and from the TUN/TAP devices
- VPNS use them a lot

## SSH

### Openssh features

- port forwarding
- x11 forwarding, (run graphical apps on a remote server)
- ip/ethernet tunneling
- ssh-agent :

  - a program that stores the priv key in memory because it's tedious to enter the passphrase each time a user wanna connect, they only need to enter the passphrase once at the first login
  - runs beyond the duration of a local login session, stores unencrypted keys in memory, and communicates with SSH clients using a Unix domain socket.
  - unix sockets are an ipc mechanism for comm between processes on the same machine. it uses the file system as the comm channel. the socket is represented as file and processes can open the socket for r/w just like any other file, and unix permissions can be set on it

- ssh-add : loads a key into the ssh-agent
- ssh-keyscan : takes an ip, and looks for pub ssh keys to populate ~/.ssh/known_hosts

## ssh best practices

- key auth > password auth :
  - passwords can be cracked/intercepted
  - ssh key with a passphrase => most secure sht ever

[best image about port forwarding](https://i.stack.imgur.com/4iK3b.png)

# SSL/TLS

- is a protocol
- client and server auth with certs
- a lot of supported cypher suites
- when a client is connected to a server he analyzes the CN common name and SAN subject alternative name of the presented cert to validate the identity of the server (see if the cert was really delivered to that domain or ip )

## https

- redirecting users to https if they connect on port 80 is not enough
- hsts : http strict transport sec :

  - Strict-Transport-Security header
  - example

  ```
  Strict-Transport-Security: max-age=31536000
  ```

  - The server that supports hsts tells the browser to only communicate with the website over HTTPS for a specified period of time (max-age)
  - Once the max-age time expires, the browser will again connect to the site over HTTP to check if the site still wants to enforce HSTS. If the site still enforces HSTS, the browser will again enforce it for the new max-age specified.
  - it ensures that even if a user types the site's URL into the browser or clicks on a link that uses HTTP, the browser will automatically upgrade the connection to HTTPS.
  - HSTS also includes a feature called "includeSubDomains" which tells the browser to enforce HTTPS for all subdomains of the specified domain

- ways to know that a server cert is valid

  - crl's : but can get too big, and are cached by browsers so a stale version can be read sometimes
  - ocsp online cert status protocol :

    - client connects to server, server replies with his cert
    - client asks the ocsp transponder of the CA that signed the server cert if the cert is still valid
    - it replies with either good, revoked, or unknown.

  - oscp stappling :
    - client connects to server
    - server contacts the OCSP transponder and gets his ocsp status, with a date and a signature
    - the server puts that thing in his cache for 7 - 10 days
    - it staples the ocsp status to his cert when replying to the client, and other subsequent clients while the cache is still valid
  - crt transparency :
    - an append-only log file that is readable by only and is cryptographically secure (a blockchain ???)
    - csr's and revocations are logged
    - servers who issue a csr and get it validated get a uuid that represents an index in the log file
    - servers send the uuid in their replies to the client
    - the clients need to look up the public log to see if everything is true

# VPNS

# SPLIT TUNNELING

- principe : not all the traffic should pass by the tunnel
- solution : routing rules
- use case : netflix while working remotely
- risk : dns leaks => contact the default dns server and not the one in the vpn, ppl will know what you will querying..

# Proxies

- advantages : caching, logging, requests auth
- proxy http with connect method

## SSL BUMPING

- client sends client hello
- proxy generates a new certificate on the fly that appears to be valid and trusted by the client.
- proxy on his turn does an ssl handshake w the server
- basically no end to end enc, data gets decrypted at the proxy and re-encrypted when going to the distant server
