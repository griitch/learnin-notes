# DB Failures modes
the various ways in which a db can experience failures or errors that impact its operation, availability and data integrity.

## Read contention
Occurs when multiple clients or processes try to read from the same location @ the same time, causing delays or errors

## Write contention
same and read contention but with writes

## Thundering herd
- occurs when a large number of processes or clients are competing for a shared resource all of a sudden
- typically happens when the clients or processes are waiting for for an event to occur, such as data becoming available, when the event occurs, all the clients/processes who were waiting for the event wake up and rush to handle it simultaneously

## Cascade
A failure in one part of the db causes a chain reaction that leads to the failure of other db parts

## Deadlock
2 or more transaction waitin for one another to finish (to release a lock on a resource)

## Corruption
Corrupted data leads to unexpected results when reading ot writing to the db

## Network, software and hardware failures

