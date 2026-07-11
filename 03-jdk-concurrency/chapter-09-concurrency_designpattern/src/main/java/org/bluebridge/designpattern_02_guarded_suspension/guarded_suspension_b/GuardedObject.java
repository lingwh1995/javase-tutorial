package org.bluebridge.designpattern_02_guarded_suspension.guarded_suspension_b;

/**
 * @author lingwh
 * @desc 关联对象-GuardedObject
 * @date 2025/7/9 00:00
 */
public class GuardedObject {
    // 结果
    private Object response;

    /**
     * 获取结果
     *
     * @param timeout 等待超时时间
     * @return
     */
    public Object get(long timeout) {
        synchronized (this) {
            // 假如开始时间为 15:00:00
            long begin = System.currentTimeMillis();
            // 经历的时间
            long passedTime = 0;
            while (response == null) {
                // 这一轮循环应该等待的时间（假设 timeout 是 1000，结果在 400 时被唤醒了，那么还有 600 要等）
                long waitTime = timeout - passedTime;
                // 经历的时间超过了最大等待时间, 退出循环
                if (waitTime <= 0) {
                    break;
                }

                try {
                    // 等待的时间应该 超时时间(timeout) - 经历的时间(passedTime)
                    this.wait(waitTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // 经历时间
                passedTime = System.currentTimeMillis() - begin; // 15:00:02
            }
            return response;
        }
    }

    /**
     * 产生结果
     *
     * @param response
     */
    public void complete(Object response) {
        synchronized (this) {
            // 给结果变量赋值
            this.response = response;
            this.notifyAll();
        }
    }
}
