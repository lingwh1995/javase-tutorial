package structure.proxy.synchronizedproxy;

/**
 * @author lingwh
 * @desc 任务接口
 * @date 2026/7/9 00:00
 */
public interface Task {

    void task();

    Thread createThread1();

    Thread createThread2();
}
