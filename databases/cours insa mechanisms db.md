# Theorem de codd

there is an equivalence between sql, first order logic, and relational algebra

# Memory model

- ram contains a hash table whose values are a db cache
- the disk contains persistent db stuff
- the main performance issue is minimizing the number of disk accesses and trying to use ram as much as possible
- access memoire = access logique
- access sur le disque = access physique
- The dbms keep disk blocs corresponding to db records in memory to cache them in case they were needed again, a LRU cache is used
- hit ration = (nb lectures logiques - nb lectures physiques )/nb lectures logiques
- writing :

  - data is not directly written on disk
  - we wait a lil bit (au risque d'une panne et la perte de la MAJ)
  - logs containing sequential updates are written instantly to disk, to help us recover the db in case of a crash
  - le cache contient une copie de certains blocs de la db
  - le cache ( buy more ram, like google does to speed up their search engine) peut etre plus grand que la base sur le disque mais ce sert a rien
  - We can't have more physical reads than logical reads.
  - q6 : r2

- info is stored as a record, complementary info is stored in the header.
- in oracle, the header specifies l'address interne des records.(where to find it)
- l'address du record est composed d'une @ logique et une @ physique
- fichier = sequence de blocs :

  - qualite de fichier is conditioned with
    - good space utilisation (all blocs are ideally full)
    - stockage contigu
    - no chaining

- if no index, only way to look for a record is sequential search, binary search if the records are sorted.
- En general, les files handled by RDBMS' are simple sequential unordered files.

- q3 page 50 : R1, car quand le first bloc est plein, it will overflow, and to write to the file, we need to sequentially access the last available bloc, so its proportional to the size of the file

- index non dense (i guess its the same as b trees index)

  - an index entry is a ( key - address ) pair
  - sorted by key
  - l'index only references the first value of each bloc
  - adapted for range queries on keys, key prefix queries, key equality queries.
  - index is said to be dense if it contained all the entries

- we can create a non dense index on top of a dense index
- we can redo the operation above many times, we can stop when the root index only has one block
- we can create at most 1 non dense index on a file, to create many non indexes, if we had a dense index, that we can put another non dense index on top of it
- index non-dense is not that helpful if wanted, for example to query films where the titles is not matrix, because the index helps us find the bloc that contains matrix, if we want the others, we need to do a sequential search
