E-tags, short for entity tags, are one of the mechanisms in the http spec for cache validation, and can also be used to optimistic concurrency control of web resources

according to wikipedia :

    An ETag is an opaque identifier assigned by a Web server to a specific version of a resource found at a URL

example :

- client : get /users/bob

  - server : sends bob with his E-tag in a header
    `E-Tag : 10c34fafrjnfjr333 `

- client : get /user/bob
  with the header : `if-None-Match : 10c34fafrjnfjr3331`
  - server : returns 304 not modified status code if bob did not change ( because the e-tag did not change ), if bob did change send the new bob and the new e-tag

## Pros

- save bandwidth and server resources
- optimistic concurrency control mechanism to ensure db consistency

## Cons

- cause problems when servers are behind a load balancer as the servers can generate different e-tags for the same unchanged resource, the solution is to make the e-tag generation deterministic and have no dependency on the server
- when making our own http client application, we need to make e-tag aware (browsers manage this by default)
- some companies use e-tags to track users who disable cookies (e-tags cannot be disabled),
