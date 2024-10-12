# Backend communication design patterns

## Request response

classic request response u know, for each request from the client, a response from the server

## Asynchronous vs synchronous workload

### sync io

Caller sends a req and BLOCKS while waiting for the request

### async io

- caller can do work while waiting for a resp by either :
  - checking regularly if resp is ready or not (epoll whatever that is)
  - the receiver / callee makes a call back to the caller (io_uring)
  - spin up a new thread that is blocked (node js with libuv threadpool)

## push

- usually associated w websockets
- client connects to a server, this cnx stays on, and the server can push stuff to the client innit
- used by rabbitmq
- clients MUST be online


## Polling
- when requests take too long to process
- server responds immediately w a handle (a unique id) to the client
- the client uses this handle to check if the resource is ready or not
- i saw this in action in api for voice transcription
- the client can disconnect as long as he can store the id persisently
- too chatty of an approach, might overwhelm the server 

## Long polling
- same as polling, the server responds instantly w a handle
- the difference is, when the client requests info on the status, the request blocks
- the server will respond the status-check response only when the request is fulfilled
- used by kafka

## server sent events
- one request, infinite responses
- there is a browser api to use them
- it starts with a request w a content type 'text/event-stream'
- then the connection is maintained and the server sends text messages that must start w 'data'
- the clients listens for these messages and an event handler is added  
- they cause a problem when using http 1.1 because of the 6 connections thingy, if 6 sse connections are on, no more http reqs can be made to the server
    
## pub/sub
- one publisher, multiple subscribers
- solves the problem of coupling between services
- the broker puts stuff in 'topics'
- each topic can be subscribed to by many subsribers

## stateful/stateless
- stateful backend : stores info about clients in memory, if the backend is restarted, the client can't work as he did before the restat
- stateless : the client is responsible for transfering the state at each req, if the server restarts, the communication can be pick up where it left off
- a backend can be considered stateless if it stores the state somewhere else, for example, storing the sessionId in a db instead of memory, the backend app (the flask server for example) is stateless, but the whole system is stateful

- the notion of statefulness is also applied to protocols, tcp for example is stateful because the state of the connection is stored as file descriptors
- a stateful protocol can be made on top a stateless one (quic over udp) and vice versa (http on top of tcp)

