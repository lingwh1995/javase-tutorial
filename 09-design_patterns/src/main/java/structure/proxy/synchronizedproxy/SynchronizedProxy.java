package structure.proxy.synchronizedproxy;

/**
 * 同步代理
 *
 * @author lingwh
 * @date 2026/7/9 19:02
 */
public class SynchronizedProxy implements Task {

    private Task asynchronousTask;

    public SynchronizedProxy(Task asynchronousTask) {
        this.asynchronousTask = asynchronousTask;
    }

    /**
     * 使用join完成同步执行处理
     */
    @Override
    public void task() {
        try {
            Thread thread1 = createThread1();
            Thread thread2 = createThread2();
            thread1.start();
            // 调用join,等待线程一的方法执行完毕
            thread1.join();
            thread2.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Thread createThread1() {
        return asynchronousTask.createThread1();
    }

    @Override
    public Thread createThread2() {
        return asynchronousTask.createThread2();
    }
}
