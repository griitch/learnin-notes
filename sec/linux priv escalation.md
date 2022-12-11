# Privilege escalation methods on linux machines

Methods can be divided into 3 big parts :

- credential access :

  - reused passwords : a password you cracked somewhere could be root passwd
  - credentials from readable config files/ local dbs
  - credentials from bash history
  - ssh keys
  - sudo access ( sudo -l )
  - group privileges (docker, lxd etc..)

- exploits / cve's :

  - exploit services running on localhost
  - kernel exploits
  - binary file versions : look for binaries that "stand out" and seems out of place, and look for exploits

- misconfiguration : the most common way to privilege escalation
  - cron jobs : writable crontab, writable cronjob dependency (if job depends on a py script, check if you can write to it, if not, check if you can write to the directory, if you can do so, you can delete the original script and replace it with your own)
  - SUID/SGID files
  - sensitive writable files (/etc/{shadow,passwd,sudoers}, services config files)
  - sensitive readable files (/etc/shadow, /root/.ssh/id_rsa)
  - writable root $PATH (check if u can write directly the path var and add your own dir at the beginning, if not so, try writing the directories in root's path, replace the binaries with your own binaries that have the same name)
  - LD_PRELOAD set in /etc/sudoers

# Readable /etc/shadow

/etc/shadow file format : colon (:) separated list of

- Username
- Encrypted password
- Date of last password change
- Minimum required days between password changes
- Maximum allowed days between password changes
- Number of days in advance to display password expiration message
- Number of days after password expiration to disable the account
- Account expiration date
- Reserved field

```shell
cat /etc/passwd | grep root > hash.txt
```

```shell
john --wordlist=/foo/bar/rockyou.txt hash.txt
```

# Sudo

List the programs the su can use with

```shell
sudo -l
```

Search on https://gtfobins.github.io/ for ways to bypass the security restrictions using those programs.

# Cron jobs

Suppose you had something like this in /etc/crontab

```
* * * * * root foo.sh
```

locate foo.sh and overwrite its content to make it start a reverse shell (after setting up a listener on your machine ), use a payload from https://github.com/swisskyrepo/PayloadsAllTheThings

# SUID execs owned by root

just look for files with the suid set with

```shell
 find / -perm /4000 2>/dev/null
```

and look in gtfo bins for exploit

# Shared object injection on SUID executables

SUID (Set User ID) is a type of permission which is given to a file and allows users to execute the file with the permissions of its owner.
We try to find a suid executable which depends on a shared library and replace it with a malicious one that spawns a root shell. one way to do that is by using strace (which capture the system calls made by an executable) while redirecting stderr to stdout.

```shell
    strace foo 2>&1 | grep -iE "open|access|no such file"
```

our hope is to find something like this : open(foo.so) = -1 ... (no such file or directory). We will make our own using gcc -shared.

# SSH keys

```
ls -al /
```

and see if you find something like root_key in .ssh, if you do use it to ssh into the machine root

```shell
 ssh -i root_key.txt root@ip_address
```

# SUDO with LD_PRELOAD env variable

LD_PRELOAD is an env variable that specifies paths to shared libraries that will be charged into memory to be used in a program.

```shell
    sudo LD_PRELOAD=/path/to/shared_lib/lib.so executable
```

By default, env variables are wiped out when using sudo, in order for this to work, LD_PRELOAD should be added to the list of the default env variables that are persisted when sudo is used.  
In /etc/sudoers : Defaults env_keep+=LD_PRELOAD.  
And we need to be able to use any command as sudo (sudo -l returns some sort of exec)

# NFS with root squash disabled

If the root squash option is enabled on the nfs server, files created by the root of the client machine will not be owned by root on the server, they will be mapped to a nobody, no group user.  
To check if its enabled or not jst create a file and ls -l to check its owner.  
If root squash is disabled, create a program that spawns a shell and execute it.

# Kernel exploits

run uname -a to know what version of the kernel is running, and look for exploits on exploit db to crack it.

# Docker group

If you were in the same group as Docker, you can launch a container and mount the whole underlying file system in it, since you are root inside the container you can do whatever you want in there
