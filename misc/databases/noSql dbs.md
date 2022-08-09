Some of the most popular no sql dbs and when it's suitable to use them

# Apache cassandra

[Cassandra basics](https://cassandra.apache.org/_/cassandra-basics.html)

### Features

- distributed : data replicated across multiple machines, but appears as a whole system to the user
- AP (available and partition tolerant ) in the CAP theorem but can be tuned for consistency
- [eventually consistent](https://en.wikipedia.org/wiki/Eventual_consistency)
- fast writing (does not write to disk right away, periodically flushes writes in memory to the disk)
- masterless architecture (any node in the db can provide the same functionality as the rest of the nodes) unlike other dbs like PostGres or MySql which use a master node for writing, and slave nodes for reading.
- [columnar database](https://www.youtube.com/watch?v=8KGVFB3kVHQ)

### How does it store data

Cassandra stores data in `partitions` (analog to tables), related data is stored in the same partition.  
For best performance, partitions should be small and bounded(in size), and each query should read only one partition.

# MongoDb

### Features

- document oriented
- schema-less

### How does it store data

In a binary format called bson, short for binary json
