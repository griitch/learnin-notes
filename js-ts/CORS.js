const express = require("express");

const app = express();

/*
https://developer.mozilla.org/en-US/docs/Web/HTTP/CORS#preflighted_requests
https://developer.mozilla.org/en-US/docs/Web/Security/Same-origin_policy
https://jakearchibald.com/2021/cors/
*/

//-----------------------------  same origin policiy : -----------------------------------------------
/* browsers by default restrict fetching some scripts or documents from different origins, an origin is 
the {protocol, host, port} tuple. Some cross origin network access is typically allowed like :
 - cross origin writes ( links (with certain Content-type headers only), form submissions ...)

 - cross origin embedding : 
    <script src=""></script>,
    css w <link rel="stylesheet" href="…"> but with correct Content-type header
    <img>, <video>, <audio> etc ...
-----------------------------------------------------------
 - cross origin reads are typically disallowed 

 to allow cross origin access we use cors
*/

//-----------------------------  CORS : ---------------------------------------------------------
/*
cors is made to loosen up the same origin policy
cors requests are sent with the { Sec-Fetch-Mode : cors } header


so there are 3 types of cors requests  

  - "normal" requests, which only require the access-control-allow-origin to match origin
normal reqs are { get head post } but with a few restrictions on the headers
*/
app.get("/", (req, res) => {
  res.setHeader("Access-Control-Allow-Origin", "*");
  // if you try to fetch "/" from another website, youll get an error if the header above was not present
  // if it's not *, it should match the value of the "origin" req header
  res.send("foobaztar");
});

/*
  - "requests that require a preflight request"
the browsers sends an OPTIONS request to the server to see if the methods and the headers that 
the client wanna send are accepted by the server, example of the headers in the preflight request  :

  Access-Control-Request-Methods: POST, PUT
  Access-Control-Request-Headers: Content-Type, Custom-griitch-Header

and their respective response headers

  Access-Control-Accept-Methods: POST, PUT
  Access-Control-Accept-Headers: Content-Type, Custom-griitch-Header

if there is a match, the actual request request will be then sent

the code below is for this client side req 
fetch("http://localhost:3000/", {
    headers: { "Content-Type" :"json/application",
    "Custom-griitch-Header" : "im wanted for tax fraud" }
    ,method : "put"
}).then(a => a.text()).then(console.log)

// in the options route handler, we specify that we accept Content-Type and custom-machin-header 
the 204 status code is for no content (empty body)
*/
app.put("/", (req, res) => {
  res.setHeader("Access-Control-Allow-Origin", "*");
  res.send("fpppppppppppp");
});

app.options("/", (req, res) => {
  res.setHeader("Access-Control-Allow-Origin", "*");
  res.setHeader(
    "Access-Control-Allow-Headers",
    "content-type, Custom-griitch-Header"
  );
  res.setHeader("Access-Control-Allow-Methods", "PUT, GET ");
  res.status(204).end();
});

/* 3rd type of reqs : reqs with credentials : by default, cross site requests are made without
credentials (cookies, tls certs... ), in order to use them, set xmlHttpRequest.withCredential = true,
or { credentials : include } in fetch options.

fetch("http://localhost:3000/withcredentials", 
{ credentials : "include",}
).then(a => a.text()).then(console.log)

*/

app.get("/withcredentials", (req, res) => {
  //res.cookie("dummycookie","dymmvalue", { sameSite: "none" }); // only for setting the cookie 1st time
  res.setHeader("Access-Control-Allow-Origin", "https://www.bing.com");
  res.setHeader("Access-Control-Allow-Credentials", "true");
  res.send("fooo");
});

/* that was for "normal" requests mentionned earlier, for requests that require a preflight, there are some
rules : 
- preflight reqs never include credentials : but preflight resps should have the allow-creds = true header
when responding to a credentialed req (after validating using preflight req), the server must not specify
"*" on any of the allow { headers, origin, methods } headers, they all should be well defined values,
otherwise the browser will blocc the resp

 */

app.listen(3000, (err) => {
  if (!err) {
    console.log("server listenning 3000");
  }
});
