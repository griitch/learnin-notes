# What is oauth

A protocol for api delegated authorization, let the client ( the app not the end user ) get access to the data related to an account of another service such as google or facebook without asking for the username / password of the said service.  
Before oauth, some websites used to ask for the email/password of other services explicitly, yelp for example used to ask users to enter their email's address and password so that they can log in and invite users contacts to the website via email.

# Terms

- resource owner : the end users who owns the data and who authorizes the app to use the data
- resource server : the server that holds the resource, and which is accessible by a protected API, oauth grants the access to this API.
- client : the app that requests authorization to the protected resources.
- authorization server : the server that is responsible of accepting credentials from the resource owner, and granting access to the protected resources for the client, might be the same server as the resource server.
- oauth flow : set of steps to reach the end goal of the client : receiving an access token to use protected resources.
- scopes : application specific permissions that the client can request to have access to, for example, gmail scopes might be : read emails, send emails, delete emails...etc

# Common oauth flows

### Authorization code flow

- The client exchanges a code for a token, the exchange is done on the back channel, well suited for a server side rendered app
  - back channel : highly secure channel of communication, server to server for example
  - front channel : slightly less secure channel, your browser for example
- it goes like this :
  - user clicks a login with gmail button
  - gets redirected to `gmail.com/oauth2/auth?clientId={public client id} &scope={do stuff} & redirect_uri={where to redirect after success} & response_type=code & state=foobar`, state query param is like a csrf token to verify if the request has not been forged
  - user signs in with his gmail account, and gives consent for the client to access the data he asked for in the scope.
  - user gets redirected to the callback uri with some query params : `example.app/oauth2/callback?code={the code}&state=foobar `
  - the client sends a post request to the authorization server encoding the code, the client secret and id, the response will be an access token the client can store in his server without needing to have it at the user side. the client will just establish a session id cookie to keep the app stateful.

### Implicit flow

- the access token is stored at the users side storage in the front channel, no code is exchanged, the authorization server returns directly the token to the app. Well suited for apps that don't use a proper backend and only talk to APIS such as single page apps
- flow goes like :
  - click login on google from my spa
  - redirected to google login page
  - enter my credentials
  - get redirected to my callback url with the access token in the url, (insecure asf)

### PKCE

Proof key of code exchange, a more secure workaround the less secure implicit flow using some cryptography and pseudo random numbers, i did not dig deep in it and i don't really understand it

# Openid connect

oAuth was not designed for authentication, it was conceived mainly for authorisation, the early login with facebook, google thingies were essentially some hacks using oauth, and each vendor used its own set of hacks around oauth to make it useable for authentication as well.

To solve this, a new protocol emerged, openid connect, which is an extension on top of oauth, that provides a set of standardised scopes, a /userinfo endpoint that is accessible with a user_id token, among other stuff that make oauth viable for user authentication as well.
