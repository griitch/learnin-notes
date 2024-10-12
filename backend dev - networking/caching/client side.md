# browser caching
[mdn reference](https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Cache-Control)
Based on http headers 

## Caching headers

- expires and pragma : used in http 1, not used as much as of today, but are still here for backwards compatibility 

- cache-control : the header that is used in http 1.1 and upwards 

    - Expires : absolute expiry date, can't be more than a eyear

    - Pragma : can only contain the value 'no-cache', it's a deprecated headers that was used to prevent caching

    - Cache-Control : multivalued header, its value is a comma separated list of directive that can take values, some ofthe directives are :
        - public : resource can be cached by everyone (the client and proxies)
        - private : only cached in the client
        - no-cache : content can be cached but needs validation each time
        - no-store : content can never be cached
        - max-age=(num of seconds to cache a resource)
        - s-max-age=(num of secs) s stands for shared, this is the caching duration at public spaces (proxies)
        - must-revalidate : indicates that the response can be cached and reused while fresh, but as soon as it becomes stale, it must be revalidated before reuse. if this directive was not present, a client can reuse a stale value if its max age is expired and the server is unreachable for validation
        - proxy-revalidate : same as prev header but for proxies
## Validation headers (for cache validation and invalidation)
- etag : unique id used to verify if a resource is stale or not, after the max-age value has passed, the client sends a req with the head `if-none-match : {value of the etag}`, if the resource changed the server will respond w the new resource and a new etag, if not, it will respond w a 304 status code (not modified), and the client will re-use the same resource for the duration of max-age once again
- Last-modified : instead of a unique id, its the last modification time of the resource, same as for etags, when the max-age value has passed, client sends a req with the header `if-modified-since: Wed, 15 sep 2023 12:30:23 GMT for example`, the server will respond w the new resource or w a 304 
- if both headers are present in the server response, the client will then send both of the validation client headers to the server, and the server will check both conditions and will return 304 if both have not changed
- if none are present in the response, there will be no request to validate the cache, and a new request will be made as soon as the resource goes stale

