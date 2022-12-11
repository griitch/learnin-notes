# What are indexes

A data structure that is built on top of a db table to increase the speed of data retrieval at the cost of additional storage and space to maintain.

Indexes are created on one or multiple columns

```sql
CREATE INDEX name_of_the_index ON table_name (id);
DROP INDEX name_of_the_index;
```

Once an index is created the system will update the index when the table is modified, and it will use the index if it judges that doing so will be more efficient that a full table scan.  
Determining wether to use the index or scanning the whole table is done at the query planning phase.  
We can use `EXPLAIN ANALYZE QUERY` on postgres for details and statistics.
example :

```sql
explain analyze select * from foo;

--                                            QUERY PLAN
-- -------------------------------------------------------------------------------------------------
--  Seq Scan on foo  (cost=0.00..34.00 rows=2400 width=6) (actual time=0.013..0.015 rows=4 loops=1)
--  Planning Time: 0.084 ms
--  Execution Time: 0.028 ms
-- (3 rows)
```

Indexes can also be used for updates and deletes and in join searches.
Creating an index can take some time, and can be done concurrently in postgres. while creating an index, reading is permitted, but writing is not.

# Index types

## B-tree indexes

Used by default in postgres, because it fits most situations, uses the b-tree data structure underneath, which can efficiently handle equality and range queries for data that can be sorted into some ordering.

Mainly used when indexed columns are involved in a comparison using < <= = >= >

The only type of indexes in postgres that can return sorted rows.

B-tree indexes can also used for pattern matching if the pattern was anchored at the beginning, for example `like foo%`, but not `like %bar%`

### Reminder on b-trees

[visualization](https://www.cs.usfca.edu/~galles/visualization/BTree.html)

self-balanced tree with O(log(N)) lookup, update, deletion time complexity and O(N) memory usage.

A b-tree of order m will have at most m-1 keys in each node.

A non-leaf node with k children will contain k-1 keys.

The root node has at least 2 children if it's not a leaf node.

Every non-leaf node (except root) has at least [m/2] children.

All leaves appear in the same level.

Inserting a node :

- find the leaf node in which they key will be inserted.
- if the node can still accommodate another item, put it there
- if the node is full( contains m-1 keys ), it will get split into 2 nodes, the smaller half in one node, the bigger hald in the other, the middle key will get promoted to its parent node, if the parent node is full, repeat.

- B vs B+ index : In a B tree, both the keys and the data records are stored in the tree nodes. This means that a B tree can be used to store and retrieve both the key and the associated data in a single operation. In a B+ tree, on the other hand, only the keys are stored in the tree nodes. The data records are stored in separate leaf nodes, which are linked together using pointers. This means that a B+ tree is better suited for storing and retrieving large amounts of data, because it allows for efficient sequential access to the data.

  - Another important difference between B trees and B+ trees is the way they handle null values. In a B tree, null values are treated like any other value, and can be inserted into the tree just like any other key. In a B+ tree, on the other hand, null values are not allowed, because they would break the tree's sorting order.

In general, B+ trees are considered to be more efficient than B trees for most database applications, because they are better suited for storing and accessing large amounts of data. However, B trees can still be useful in certain scenarios, such as when the data is highly variable and does not lend itself well to being stored in a B+ tree.

## Hash index

store 32 bits hash derived from the value of the indexed column, suited for equality comparison using =  
Does not support multi-column indexes.

### Gist, sp-gist, gin

did not bother reading about them for now

# Indexes on expressions

Useful to obtain fast access to tables based on the computed value. for example if we query a lor for the lowercase name of a students table like this :

```sql
SELECT * FROM students where lower(name) = 'gritch';
```

we might consider making an index on the lowercase names

```sql
CREATE INDEX lowercase_names ON students (lower(name));
```

# Partial Indexes

Indexes on a subset of a table that satisfy a predicate, used for only indexing the values that are important and to reduce the costs.  
Examples :

- Storing ip addresses, only index the ips that are outside our LAN

```sql
CREATE INDEX access_log_client_ip_ix ON access_log (client_ip)
WHERE NOT (client_ip > inet '192.168.100.0' AND
           client_ip < inet '192.168.100.255');
```

- exclude uninteresting values (unpaid orders for example)

```sql

CREATE INDEX unpaid_orders on orders
WHERE paid is not true;
```
