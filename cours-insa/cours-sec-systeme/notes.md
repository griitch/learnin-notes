# SELinux : security enhanced linux

## some security principles

- least privilege principle : an entity must be able to access only the information and resources that are necessary for its legitimate purpose
- privilege segregation (role) :
- CIA (confidentiality, integrity, availability)

[a nice article](https://www.linux.com/news/securing-linux-mandatory-access-controls/)

## DAC

- controle d'access discretionnaire
- Security model used by most mainstream os's
- enforces security by ownsership :
  - if a user owns a file he can set the permissions on it
- the owner of the system does not have total control over the system, the users do
- The biggest concern with the DAC model is the almighty root account who can control all files and processes
- programs have the same permissions as the users who launched them
- security levels depends on the security levels of the apps running

## MAC mandatory access control (a fine grain control)

- from what i've understood, MAC is basically the security admin defining exactly what subjects can do on a given resource, and the policies are centrally controlled by him, the users cannot override them like in DAC
- MAC approach limits the need for a root account, and shifts the power from the user accounts to the owner of the sys.
- Limit sys calls each user can make
- even root can't violate it
- zero trust in all apps

## What is selinux

- a kernel patch developed by the NSA
- implements MAC through the use of type enforcement, role based access control, and multi-level security
- no concept of a super user
- based on DTE which was licensed, and because licenses suck they decided to create their own
- There is no secure program, selinux is only here to minimize the impact and not protect 100%, selinux does so by limiting what the rights associated to the program so that, even if compromised, not much damage will be done
- other models : biba, blp , military tech that are proven to create a system that respects either integrity or confidentiality

  - blp : no reads up, no write downs. assures confidentiality

  - biba assures integrity : there are 2 types of resources and processes, privileged and non privileged processes(P anand NP), and critical and non critical resources (C and NC), no reads down, no write ups

## RBAC : role based access control

- [Dan Walsh article about selinux roles](https://danwalsh.livejournal.com/75683.html)

- because implementing MAC in unix is too complicated because the admin will have a HUGE set of rule for each user
- assigns roles to users
- there are too many rbac models :
  - for example, if a used had conflicting roles, one which permits an action and another that does not, what to do

## Type enforement

- every object in the system is assigned a type, the sec admin then defines policies that indicate the access between pairs of types
- A type = A Security Context (SC)

## Multi-level security mls

- MLS allows for classified info to be shared at different security clearance levels, whatever that means...
- represented in selinux coloring book by different rules for a greyhounds and for chihuahuas, a greyhound can do everything a chihuahuas can do but the opposite is not true

## Elements of Selinux

- [Security context](#security-contexts) :

  - subject : active entity => process
  - object : passive entity (socket file...)

- [Operations](#operations) : permissions on classes

- Interactions : an operation between 2 security contexts : SC1 => SC2 [class:permission]

- [A selinux policy](#selinux-policies) is the set of all allowed interactions

## Security contexts

- a set of attributes, with 3 important ones :
  - identity : owner of the context
  - role : RBAC role
  - Type : for type enforcement, subjects have a `domain (scs)`, objects have a `type (sco)`
  - Format is identity:role:type, example : system_u:system_r:httpd_t
- Other attributes : category and sensibility

> The identity controls the reachable roles and the roles control the reachable types.

- each process is labeled by a CSS (context security subject)
- when a process is created he inherits the type of his parent, a transition can be made to change its type

- system objects are labeled by a CSO (context security object)
  - stored in the inode
  - par default, a file inherits the cso of the dir it was created inside
  - the default role for objects is object_r

> object_r is not really a role, but more of a place holder. Roles only make sense for processes, not for files on the file system. But the SELinux label requires a role for all labels. object_r is the role that we use to fill the objects on disks role

## Domains and types

- an id, declared and defined by the author of the politic
- no intrinsic meaning, makes no sense outside of the politic
- regroups subjects and objects having the same permissions
- called type for objects, domain for subjects, but mean the same thing
- naming convention, for a given app/service foo

  - foo_d_t : daemon type
  - foo_t : type for classic process
  - foo_exec_t : the executable file (/usr/bin/ssh for example)
  - foo_conf_t : conf file
  - foo_key_t : sensible files
    etc..

- an object/process may have one and only type, but the same type can be assigned to multiple objects/processes

## Transition operations

### Operations :

- un droit d'un contexte sujet sur un autre contexte
- un droit => permission on an object class

  - Class : selinux defines classes for objects (file for files, dir for directories ...), [article](https://access.redhat.com/documentation/en-us/red_hat_enterprise_linux/5/html/deployment_guide/rhlcommon-section-0049)
  - permissions : syscalls (read,write,unlink,accept...) + transitions

### Transitions

- by default, when a process is created it inherits the sec context of its parent
- a process can update an attribute of its context : it transits
- each attribute has it own rules, for example identity est normalement impossible a changer sauf appel a setcon
- the transition can be :
  - automatic : When a process executes a file (init_t transits to sshd_t when executing /usr/bin/sshd )
  - manual : appel a setcon(sid id) in the code of the program

## selinux policies :

- Set of access control rules

  - rules of authorisation
  - rules of types transition
  - audit rules

- selinux policy can be :

  - static :
    - a big binary monolith containing all rules
    - a policy update requires the re-compilation
    - legacy
  - modular :
    - a policy is a collection of modules
    - each module specifies the policy of an app
  - gentoo : when downloading something like firefox, firefox selinux modules are automatically downloaded
    - update do not require re-compilation
  - is dynamic : supports hot insertion and removal of modules

- Policies are `factorized` : for example, system_u:system_r:httpd_t might apply for every type of http server, que ce soit apache, nginx or iis ...

### types of policies :

- targeted : default policy for most Linux distributions, and it provides a targeted level of security by restricting access to certain system services and applications, the rest of the system is "unconfined", which basically means no control

- strict : This policy provides a higher level of security by enforcing stricter rules for controlling access to system resources. It is more difficult to configure and maintain than the targeted policy, but it can provide better protection against security threats

- can also specify some audits : example, know if someone cat'd /etc/shadow

### Policy mode

- Enforcing mode : SELinux will strictly enforce the rules defined in its policies. This means that any access to a resource that is not specifically allowed by the policy will be denied. This is the default mode for SELinux, and it provides the strongest security by preventing any unauthorized access to resources on the system.

- Permissive : SELinux will still enforce its policies, but it will not deny access to any resources. Instead, it will log any violations of the policy, but it will allow the access to proceed. This mode can be useful for testing and troubleshooting, as it allows administrators to see if any violations of the policy are occurring without actually preventing access to the resources

- disabled : no policy is enforced, duh

## Specification of a selinux policy

Need to define :

### Identities :

- should be independent of the system users
- Are persistant, a subject keeps his identity tout au long de sa session even after a su :
  - there are 2 exceptions, auth (login, ssh ...) => affectation de l'id initiale d'un user, et cron
- They guarantee l'imputabilité of actions on the sys
- `system_u` is a SElinux specific user (does not correspond to any actual user) that is used for system services
- `user_u` for users not declared in the policy

```shell
semanage login -l # list current mappings

Login Name                SELinux User

__default__               user_u
root                      root
system_u                  system_u
user                      staff_u

```

- Identities are created in the file `config/local.users`, full path for the gentoo vm is /root/selinux-base-policy-20080525/[targeted,refpolicy,strict]/local.users

```sh
# sample for administrative user
user admin roles { staff_r sysadm_r };
# sample for regular user
user toto roles { user_r };
```

### Roles

- Link identities to domains (subject types)
- The notion of roles is only applied to subjects (processes)
- a user can change his role during a session with the `newrole` command
- in policy/users declare roles

```shell
role sysadm_r;
role staff_r ;
role user_r
```

- associate a role to an identity

```shell
# l ’ utilisateur root peut utiliser les rôles user_r et sysadm_r
user root roles {user_r sysadm_r};
```

- associate types to roles

```sh
# le rôle sysadm_r peut accéder au type sysadm_t
role sysadm_r types sysadm_t;
role staff_r types staff_gpg_t;
```

- allow transitions from a role to another

```sh
# le role system_r peut transiter vers le rôle sysadm_r
allow system_r sysadm_r;
```

- Types :

- Control access rules :

  - legal operations of a type on another

- transition rules
- The relationships between system objects and object contexts
- audit rules : legal operations that are logged
- constraints (whatever that means in this context)

### Authorization rules

- Declare classes in policy/flask/security_classes

  > class security  
  > class process  
  > class system

- declare permissions in policy/flask/access_vectors
  the syntax is like :
- starts with common prefixes which are like the base

  > common file
  > {
  > ioctl
  > read
  > write
  > ....
  > }

- For classes, inherit from a common prefix and add class specific permissions

> class file  
> inherits file  
> {  
>  execute_no_trans  
>  entrypoint  
> ....  
> }

### type declarations

- all types (objects and subjects) need to be declared once before utilisation.

> type init_d;  
> type sshd_t;  
> type init_conf_t;

- Attribute = ensemble de types, vu comme un seul type
- define attributes (kinda like declaring vars without initialization)

  > attribute admin_terminal  
  > attribute application_exec_type

- associate an afore declared type to an attribute

  > typeattribute <type> <attribute>;  
  > typeattribute shadow_t security_file_type;

- delcare a type and associate it to attributes
  > type <type>, <comma separated list of attributes>;  
  > type afs_bos_client_packet_t, packet_type, client_packet_type;

### Access control rules

- allow sourceType targetType:class { permissions };
- the target type can be an attribute
- this line : give the type sysadm_t the permissions {} on objects of class file and type ssh_exec_t
  > allow sysadm_t ssh_exec_t:file { getattr read execute };
- some of the most used permissions are : **read, write, excecute, getattr(ibutes), setattr, some syscalls like [open, ioctl,link,unlink]**

### Control rules other than allow

- audit reading of shadow_t by sysadm_t

  > auditallow sysadm_t file:read shadow_t;

- Neverallow = deny
- use ~ for negation, this rule prevents non privileged users from reading shadow_t, this rule says, never allow anyone that does not have the can_read_shadow_passwords type from reading files of type shadow_t

  > neverallow ~can_read_shadow_passwords shadow_t:file read;

- use - to exclude types, this rule says : don't audit access attempts from staff_t on dir class with type file_type, the security_file_type is excluded from this rule
  > dontaudit staff_t { file_type −security_file_type }: dir { getattr search read lock};

### Permissions macros : factorize permissions

- policy/support/
- r_file_perms => open,getattr, read, lock, ioctl
- r_dir_perms : open, getattr, read, lock, search, ioctl
- x_file_perms : open, getattr, execute

### Type 1 transitions

- automatic type transition lors d'un access sujet
- change / narrow down droits d'un process en fonction de son but
- user_t transits to user_ssh_t when executing a file w type ssh_exec_t

  > type_transition user_t ssh_exec_t:process user_ssh_t;

- this needs additional allow rules (idk why this reminds me of dns glue records)
  > allow user_t ssh_exec_t:file {read getattr execute}  
  > allow user_r user_ssh_t : process transition ;  
  > allow user_ssh_t ssh_exec_t : file entrypoint;

### Type 2 transitions

- automatic type transition lors d'un access object
- change the type of created objects

- when a process of type user_t accesses an object of type tmp_t, the object type transits to user_tmp_t

  > type_transition user_t tmp_t:{ dir file lnk_file sock_file fifo_file } user_tmp_t;

- when a process labeled w sshd_t accesses an object of type tmp_t, the object type transits to sshd_tmp_t

  > type_transition sshd_t tmp_t:{ dir file sock_file } sshd_tmp_t

- needs additional allow rules as well
  > allow user_t tmp_t: dir { open getattr create write };  
  > allow user_t user_tmp_t:file rw_file_perms;

### Attribution des contextes

- subjects (aka processes) : contexts are dynamically created

  - inherited from the father process (init is the father of all of em)
  - updated with transitions

- objects : rules make the links between objects and object contexts
  - when the policy is compiled a file `file_contexts1` is created, there are multiple files like that on the td gentoo machine, one of them is /etc/selinux/targeted/contexts/files/file_contexts, the file content is of the form `/usr/(.*/)?lib(/.*)?    system_u:object_r:lib_t`, the first line is `/.*     system_u:object_r:default_t`
- the label (context) is stored in the inode as an extended attribute
- when a new object is created it inherits the context of its parent dir
- it can be changed with type_transition rule

```
#HOME_DIR est une variable qui représente tous les home directory
HOME_DIR/\.ssh(/.*)? system_u:object_r:ROLE_home_ssh_t

#label les fichiers sensibles
/etc/ssh/primes −− system_u:object_r:sshd_key_t
/etc/ssh/ssh_host_key −− system_u:object_r:sshd_key_t
/etc/ssh/ssh_host_dsa_key −− system_u:object_r:sshd_key_t
/etc/ssh/ssh_host_rsa_key −− system_u:object_r:sshd_key_t

#label les applications
/usr/bin/ssh −− system_u:object_r:ssh_exec_t
/usr/bin/ssh−agent −− system_u:object_r:ssh_agent_exec_t
/usr/bin/ssh−keygen −− system_u:object_r:ssh_keygen_exec_t
/usr/libexec /openssh/ssh−keysign −− system_u:object_r:ssh_keysign_exec_t

#label le service
/usr/sbin/sshd −− system_u:object_r:sshd_exec_t

#label les fichiers variables créé par le daemon
/var/run/sshd\. init \. pid −− system_u:object_r:sshd_var_run_t
```

### Constraints on rules

- boolean expressions expressed in the policy and checked when compiling it, uses =, !=, and, or
- used to make constraints on ids, roles and types

```py
#contrainte sur la transition de processus
constraint process transition
(
# l ’ identité source et cible doivent être identique
u1 == u2
# le type doit avoir le droit de changer l’ identité ( login ...)
or ( t1 == can_change_process_identity and t2 == process_user_target )
# il s’ agit de cron
or ( t1==cron_source_domain and (t2==cron_job_domain or u2==system_u))
# il s’ agit du systeme
or ( t1 == can_system_change and u2 == system_u )
# aucun contrôle sur ce type
or ( t1 == process_uncond_exempt )
) ;

```

## Modules selinux, the most important part

- its a part of a selinux policy that is specific to an app/service
- can be hot plugged/unplugged from the kernel anytime
- a module has a generic Makefile for compilation and loading and is made of 3 files :

### my app.te : type enforcement

- declaration of TE model rules
- name and version of the module
- declaration of roles, subject and object types
- declaration of control rules

### myapp.fc : object contexts

- declaration of associations file <-> object context
- i guess it's the same syntax as the file_contexts file

### myapp.if : rules for other modules

- dynamic rules for interaction w other modules
- generic rules using macros and variables
- in the TD this was empty..

### TE file example

```
# Nom du module et version
policy_module(myapp,1.0.0)

# Declarations des types
type myapp_t;
type myapp_exec_t;

# association de myapp_t à l’ attribut domaine (contexte sujet)
domain_type(myapp_t)

# transition automatique vers myapp_t lors de l’exécution de myapp_exec_t
domain_entry_file(myapp_t, myapp_exec_t)

# type pour les fichiers de logs
type myapp_log_t;

# ajout de myapp_log_t à l’ attribut log_file
logging_log_file (myapp_log_t)

# déclaration du type pour les fichiers temporaires
type myapp_tmp_t;

# ajout de myapp_t à l’ attribut tmp_file
files_tmp_file (myapp_tmp_t)
########################################
#
# Myapp local policy
#
# myapp peut lire/ajouter dans les fichiers myapp_log_t
# utilisation d’un attribut pour les permissions : ra_file_perms (read/append)
allow myapp_t myapp_log_t:file ra_file_perms;

# génération des règles d’accès pour myapp_t à myapp_log_t
# myapp_t peut changer les droits sur ces fichiers temporaires myapp_tmp_t
# utilisation d’un attribut manage_file_perms pour les opérations de changement de droits
allow myapp_t myapp_tmp_t:file manage_file_perms;

# transition automatique pour les fichiers créés tmp_t vers myapp_tmp_t
files_tmp_filetrans (myapp_t,myapp_tmp_t,file)

```

### FC file example

```
# myapp executable will have:
# label : system_u:object_r:myapp_exec_t

/usr/sbin/myapp −− system_u:object_r:myapp_exec_t

/root/\.claws-mail(/.*)? 		root:object_r:staff_clawsmail_file_t

/usr/bin/claws-mail			--	system_u:object_r:clawsmail_exec_t

/usr/bin/sylpheed-claws			-l	system_u:object_r:clawsmail_exec_t

```

- The middle column : -- refers to regular files, -d directories, nothing listed means anything is matched, -l symbolic link, -b block device... basically, -x where x is the type of file that appears when u do a 'ls -l'

### IF file : fichier de déclaration d'interfaces

```
########################################
## <summary>
## Execute a domain transition to run myapp.
## </summary>
## <param name="domain">
## Domain allowed to transition.
## </param>
# Règles pour la transition automatique d’un type ($1) vers myapp_t
interface(‘myapp_domtrans’,‘
  gen_require(‘
    type myapp_t, myapp_exec_t;
  ’)
  domain_auto_trans($1,myapp_exec_t,myapp_t)
  allow $1 myapp_t:fd use;
  allow myapp_t $1:fd use;
  allow $1 myapp_t: fifo_file rw_file_perms;
  allow $1 myapp_t:process sigchld;
’)

```

### Compilations and loading of the module

- Run `make`, it verifies that everything is alright and it compiles it
- the result is a .pp file, clawsmail.pp for example
- `make load` loads the policy

### Attribution des contextes objets au system

- for all the system :`rlpkg` command : Usage: rlpkg [OPTIONS] {<pkg1> [<pkg2> ...]}  
  −a, −−all Relabel the entire filesystem instead of individual packages.  
  −r, −−reset Force reset of context if the file ’s selinux identity is different or the file ’s type is customizable.  
  −t, −−textrels Scan for libraries with text relocations and relabel them.

- for a module that has just been compiled : `setfiles` command :
  - usage: setfiles [−dnpqvW] [−o filename] [−r alt_root_path ] spec_file pathname...
  - usage: setfiles −c policyfile spec_file
  - usage: setfiles −s [−dnqvW] [−o filename ] spec_file
  - will usually be used with the .fc file and the directory whose files will be labeled recursively as a params **setfiles clawsmail.fc /**

### tools to help with dealing with policies

- the logs : /var/log/avc.log + dmesg

```log
type=1400 audit(1227463900.474:15869): avc: granted { read execute } for pid=10488 comm="dmesg" ppid=8900 path="/lib/libc−2.6.1.so" dev=sda3 ino=788736 scontext=root:sysadm_r:sysadm_t tcontext=system_u:object_r:lib_t tclass=file

```

- Policy conception :

  - mitre.polgen : gen policies from executin traces of a program
  - sediff : semantic differences between policies
  - sechecker : search for errors in policies

- trace analysis
  - seaudit : view and analyse log messages
  - audit2why : explain selinux denial messages by converting the audit log into a human-readable text
  - audit2allow : help resolve selinux denial messages
