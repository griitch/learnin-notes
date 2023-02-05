Metasploit is a set tool for info gathering, scanning, exploitation, exploit development, post-exploitation and more.  

The main components are :
- msfconsole : the main cli
- Modules : exploits, scanners, payloads...
- tools : stand-alone tools that help vunl research and assessment or pentesting...

## Defs
- exlpoit : a piece of code that uses a vulnerability present on the target system.
- vulnerabilty : a design, coding, or logic flaw within the target system, it's exploitation can result in disclosing confidential info or allowing code exec on the target sys.
- payload : code that will run on the target system
- auxiliary modules : scanners, crawrlers, fuzzers etc..
- encoders : to encode the exploit and payload in a way that bypasses signature-based antivirus solutions. 
- payload categories :
  - singles : self-contained, do not need to download additional components to run
  - stagers : responsible for setting up a cnx channel between metasploit and the target system, the stager will be download the 'stage' which represents the rest of the payload
  - stages : downloaded by the stager.
- post modules : used for post exploitation 

## Msfconsole
most linux commands are supported in it, but does not support some feeatures such as output/input redirection.  
Msfconsole is managed by context, all param settings will be lost when sitching modules unless they were set as global variables.  
We select a module using the `use` command followed by its name, when using a module, a context is created.  

The `show` command can be used folloed bya a module type, xmpl, `show paylods`, or `show options`  

We can leave the context with the `back` command  
Further info about a module is used with `info` within its context.  
`search` is one of the most useful cmds, we can search for CVEs, exploit names, or target systems, we can filter searches using a :, for example `search type:auxiliary telnet`

When all the required params are set using `set name value`, we can use the `exploit` command to run the exploit.  

When the vulnerability has been exploited, a session will be created, and a new prompt called `meterpreter` will be started,
we can leave meterpreter using the `background` command, it will still be running in the bg as the name suggests.  

We can list acive sessions using `sessions` command

