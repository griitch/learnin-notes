In dbms, concurrency control means how to deal with concurrent access to the same db row.  
Concurrent reads are fine, but when a transaction updates the row, what the other transactions see is what causes problems

# Optimistic Concurrency Control

No locks, before committing a transaction, verify if the values of the rows are equal to the values they had in the beginning of the transaction, if they were different rollback.
used in mongodb, and in web resources using http e-tags.

# Pessimistic Concurrency control

When writing to a row, put a mutex lock on it to prevent any reading/writing to the row while the transaction si not finished, and release it when it's done.
Could cause problems if a huge number of transactions are trying to access the same row, but that's unlikely to happen and smells like bad database design
used in postgres and maybe other relational dbs.
