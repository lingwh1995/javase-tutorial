# 1.ReentrantLock特点
    可中断
    可以设置超时时间
    可以设置为公平锁
    支持多个条件变量
    可重入（与synchronized一样，都支持可重入（可重入是指同一个线程如果首次获得了这把锁，那么因为它是这把锁的拥有者，因此有权利再次获取这把锁如果是不可重入锁，那么第二次获得锁时，自己也会被锁挡住））
# 2.ReentrantLock常用方法
    lock()：得到锁继续往下执行，反之一直阻塞。不可被中断 
    lockInterruptibly()：得到锁则继续往下，反之一直阻塞。可被中断
    tryLock()：得到锁继续往下执行并返回true，反之也是继续往下执行并返回false。不可被中断
    tryLock(long time, TimeUnit unit)：逻辑与tryLock()，只是增加超时机制，超时得不到锁就返回false。可被中断