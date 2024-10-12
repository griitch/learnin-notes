# internet protocol
- the only thing i found interesting in this vid is how traceroute is implemented using icmp
- an icmp message indicating that the host in unreachable is sent from a router to the source ip addy when the ttl reaches 0
- so to trace the route from A to B, the router A sends a packet with ttl == 1, the packet will be dropped as soon as it reaches the router after A (probably the isp's routre), the isp router will decrement the ttl and since it will be 0, itll send an icmp message to A indicating that the host is unreachable ,that will give us the first step to reach B.
- after that A sends a packet with ttl==2, it will give the second step, and on and on, when the icmp packet source == B ip address, it means the packet finally reached its destination, and all the steps in between will be the route used to reach it 
