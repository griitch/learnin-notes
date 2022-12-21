# ACID Compliance

[good article](https://fauna.com/blog/database-transaction)

Transaction : indivisble unit of work, a set of operations that must either be all done, or none of them done at all.

Atomicity : all ops in the transacation must be performed successfully or fail altogether.

Consistency : data must be valid/consistent before and after the transactions, constraints and integrity checks should be respected.

Isolation : according to wikipedia, concurrent execution of transactions should leave the db in the same state it would have been in had the transactions executed sequentially.

durability : once a transaction is over, the state of the db should be persisted for good, even in the case of a system failure, we mean by that storing in non volatile memory

# More on Isolation

- Does the transaction see other changes made by other transactions, and is it okay ?
- There are levels to isolation, it's up to the rdbms to choose one by default, or for the dev to configure the rdbms to use a particular level

### Read phenomena

- according to the sql standard, its the issues that can occur when many people read and write to the same rows
  - dirty reads : when you see uncommitted rows in another transaction. as the name suggests, it's reading a dirty value, There is no guarantee the other transaction will commit. So when these are possible, you could return data that was never saved to the database
  - non-repeatable reads : when selecting the same row twice yields different results, because another transaction modified it, the difference between this and a dirty read is that the other transaction committed its changes, it's a legit update
  - phantom read : a special case of non-repeatable reads, happens when a tuple that matches the where clause in inserted or deleted by the other transaction,

### Isolation Levels

- Implemented by the dbs to deal with the read phenomena

#### Read uncommitted

- No isolation whatsoever, any change from other transactions are visible to the current transaction
- All the read phenomena can occur at this level

#### Read committed :

- most dbs implement it
- each query sees only the committed stuff
- No dirty reads, because each query is getting the new committed stuff
- all the other phenomena can occur

#### Repeatable reads :

- Only see the stuff that have been committed BEFORE starting the transaction
- reminds me of pessimistic concurrency control
- can be implemented with locks
- No dirty reads, no non-repeatable reads but phantom reads may occur

#### Serializable

- highest isolation level and the slowest in term of performance
- each transaction has to be almost serialized, which means they can pretty much get executed one after the other, and not in parallel.
- No read phenomena

# More about consistency

- Generally, 2 things are meant by consistency :
- **_Data Consistency_** :
  - Transactions should leave the db tables in a consistent state, whatever consistent state is that is defined by the user, for example : the picture.number_of_likes should be equal to count(\*) where picture_likes.pic_id = picture.id
  - Sometimes it matter, sometimes it does not, it all depends on the requirements of the app
- **_Read Consistency_** :
  - if T1 commits an update to X, and T2 reads X, it should get the latest value
  - Relational dbs are consistent in reads when there is one lone server
  - When using multiple replica servers both relational and noSql dbs do not guarantee it
  - Again, wether the inconsistency is accepted or not is up to the engineer and the requirements of the app, for example, on instagram getting the latest number of likes on a pic is not that important
  - NoSql dbs gave up consistency in favor of scalability
  - it makes sense, writes are done on the primary server, and reads can be done from either the primary or secondary server, it takes some time for the changes to propagate through the network, so if a client reads a value that has just been updated on the primary, he will likely get an old value
  - **_Eventual Consistent_** : stupid marketing term used by db vendors, because ofc data will be eventually consistent after the changes have been propagated
