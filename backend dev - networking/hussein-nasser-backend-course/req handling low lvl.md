# How backends accept connections
- A server is listenning on an address:port combination (btw, listenning on the port only without specifying the host is a bad practice as it's basically listenning on all NICs, it's better to explicitly specify the host, sometimes we don't even wanna expose the port to the public, only to localhost or to a nic that has an ip in a private subnet)   
- a client connects th the ip:port  
- the kernel does the tcp handshake, creates a connection (a file w a file descriptor), the application process accepts then the connection and uses it to read write  

## What actually the kernel does during connection establishment
- For each call to the `listen` syscall,the kernel creates a socket, a SYN queue, and an ACCEPT queue
- The client sends a syn, the kernel adds the syn to the syn queue and sends back a syn/ack, whats being added to the q is a c struct containing info such as the src and dst ips and ports, initial sequence number, tcp control flags,timestamps etc.. 
- the client sends an ack, the kernel tries to match that ack with an entry in the syn queue. if it does find a match, it creates the connection, it removes, the entry from the syn queue, it adds the connection to the accept q, and it creates a file descriptor for the connection
- when the application accepts the connection, (the `accept` syscall that returns the fd value), the connection entry is removed from the accept queue.
- the application uses the fd to read and write to the connection, and to eventually close it.
- If the syn queue is full (the maximum number of connections to accept is the `backlog` parameter of the listen syscall), no more connections can be initiated, any further syn is dropped (SYN FLOODING : sending syn, but no acknowledging when receiving the syn/ack)
- The accept call is blocking, it stays blocked while the accept queue is empty. (technically, it's blocking by default, if the socket is marked as nonblocking and the q is empty,the call will fail with an error)  
- if the accept queue gets full (the appplications are not accepting connections fast enough, which is the main problem reverse proxies struggle with) no more connections can be established. the kernel may reply with a RST to the incoming SYNs

# Reading and sending socket data    
- in addition to the syn and accept queues, SEND and RECEIVE queues are also created at the same time a call to `listen` is made 
- When a tcp segment arrives, its dataa is put the in the receive queue, the kernel ACKS and updates the window (basically lets the sender know that its maximum amount of storage for the next segment is not as big as the previous one, it will become so when the app reads the previous segment)
- the app calls `read` to _copy_ the data from the kernel to the app space : copying it from the kernel memory to the app memory. the call to read is blocking if there is no data to read
- the ack is handled by the kernel and wether the app read the data or not is irrelevant 
- the app then needs to parse the sequence of bytes and makes sense out of them
- sending data is done w the `send` syscall, data is copied from user to kernel space
- the kernel does not send the data right away, it bufferizes data for a moment and sends it when there is enough data


# Listeners, readers, and acceptors
- these 3 can be architected in different ways, the listener is the process that calls `listen` and specifies the address and the port number, and receives the socket fd. the acceptor is a process that has access to the socket id and calls `accept` on it to get file descrpitors related to the connections. and finally the reader (or write),who reads/write from the connections fds, all of these 3 can be the same process. but can also be different ones or multiple threads etc....
## single listener, single worker thread 
example node js. a single threaded proces that accepts, listens and reads from all the connections, asynchronosly using epoll, it's a very simple architecture, but is not scalable for receiving alot of connections. but you can spin up more node instances.

## Single listener, multiple worker threads
example memcached. One process that spins up a number of threads, that one process is the listener/acceptor. the main process is an inifite loop, that keeps on acceping new connections, for each new one it assigns a thread from the lot. a thread is not necessarily handling one connection at a time, it could be handling more than one concurrently. 

## single listener, multiple worker threads but with load balancing
RAMcloud, a storage system for data center apps which store data in dram. The worker threads execute the REQUESTS (applicatio lvl reqs), the listener, acceptor and readers are in the main process.

## multiple accepter threads on a single socket
A possible way nginx can be configured. One listener, the main process, but multiple threads call accept on the same listener socket (they can do so because they share memory w the main process). 

## multiple listeners on the same port
using the `SO_REUSEPORT` option can be used to share a socket between multiple processes. basically, the os create as much syn/accept queues for each process that wants to use the port, and the incoming connections will be load balanced. each thread can be its own listener, acceptor, and reader. each call to listen will be a different socket id that no one can use but the calling thread. this is the default in multiple rev proxies including nginx (socket sharding)


