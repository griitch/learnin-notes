# Indexes

- if no index, only way to look for a record is sequential search, binary search if the records are sorted.
- En general, les files handled by RDBMS are simple sequential unordered files. so the sorting is sort of out of the question
- an index entry is a ( key - address ) pair

### Non dense index

- index non dense (reminds me of b trees index) is reminiscent of a real dictionary, each page is indexed by the first word it contains
- Data file (file that contains the blocs) sorted by key
- l'index only references the first value of each bloc
- adapted for range queries on keys, key prefix queries, key equality queries.
- we can create at most 1 non dense index on a file

![non dense index](http://sys.bdpedia.fr/_images/indexNonDense.png)

### Dense index

- reminds me of hash indexes
- The index is said to be dense if it contained all the entries of an indexation key, it's like a [key,address] ordered map (the keys are sorted ) for the whole table
- the data file is not sorted though
- only suited for key equality searches efficiently
- interval searches cause random access which is quite penalizing in terms of performance compared to a sequential access

### Multi level indexes

- we can create a non dense index on top of a dense index

![multi lvl index](http://sys.bdpedia.fr/_images/index2Niveaux.png)

- we can redo the operation above many times, we can stop when the root index only has one block

![multi lvl index max](http://sys.bdpedia.fr/_images/indexMultiNiv.png)

- index non-dense is not that helpful if we wanted for example to query films where the titles is not matrix, because the index helps us find the bloc that contains matrix, if we want the others, we need to do a sequential search

# B+ index

![btree](http://sys.bdpedia.fr/_images/arbreBAnnees.png)

- [Hussein nasser b vs b+](https://www.youtube.com/watch?v=UzHl2VzyZS4)
- only leaves contain pointers to data
- the data file is not ordered, insertion is done at the last bloc
- the blocs are always at least half-full
- channing between leaves is for interval searches, to find the values in [a,b] go to the leave containing a, and keep on following the links till u reach a key > b

# B tree index

- pointers to data can be found in internal nodes as well as leaves

# Hash index

- more performant on key equality search, and btw its only suited for that lol
- Not as much space overhead as a b-tree
- but but hard to reorganize

## Static hashing

- Storage (whatever that is) is divided into N fragments (buckets) which are a sequence of blocs
- called static because the number of buckets is fixed
- a hash function will take as input the indexing key and will return the @ of the bloc, the only memory usage will be the table that for each index i, contains the memory @ of the i'th bloc
- main difficulties :
  - the hash func needs to yield uniform results
  - we can't change the place of a record, so we need to link blocs when they overflow and it will decrease performance

## Extensible hashing

- The hashing func is still fixed, but the number of bits that we care about from the hash is what changes
- Only the n first bits of the hash are used to index records, n is always a power of 2.
- n starts with 2, we can only have 4 fragments in the index 00 and 01 and 10 and 11
- when a fragment becomes full, n gets doubled, and we allocate an extra fragment, we only need to reorder items from the full fragment and put them in the new fragment if the hash matches. the rest of the entries that point to blocs that are not full yet will be pointing to an already existing bloc
- the table grows exponentially, which can cause quite a lot of memory overhead

## Linear hashing

todo later
