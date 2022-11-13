as im already familiar with most of the topics ill just skim thru this chapter

## new stuff i learnt

- http tunnelling & http connect method

  - client sends to the proxy / vpn server a connect request with the ip address of a distant server that the client cannnot access directly
  - an http tunnel is created
  - a tunnel is basically a tcp connection between the server and the proxy through which the client requests will be forwarded
  - any subsequent request to the proxy from that client will be sent to the distant server thru that tunnel ( with nat )
  - why hadchi kamlo ? to have e2e encryption, the client will send the tls hello req to the proxy who will send it to the server, the server will reply with its tls certificate to the proxy who will send it back to the client
  - 9bel kan the proxy ki jawb ela tls hello b its own certificate, encryption katkon bin client o proxy o bin server o proxy, so the proxy has access to the data as its being decrypted fih.
  - more details on nasser [huseins vids](https://www.youtube.com/watch?v=PAJ5kK50qp8&list=TLPQMTkwNjIwMjI-TDzZBRmXmg)

- http trace method
  - returns the sent request in its body
  - used for debugging
