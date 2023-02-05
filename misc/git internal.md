https://missing.csail.mit.edu/2020/version-control/

# Git's data model

## Snapshots
git models the history as a collection of files and folders within a directory as a series of snapshots.  
in git's terminology, a file is a blob, which is a sequence of bytes.  
a directory is called a tree, it's like a map that maps names to either blobs or trees.  
a snapshot is the top-lvl tree that is being tracked, the one that has the .git folder.

## Relating snapshots
In git history is modeled with a directed acyclic graph of snapshots, each snapshot is a node of the graph, and each edge that points to a given node is a snapshot that preceded it.  

A node can have multiple precedents which is not possible in a linear history model. 
A snapshot is a commit.

## Data model as pseudo code
```ts
type blob = byte[]

type tree = map<string, tree | blob >

type commit = {
	parents : commit[],
	author: string,
	message : string,
	snapshot : tree
}
```
 
## Objects and content-addressing
an object is a blob, a tree, or a commit :
```ts
 type opject = blob | tree | commit
```

in git's data store, all object are content-addressed by their sha-1 hash
```py
objects = map<string,object>

def store(object):
	id = sha1(object)
	map.put(id,object)

def load(id):
	return objects.get(id)

```
this way, blobs, trees and commits are unified, they are all objects, when they reference other objects, they don't contain them in their on-disk representation, but have a reference to them by their hash.  

```sh
git cat-file -p ff3defa # ff3defa is the prefix of the hash of the latest commit

tree bdb9c915da718ba031729074012600bf47eb495c
parent e4067b71b33289a73b54aad946ba784116175ef1
parent afac7d1d605a953eebec1059f09f28929486f124
author griitch <omargrinat9@gmail.com> 1675613185 +0100
committer griitch <omargrinat9@gmail.com> 1675613185 +0100
```

we can use cat-file to see what in any of the referenced hashes in here
```sh
git cat-file -p bdb9c

100644 blob 316e65e2690c679508c4acbe183ce43257f6573f    .gitignore
040000 tree b7b1d1096bf8321c415e0751f6df5eb960b23edb    algorithms
040000 tree 3922fd2d213b7398084ea4946431561b87a7df6f    backend dev - networking
040000 tree 1dcd69c1d51dd7ece793152b08e7fbd4dcdd717b    books
040000 tree 29190e279d2454c7bece0bfde333bdbd78998b97    cours-insa
040000 tree b4e4dd9436494ad8ec705716838088d5fd10b22a    cryptography
040000 tree f613794ef32674d75158ba0b3dd1d0db78b1f2e3    databases
040000 tree 97d4477fd94e095a912d81d7f598bf35dd26f3b1    java
040000 tree fdf8eb2cfbbeec1969a2bc04d2aa5bb4fa70c416    js-ts
040000 tree 052047a517f269005e4ddeefef1ee33c86aa12f3    misc
040000 tree fb9323a76ac9fbdad6f48656c7ef1dddfe66e337    os
040000 tree 92f511a62363bf37aa62949a88f532465b5057a3    sec
```

```sh
git cat-file -p 316e # the prefix of .gitignore blob hash

# We get the contents of the file 
*.class
.vscode
```
## References 
References are human readable names for snapshots because humans cannot remember hashes.  
Unlike commits which are immutable, references can are mutable, they can be updated to point to a new commit.
Master and main and HEAD are examples of references.

## Repositories
a git repository is the data (objects) and the references. those 2 things are the only data stored on disk by git, and all the functionnality that git has to offer does something w these 2
