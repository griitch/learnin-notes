### Fuzzing
testing random inputs in hope of finding anomalities in the results  
treats the system as a black box  

## methods
#### spray and pray
We can try to fire any type of payload we can think of at any particular parameters and hope for an unexpected result. can take a long time

#### targeted list
directed atacks, xmpl using a list of usernames + password from somewhere else ( possibility of users using the same psswd on different sites ), or spraying endpoints with xss vectors etc...

### Hybrid  
example password fuzzing : using a list of usernames, passwords will be sprayed at random

## tools
a py or bash script can be enough, but we can also use burp intruder, owasp zap, wfuzz, ffuf etc..

## using ffuff
```shell

 ffuf [-X httpMethod ] -u http://foo.bar/FUZZ -w worldlist [-t number of threads used defaults to 40 ] [-mc http status to match eg : 200,201] -[fc http status codes to filter ] 

```
ffuf will replace FUZZ with whatever is in the wordlist, we can for example fuzz query parameters ```foo.baz?FUZZ=test```
