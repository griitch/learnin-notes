# Windows domains 
- A windows domain: a group of users and computers under the administration of a certain business / entity
- Active directory : a repository in which all the management of the domain components in centralized
  - what do we manage ? :
    - Centralized identity management 
    - Security policies
- Domain controller : the server that runs the active directory service
- example of active directory : pc's at INSA

# Active directory domain service (AD DS)
The DS is the core of any windows domain. it's a catalogue that holds info about all the objects that exist on the network. among the objects supported by AD, we have users,groups,machines,printers,shares and many others.
- users :
  - most common object types
  - Aka security prinipals, means they can be authenticated by the domain and can be assigned privileges over resources like files or printers and disk space ..
  - users can be used to represent 2 types of entities : humans and services (like mssql or apache, but are different from regular human users as they only have the privilege to run their specific service)

- machines :
  - for every pc that joins the AD domain, a machine object is created
  - are also considered security princpals and are assigned an account like a regular user but have limited rights
  - the machine accounts are local admin accounts on the assigned pc. generally not supposed to be accessed by anyone except the pc itself, but as with any other acc, if u have the passwd u can log in.
  - the machine's acc name follows a convention, it's the machine name followed by a dollar sign, griitch's account name is griitch$
  
- security groups : 
  - Like the roles you're familiar with in spring security
  - members of the same group have the same rights and privileges.
  - groups can have users and machines as members, and can include other groups as members as well. 
  - Several groups are created by default in a domain, for example :
    - domain admins : group of ppl with admin privs over the entire domain
    - server operators : can adminster the domain controllers but cannot change any administrative group memberships
    - backup operators : allowed to access any file regardless of the permissions, as the name suggests, used to perform data backups
    - account operators : can create / modify other accounts in the domain.
    - domain users : all existing user accounts in the domain
    - domain computers : ...
    - domain controllers : all the existing DC's on the domain.


- The objects are organized in **Organizational Units**, which are container objects that classift users and machines, mainly used to define sets of users with similar policing requirement.
- the OUs are hierarchical : example students OU, can contain 2 sub OUs : MRI and STI, which contain 3 sub OUs each, 3a,4a and 5a.

- A part from the admin created OUs, some default containers are automatically created by windows such as :
  - Built in : contains default groups
  - computers : mahines that join the network will be put in it by default
  - domain controllers : default OU that contain the DCs
  - users : default users and groups
  - managed service accouts : accounts used by services 

-Security groups vs OU
  - OU : assemble objects that are logically tied to policies related to their role in thenterprise, a user can only be a member of a sinle OU at a time.
  - Sec groups : used to grant permissions over resources, a user can be in multiple groups.


# Delegation
- We can give some users some control over some OUs, for example we might give an IT specialist the possibility of resetting passwords of users in the finance OU
- there are many standard tasks such as resetting passwords, being able to read users info, account creation and management, group creation/mgmt, 
- But the admin can also delegate some custom tasks that he creates.

# Managing computers in AD
- By default all the machines that joing a domain except the DCs will be put in a container called Computers. 
- A good practice is to segregate computers according to their use. In general devices are divided into at least 3 categories :
  - workstations : machines each user in the domain will be logging into. no privileged user logs into them
  - servers : servers.. duh
  - domain controllers : the most sensitive devices in the network as they contain hashed passwords and sensitive infos.

# Group policies
- policies for each OU are managed through *Group Policy Objects*, (GPOs)
- GPOs are a set of settings that can be applied to OUs.
- a GPO applied to an OU is also applied to it children (unless overriden)

# Auth methods
2 main methods, kerberors and netNTLM, the latter is considered obsolete.
### Kerberos
- the default protocol in recent domains.
- Uses tickets, which are like a proof of a previous authentication
- it goes like :
  - the user sends their username and a timestamp encrypted using a key derived from their password to the **Key Distribution Center (KDC)**, a service usually installed on te DC in charge of creating kerberos tickets on the network.
  - The KDC will create and send back a **Ticket granting ticket (TGT)**, which is like a jwt refresh token, it allows the user to request additional tickets to access services. Along with the TGT, a **session key** is given to the user, which they will need to send in the following requests. The TGT is encrypted with a key generated from the **krbtgt** (the account used for Microsoft's implementation of kerberos)account password hash (its like a private key)
  - when a user wants to connect to a service on the network, they will use their TGT to ask the KDC for a TGS **(Ticket Granting service)**. TGSs are tickets that allow connection only to the specific service they were created for. To request a TGS the user sends their userna,e, a timestamp encrypted using the session key, the TGT and a **Service principal name (SPN)**, which indicates the service and server name we intend to access
  - the server responds with a TGS along with a **Service Session Key**, which we will need to authenticate to the service. the TGS is encrypted using a key derived from the Service owner hash. THe service owner is the user/machine that the service runs under. the TGS contains a copy of the Service Session Key so that the service owner can access it by decrypting the TGS.
  - the TGS can then be sent to the desired service to authenticate and establis a cnx. The service will decrpyt the TGS and validate the service session key using its password hash derived key.


# Trees, Forests and Trusts
As companies grow, so do their networks, so they might add more domains to their network.
### Trees
- like dns 
- used to group domaines that share the same namespace (like a dns tld)
  - exmaple : griitch.local is the root, foo.griitch.local and bar.griitch.local are 2 subtrees of it.
  - foo and bar have their own DCs that manage their own resources.
### Forests
- The union of several trees with different namespaces is called a forrest
### Trust relationships
- normally, users can only access resources from their own domain. but they can use resources from other domains using trust relationships.
- a trust relationship can be a one-way relationship
  - if domain AA trusts domain BB, BB users can access AA resources.
- a trust relationship can also be bi-directional 

