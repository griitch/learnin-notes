### Ways to start a thread
###### Extending Thread
```java

class Foo extends Thread {
    public void run() {
        //
    }
}

```

###### Implementing Runnable (functional interface)


###### Thread sync
```java

    Thread t1 = new Thread();
    t1.start(); 
    t1.join(); // wait for t1 to stop before continuing execution
    t1.join(1000); // wait for t1 to stop but for at 
                   // most 1000ms, if 1000ms have passed continue exec

```
###### Volatile keyword
When an attribute is marked as volatile, it will be
directly read from memory each time it's solicited.
This is helpful because on some systems, the jvm will
try to make some optimizations and cache the value
of an attribute if it's not being explicitly modified
in code of the `run` method, if the value of the attribute
got modified by calling the setter in another thread,
the thread might not see the latest value since it reads
from its cache. So the volatile keyword is for the rescue. 
It guarantees that all the threads see the same state.

###### Synchronized blocks/methods
```java
/***
 * Every object in java has an intrinsic lock (a mutex)
 * If we call a synchronized method, we need to acquire the 
 * intrinsic lock, but only one thread can acquire it at a time,
 * if a thread acquired the lock, and another thread at the same 
 * time tried to call a synchronized method, the call will be blocking
 * until the method returns from the first thread and releases the lock
 * */
public class App {
    public synchronized void increment() {
        c++;
    }


    /**
     * 
     * Synchronized blocks are like synchronized methods,
     * except that blocks take an object as a param, that 
     * object's intrinsic lock will be used instead of the 
     * whole outer object's lock to synchronize. 
     * 
     * In this example below, when the block in foo is executed,
     * it acquires the lock of obj1, no other thread can acquire
     * it until it returns. 
     * So basically, 2 threads can execute foo and bar at the same 
     * time, because they do not synchronize using the same lock,
     * but 2 threads (or more duh) can not simultaneously call foo !!
     * 
     * Using a sync method is equivalent to usin a sync block 
     * with `this` as the lock object
     * 
     */

    Object obj1 = new Object();
    Object obj2 = new Object();
    public void foo() {
        synchronized (obj1) {
        }
    }
    
    public void bar() {
        synchronized (obj2) {
        }
    }
}
```
###### Notify/wait
- Methods inherited from Object
- `wait()` causes the running thread to pause execution and gives
cpu time for other processes until notify is called
- `wait(long ms)` does the same as wait with no args, but will stop 
waiting after the time specified has passed
- The `wait` methods can only be called within synchronized blocks,
as they release the lock on the object so that other methods/blocks 
can acquire it.
- `notify` can only be called from synchronized blocks as well
- `sleep` par contre does not release the lock
- `notify` as its name applies, notifies the first thread that's
waiting to acquire the lock to wake up,`notify` does not release 
the intrinsic lock, it is actually released after the sync
block containing the call to `notify` exits.
- `notifyAll` notifies ALL the threads that are waiting to acquire
the lock










