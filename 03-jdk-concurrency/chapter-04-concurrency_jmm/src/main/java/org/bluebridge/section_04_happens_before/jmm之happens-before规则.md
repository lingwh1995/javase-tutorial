# 1.什么是happens-before规则?
    happens-before规则非常重要，它是判断数据是否存在竞争、线程是否安全的主要依据。JSR-133S使用
    happens-before概念阐述了两个操作之间的内存可见性。在JMM中，如果一个操作的结果需要对另一个操
    作可见，那么这两个操作则存在happens-before关系。
  
# 2.JSR-133中对happens-before关系定义?
    如果一个操作happens-before另一个操作，那么意味着第一个操作的结果对第二个操作可见，而且第
    一个操作的执行顺序将排在第二个操作的前面。
    
    两个操作之间存在happens-before关系，并不意味着Java平台的具体实现必须按照happens-before
    关系指定的顺序来执行。如果重排序之后的结果，与按照happens-before关系来执行的结果一致，那么
    这种重排序并不非法（也就是说，JMM允许这种重排序）
    
# 3.happens-before规则总结
    1.程序顺序规则：一个线程中的每一个操作，happens-before于该线程中的任意后续操作。
    2.监视器规则：对一个锁的解锁，happens-before于随后对这个锁的加锁。
    3.volatile规则：对一个volatile变量的写，happens-before于任意后续对一个volatile变量的读。
    4.传递性：若果A happens-before B，B happens-before C，那么A happens-before C。
    5.线程启动规则：Thread对象的start()方法，happens-before于这个线程的任意后续操作。
    6.线程终止规则：线程中的任意操作，happens-before于该线程的终止监测。我们可以通过Thread.join()方法结束、Thread.isAlive()的返回值等手段检测到线程已经终止执行。
    7.线程中断操作：对线程interrupt()方法的调用，happens-before于被中断线程的代码检测到中断事件的发生，可以通过Thread.interrupted()方法检测到线程是否有中断发生。
    8.对象终结规则：一个对象的初始化完成，happens-before于这个对象的finalize()方法的开始。