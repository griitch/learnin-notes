# Theorem de codd

there is an equivalence between sql, first order logic, and relational algebra

# Memory model

## Reading

- access memoire = access logique
- access sur le disque = access physique
- the disk contains persistent db stuff
- db files are stored in persistent storage for 2 main reasons :

  - the size of the db can be too big for ram
  - duh, because ram is volatile

- A db module, lets call it AM for access manager, is what manages ram and disk usage for a dbms
- when reading a bloc, the AM checks if it exists in ram, if so he straight up reads the cached bloc, else he fetches it from the disk
- ram contains a hash table whose values are a db cache, why a hash table ? because it's very fast to determine wether a bloc was in ram or not with it !! just check the key (idk how is calculated tho) exists in the table or not.
- the main performance issue is minimizing the number of disk accesses and trying to use ram as much as possible
- The dbms keep disk blocs corresponding to db records in memory to cache them in case they were needed again, a LRU cache is used
- hit ratio = (nb lectures logiques - nb lectures physiques )/nb lectures logiques

## writing :

- data is not directly written on disk
- we wait a lil bit to update in batches (au risque d'une panne et la perte de la MAJ)
- logs containing sequential updates are written instantly to disk, to help us recover the db in case of a crash
- writes to disk are done when :
  - the cache buffer is full
  - the server stops
  - the db is inactive
- le cache contient une copie de certains blocs de la db (lru), each bloc contains many tuples
- le cache ( buy more ram, like google does to speed up their search engine) peut etre plus grand que la base sur le disque mais ca sert a rien
- We can't have more physical reads than logical reads.
- q6 : r2

- info is stored as a record (aka a tuple), complementary info is stored in the header.
- in oracle, the header specifies l'address interne des records.(where to find it)
- l'address du record est composed d'une @ logique et une @ physique
- fichier = sequence de blocs :

  - qualite de fichier is conditioned with
    - good space utilisation (all blocs are ideally full)
    - stockage contigu
    - no chaining

## Data locality

- The principle of data locality : the data that is used by an app forms a well defined group
  - spatial locality : if d is used by app A, there are high chances that data close to d (in terms of memory addresses ) is also used by A
  - temporal locality : if A uses d, there is a high chance it will use it again shortly
  - reference locality : if d1 references d2, and A uses d1, it will most likely use d2
- how do caches take advantage of locality ?
  - spatial locality : when reading bloc D, might as well read D+1 and D+2 ... etc !!, and when saving to persistent storage (this only applies for hard drives...) stock the blocks near to one another
  - temporal locality : scheduling,

# Db records, blocs, and files

- for an os, a file is a sequence of bytes, files handle by dbms are more structured, they are made of blocs, which in turn contain records which represent physically the entities of the db, which are usually tuples.
- the records should be indexable, by that we mean they have _fixed_ addresses than they can be referenced by
- at first glance, it seems like the size of a record is the sum of the sizes of the attributes, but it's more subtle than that, as a matter of fact a record's size might change overtime (for example, updating a value from null to something other than null) :

  - there are some sql types that have a variable size. for example strings, ever wondered why the max size of a varchar is 255 ? because the dbms stores the size of the text as a 8 bit integer in the first byte of the text. another approach to bypass this limitation is to use a termination character like '\0' in c.
  - some fields might be null, and how to deal with them is up to the dbms, oracle for example give null fields a size of zero, another approach is using a bitmask in the record header, for example 110 might indicate that the first and second fields are not null, but the third is
  - Why all of this ? to know where a record starts and ends in a sequence of bytes, and also, to know where each fields begins and ends within the record itself

- the header of a record : contains the size of the record, a pointer to the schema to know the shape of the record, an update/creation timestamp...

## Blocs

- problem : we want records to have fixed addresses, but the @'s might need to change, because of the variable length records, for example, if updating a record containing some text to make the txt bigger but there is no more space in the bloc, we might need to change the location of the record to a bloc w more space
- a solution used in oracle : 2 adressages :
  - adressage global fixe : which bloc is the record contained in, a physical address fixed and used for direct access (indexation)
  - adressage interne au bloc : where to find the record inside the bloc : a logical address
  - the header of the bloc contains a hash-table like structure that maps records (keys i guess ?) to where the tuple data is stored
  - when updating a record so that his size increases there 2 possibilities :
    - there is space left in the bloc : the bloc is reorganized so the whole record can fit it
    - there is no more space :
      - chaining : the _key_ in the header now points to a location in another bloc that have space innit, should be avoided because it create performance bottlenecks
      - fragmentation : store a part in the current block, and the rest in another bloc, a pointer _in the record_ itself will point to the fragment stored in the other bloc

![](http://sys.bdpedia.fr/_images/blocenr3.png)

## Files

- a file is a sequence of blocs containing data of the same obj (table, index)
- the quality of a file is determined by :
  - good space usage : most of the blocs are full
  - stockage le plus contigu possible sur le disc
  - Not much chaining between blocs
- when writing to a file, we just write to the last available bloc, as simple as that
- a file can be ordered, if not, the only way to find a record is sequential search (if there was not an index)
- the dbms must efficiently handle insertion, searching, deletion and updates in a file in terms of memory/space complexity
- space efficiency of a file can be measured by the ratio of the number of blocs used by a file and the sufficient number of blocs
- time efficiency is measured by the number of blocs read to perform an action, for example. linear for an unordered table, and logarithmic for a b-tree index
- how are CRUD ops handled in simple sequential files :
  - READING : sequential search
  - updates, creation and deletion : We need to find a bloc with enough space for our updates, because we cannot always use the last bloc of the file, because by doing so we will not be using the blocs that became empty through deletion and updates of records, we need an auxiliary structure that helps us find quickly blocs that are not full, a doubly linked list : when a bloc is no longer full, it gets inserted at the tail of the list, and when a bloc gets full, it gets removed from it, i suppose we insert at the end of the file when the list is empty
- q3 page 50 : R1, car quand le first bloc est plein, it will overflow, and to write to the file, we need to sequentially access the last available bloc, so its proportional to the size of the file
