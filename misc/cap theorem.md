[goat article](https://mwhittaker.github.io/blog/an_illustrated_proof_of_the_cap_theorem/)

# Consistency

Any read operation that begins after a write operation must return that value,
or the result of a later write operation

# Availability

Every request received by a non failing node must return an answer

# Partition tolerance

The system must be able to work even when partitioned. this means that the network is allowed
to drop messages from one node to the other.

## Proof that only 2 properties can exist on a distributed system

Suppose we had a system of 2 nodes, g1 and g2, that has all the 3 properties.  
First thing to do is partition the system : g1 and g2 can no longer communicate w each other.  
The client issues a write request to g1, since the system is available it needs to respond.  
but, since the system is partitioned it g1 cannot send the new version of the data to g1.  
Next, the client issues a read request for g2, requesting the data written on g2, the system
is available but responds with stale data, therefore the system is inconsistent.
