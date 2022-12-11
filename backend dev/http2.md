[goat article](https://web.dev/performance-http2/#:~:text=In%20short%2C%20HTTP%2F2%20breaks,within%20a%20single%20TCP%20connection.)

## Terms

A stream is a bidirectional flow of bytes within an established connection, which may carry one or more messages. Streams may be given a higher priority than others, and can have explicit dependencies on other streams.

Message : a sequence of frames that map to a logical request or response message.  
Frame : MTU of http/2, each containing a frame header that contains enough info to identify which stream the frame belongs to.

## TCP sockets optimization

http/1.1 uses 6 tcp connections with the server, and the client can only send requests one at a time using one of the 6 connections, if all the 6 sockets were used, the client waits for one of them to free up before sending another requests, this turns out to be inefficient as the tcp sockets are underutilized.

In http 2, only one tcp socket is used and its multiplexed, and the client can send as much requests as he can.
The tcp connection can handle any number of bidirectional streams, each stream has a unique stream id and optional priority information. Each req/res consists of one or more frames that are sent over the tcp socket. frames may be interleaved and reassembled.

## Header compression

In http/1.1 only the body was compressed, because compressing headers was the source of an attack known as CRIME related to the compression algorithms that were used, http/2 solves this problem by using a new algorithm named HPACK specially designed for http header compression.

## Binary encoded frames

Messages are encoded in binary format instead of the newline delimited plain text used in http/1.x. This is the killer feature of http/2 which causes a huge surge in performance

## Server push

The server can send multiple responses for a single client request, even if the client did not request the pushed resources, if the client requested index.html, he will eventually have to request for main.css and index.js, so the server anticipates that and sends them in the response to the initial request.

## Secure by default

the general consensus is that http should use tls, and one more thing, ALPN (app layer protocol negotiation : decide wether to use http/1.x or http/2) happens during the tls handshake
