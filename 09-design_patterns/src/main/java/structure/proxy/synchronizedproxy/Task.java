package structure.proxy.synchronizedproxy;

/**
 * 任务接口
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public interface Task {

    void task();

    Thread createThread1();

    Thread createThread2();
}
