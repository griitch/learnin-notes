Transaction : indivisble unit of work, a set of operations that must either be all done, or none of them done at all.  

Atomicity : all ops in the transacation must be performed successfully or fail altogether.    

Consistency : data must be valid/consistent before and after the transactions, constraints and integrity checks should be respected.  

Isolation : according to wikipedia, concurrent execution of transactions should leave the db in the same state it would have been in had the transactions executed sequentially.

durability : once a transaction is over, the state of the db should be persisted for good, even in the case of a system failure, we mean by that storing in non volatile memory
