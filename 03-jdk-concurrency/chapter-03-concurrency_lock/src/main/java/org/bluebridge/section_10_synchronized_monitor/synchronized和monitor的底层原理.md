# 1.synchronized底层原理
    synchronized的底层原理是基于JVM的monitor对象实现的，每次创建对象时，JVM都会为这个对象关联一个monitor对象，这意味
    着任何一个java对象都有一个monitor对象与之关联
# 2.monitor实现同步原理（JVM通过管程实现同步的原理）
    1.任何一个java对象都有一个monitor与之关联，当拿到这个对象时，这个monitor对象就会处于锁定的状态，其他对象不会再获取
    2.synchronized在JVM里的实现都是基于进入和退出Monitor象来实现方法同步和代码块同步。虽然具体实现细节不一样，但是都
      可以通过成对的MonitorEnter和MonitorExit指令来实现。
# 3.同步方法和同步代码块通过monitor锁实现同步的原理及验证
## 3.1.同步代码块通过monitorEnter和monitorexit实现同步
    monitorEnter
      当monitor被占用时就会处于锁定状态，线程执行monitorenter指令时尝试获取monitor的所有权，过程如下：
        a. 如果monitor的进入数为0，则该线程进入monitor，然后将进入数设置为1，该线程即为monitor的所有者
        b. 如果线程已经占有该monitor，只是重新进入，则进入monitor的进入数加1；（体现可重入锁）
        c. 如果其他线程已经占用了monitor，则该线程进入阻塞状态，直到monitor的进入数为0，再重新尝试获取monitor的所有权
    monitorexit
      执行monitorexit的线程必须是object_ref所对应的monitor的所有者。指令执行时，monitor的进入数减1，如果减1后进入数为
      0，那线程退出monitor，不再是这个monitor的所有者。其他被这个monitor阻塞的线程可以尝试去获取这个 monitor 的所有权。
    如何验证
        javac .\MonitorSynchronizedMethodTest.java -> javap -v .\MonitorSynchronizedMethodTest.class -> 可以查看到编译结果中有monitorEnter和monitorexit
## 3.2.同步方法通过添加 ACC_SYNCHRONIZED 实现同步
    原理:
        当方法调用时，调用指令将会检查方法的 ACC_SYNCHRONIZED 访问标志是否被设置，如果设置了，执行线程将先获取monitor，
        获取成功之后才能执行方法体，方法执行完后再释放monitor。在方法执行期间，其他任何线程都无法再获得同一个monitor对象。
    如何验证
        javac .\MonitorSynchronizedCodeBlockTest.java -> javap -c .\MonitorSynchronizedCodeBlockTest.class -> 可以查看到编译结果中多了ACC_SYNCHRONIZED这个标识符
# 4.ObjectMonitor底层C++源码
    class ObjectMonitor {
        public:
        enum {
        OM_OK,                    // no error
        OM_SYSTEM_ERROR,          // operating system error
        OM_ILLEGAL_MONITOR_STATE, // IllegalMonitorStateException
        OM_INTERRUPTED,           // Thread.interrupt()
        OM_TIMED_OUT              // Object.wait() timed out
        };
        ...
        ObjectMonitor() {
        _header       = NULL; // 对象头
        _count        = 0; // 记录该线程获取锁的次数
        _waiters      = 0, // 当前有多少处于wait状态的thread
        _recursions   = 0; // 锁的重入次数
        _object       = NULL;
        _owner        = NULL; // 指向持有ObjectMonitor对象的线程
        _WaitSet      = NULL; // 存放处于wait状态的线程队列
        _WaitSetLock  = 0 ;
        _Responsible  = NULL ;
        _succ         = NULL ;
        _cxq          = NULL ;
        FreeNext      = NULL ;
        _EntryList    = NULL ; // 存放处于block锁阻塞状态的线程队列
        _SpinFreq     = 0 ;
        _SpinClock    = 0 ;
        OwnerIsThread = 0 ;
        _previous_owner_tid = 0;
    }
    关键属性：
        _count，记录该线程获取锁的次数（就是前前后后，这个线程一共获取了多少次锁）；
        _recursions ，锁的重入次数（道格李Lock下的state）；
        _owner 对应 The Owner，含义是持有ObjectMonitor对象的线程；
        _EntryList 对应 Entry List，含义是存放处于block锁阻塞状态的线程队列（多线程下，竞争锁失败的线程会进入EntryList队列）；
        _WaitSet 对应 Wait Set，含义是存放处于wait状态的线程队列（正在执行代码的线程遇到wait()，会进行WaitSet队列）。
# 5.ObjectMonitor中的队列
    ObjectMonitor中有两个队列，_EntryList 和_WaitSet ，用来保存ObjectWaiter对象列表（ 每个等待锁的线程都会被封装成ObjectWaiter对象 ），_owner指向持有ObjectMonitor对象的线程，当多个线程同时访问一段同步代码时：
        首先会进入 _EntryList 集合，当线程获取到对象的monitor后，进入 _Owner区域并把monitor中的owner变量设置为当前线程，同时monitor中的计数器count加1；
        若线程调用 wait() 方法，将释放当前持有的monitor，owner变量恢复为null，count自减1，同时该线程进入 WaitSet集合中等待被唤醒；
        若当前线程执行完毕，也将释放monitor（锁）并复位count的值，以便其他线程进入获取monitor(锁)；
        同时，Monitor对象存在于每个Java对象的对象头Mark Word中（存储的指针的指向），Synchronized锁便是通过这种方式获取锁的，也是为什么Java中任意对象可以作为锁的原因，同时notify/notifyAll/wait等方法会使用到Monitor锁对象，所以必须在同步代码块中使用。监视器Monitor有两种同步方式：互斥与协作。多线程环境下线程之间如果需要共享数据，需要解决互斥访问数据的问题，监视器可以确保监视器上的数据在同一时刻只会有一个线程在访问。