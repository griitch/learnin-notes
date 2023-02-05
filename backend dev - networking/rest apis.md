In order for a web service to be qualified as RESTFUL, it should adhere to 6 constraints

- uniform interface
- stateless
- cacheable
- client-server
- layered system
- code on demand (optional)

Key differences between rest and something like rpc or soap

- rest is resource based vs action-based
- rest uses nouns instead of verbs
- resources identified by URIs
  - multiple URIs may refer to the same resource

REST is not a standard or specification, it's an architectural paradigm. The format / mime type of the requests and responses is not a part of rest, but nonetheless, we can implement a response/request api format specification like JSON:API.

[JSON:API](https://jsonapi.org/) is a standard for building apis that use json as the exchange format and can be used in a rest architecture.

## Uniform interface

Using uri's and nouns to describe resources and http verbs to describe actions

## Stateless

- The server contains no client state
- Each request contains enough context to process the message : self-descriptive messages
- any session is contained on the client.

## Cacheable

Server responses are cacheable :

- implicitly : if no info is provided by the server about caching
- explicitly : if the server specifies max age, duration and all that jazz
- negotiated

## Layered system

The client can't assume direct connection to the server.
Middleware can sit in between the client and server.

## Code on demand

some bs that made no sense whatsoever to me, but it's optional so i won't bother using it unless i had to, then i will look up what it actually means. but essentially, from what i've understood, a browser can request some js to execute from the server for example

## Idempotent, safe and unsafe requests

for a request to be idempotent, requesting multiple times has the same effect as requesting it one and only one time. And by that we don't mean the same response from the server, but the server state as well.

PUT & DELETE operations are idempotent, even tho, sending a delete request after the first successful 200 request will have a 404 not found response, the server state remains the same, the resource is deleted.

Unsafe methods are method that alter the server state, such as POST,DELETE,PUT,PATCH

Safe methods are only intended for data retrieval and are safe and inherently idempotent.
