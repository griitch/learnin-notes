- some security princips :
  - least privilege principle : an entity must be able to access only the information and resources that are necessary for its legitimate purpose
  - privilege segregation (role) :
  - CIA (confidentiality, integrity, availability)

[a nice article](https://www.linux.com/news/securing-linux-mandatory-access-controls/)

# DAC

- controle d'access discretionnaire
- Security model used by most mainstream os's
- enforces security by ownsership :
  - if a user owns a file he can set the permissions on it
- the owner of the system does not have total control over the system, the users do
- The biggest concern with the DAC model is the almighty root account who can control all files and processes
- programs have the same permissions as the users who launched them
- security levels depends on the security levels of the apps running

# MAC mandatory access control (a fine grain control)

- from what i've understood, MAC is basically the security admin defining exactly what subjects can do on a given resource, and the policies are centrally controlled by him, the users cannot override them like in DAC
- MAC approach limits the need for a root account, and shifts the power from the user accounts to the owner of the sys.
- Limit sys calls each user can make
- even root can't violate it
- zero trust in all apps

# SELinux

- security enhanced linux
- a kernel patch developed by the NSA
- implements MAC through the use of type enforcement, role based access control, and multi-level security
- no concept of a super user
- based on DTE which was licensed, and because licenses suck they decided to create their own
- other models : biba, blp , military tech that are proven to create a system that respects either integrity or confidentiality

  - blp : no reads up, no write downs. assures confidentiality

  - biba assures integrity : there are 2 types of resources and processes, privileged and non privileged processes(P anand NP), and critical and non critical resources (C and NC), no reads down, no write ups

# RBAC : role based access control

- [Dan Walsh article about selinux roles](https://danwalsh.livejournal.com/75683.html)

- because implementing MAC in unix is too complicated because the admin will have a HUGE set of rule for each user
- assigns roles to users
- there are too many rbac models :
  - for example, if a used had conflicting roles, one which permits an action and another that does not, what to do
  - in linux, the model is to have only one role per user, and only have `allow` rules instead of allow and deny

# Type enforement

- every object in the system is assigned a type, the sec admin then defines policies that indicate the access between pairs of types
- A type = A Security Context (SC)

# Multi-level security mls

- MLS allows for classified info to be shared at different security clearance levels, whatever that means...

# SELinux sert a quoi

- There is no secure program, selinux is only here to minimize the impact and not protect 100%

### Elements of Selinux

- security contexts :

  - subject : active entity
  - object : passive entity (socket file...)

- Operations : permissions on classes

  - Class : selinux defines classes for objects (file for files, dir for directories ...), [article](https://access.redhat.com/documentation/en-us/red_hat_enterprise_linux/5/html/deployment_guide/rhlcommon-section-0049)
  - permissions : read,write,unlink,accept... (syscalls)

- Interactions : an operation between 2 security contexts : SC1 => SC2 [class:permission]

- A selinux politic is the set of all allowed interactions

- politics are `factorized` : for example, system_u:system_r:httpd_t might apply for every type of http server, que ce soit apache, nginx or iis ...

- SElinux, XSELinux, dbos-selinux, iptables-selinux

## Security contexts

- a set of attributes, with 3 important ones :
  - identity : owner of the context
  - role : RBAC role
  - Type : for type enforcement, subjects have a `domain (scs)`, objects have a `type (sco)`
  - Format is identity:role:type, example : system_u:system_r:httpd_t
- Other attributes : category and sensibility

- each process is labeled by a CSS (context security subject)
- when a process is created he inherits the type of his parent, a transition can be made to change its type

- system objects are labeled by a CSO (context security object)
  - stored in the inode
  - par default, a file inherits the cso of the dir it was created inside

## Domains and types

- an id, declared and defined by the author of the politic
- no intrinsic meaning, makes no sense outside of the politic
- regroupe subjects and objects having the same permissions
- called type for objects, domain for subjects, but mean the same thing
- naming convention, for a given app/service foo

  - food_t : daemon type
  - foo_t : type for classic process
  - foo_exec_t : the executable file
  - foo_conf_t : conf file
    etc..

- an object/process may have one and only type, but the same type can be assigned to multiple objects/processes

## Transition operations

- by default, when a process is created it inherits the sec context of its parent
- a process can update an attribute of its context : it transits
- the transition can be :
  - automatic : When a process executes a file (init_t transits to sshd_t when executing /usr/bin/sshd )
  - manual : appel a setcon(sid id) in the code of the program

## 09/12/2022

# selinux policies :

- Set of access control rules

  - rules of authorisation
  - rules of types transition
  - audit rules

- selinux config can be :

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

- types of politics :
  - targeted : default policy for most Linux distributions, and it provides a targeted level of security by restricting access to certain system services and applications, the rest of the system is "unconfined"
  - strict : This policy provides a higher level of security by enforcing stricter rules for controlling access to system resources. It is more difficult to configure and maintain than the targeted policy, but it can provide better protection against security threats
- can also specify some audits : example, know if someone cat'd /etc/shadow

# Policy mode

- Enforcing mode : SELinux will strictly enforce the rules defined in its policies. This means that any access to a resource that is not specifically allowed by the policy will be denied. This is the default mode for SELinux, and it provides the strongest security by preventing any unauthorized access to resources on the system.
- Permissive : SELinux will still enforce its policies, but it will not deny access to any resources. Instead, it will log any violations of the policy, but it will allow the access to proceed. This mode can be useful for testing and troubleshooting, as it allows administrators to see if any violations of the policy are occurring without actually preventing access to the resources
- disabled : no policy is enforced, duh
