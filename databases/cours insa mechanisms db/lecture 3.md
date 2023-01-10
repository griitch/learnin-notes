# Optimisation and execution of requests

# this part is very complicated

- The dbms transforms raw sql to a form called the execution plan
- the execution plan is a tree of `operators` that exchange data
- the operators are just black box software components that take a stream of data, do something with it, outputs the result.
- just like any sql request can be written user relational algebra operators, it can also be expressed with a tree of dbms operators
- the operators :
  - have a generic format (iterators)
  - do a specific task
  - can be blocking or non blocking

## Modes (of what ? idk)

### Naive mode : materialisation

The operator calculates all of the result and puts it in ram and then transfers it to to the client app as a big batch.  
This is problematic because it uses a lot of ram and there is a latency before getting the first result.

### better solution : pipelining

No temporary storage, the result is produced on demand, for example, a select \* statement, instead of giving the app the whole result set at once, give it one tuple at a time.  
Example : a full-scan operator

```java
// select * from T
Cursor c = new FullScan(T);
Nuplet n = c.next();

while(n != null) {
    // do something with the n-uplet
    n = c.next();
}
c.close();

```

### Blocking operators

Not all operators can work in pipelining mode.  
min, avg, max for example, can't yield any result before scanning the full table.  
Blocking operators needs to write their intermediary result in ram or disk before returning it.

### response and execution time

- Response time : latency of getting the first tuple of the result set
- exec time : time required to get the whole set

### Operator implementation

- Operators consume tuples coming from other operators.
- Operators produce tuples for client apps or for other operators
  All operators have the simple interface as an iterator

```java

interface Operator {

    void open() {
        // initialises the operator
        // puts the cursor at the beginning of the result set
    }
    Cursor next(){
        // advances the cursor by one position
    }
    void close() {
        // frees up the resources
    }
}

```

## Examples

#### b+tree Index scan operator

```ts

interface indexScanner implements Operator {

    entry;
    key;

    open(key_to_look_for) {
        key = key_to_look_for
        // traversal of the tree from the root till a leaf
        bloc = index.root;
        while(!bloc.isLeaf()) {
            for (entry e in bloc.entries) {
                if(e.key > key)
                    break;
            }

            bloc = blocAtAddress(e.address);
        }

        entry = bloc.firstOccurrence(key);
    }

    next() {
        // sequential traversal of leaves
        if(entry.key == key ) {
            addy = entry.address
            entry = entry.next()
            return addy;
        } else {
            return null;
        }
    }

}

```

### Direct access operator

```python
class DirectAccessOp(address):
    def open():
        return # does nothing

    def next(address):
        return tupleAt(address)

    def close():
        #
```

### Search with index

- it's the chaining of the 2 previous operators
-
