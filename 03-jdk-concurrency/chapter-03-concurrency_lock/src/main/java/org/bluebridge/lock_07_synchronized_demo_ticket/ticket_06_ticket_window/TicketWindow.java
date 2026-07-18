package org.bluebridge.lock_07_synchronized_demo_ticket.ticket_06_ticket_window;

/**
 * 售票窗口
 *
 * @author lingwh
 * @date 2026/7/13 19:02
 */
public class TicketWindow {

    private int count;

    public TicketWindow(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    /**
     * 为什么这个方法会出现线程安全问题？
     *
     * 下面代码中对 this.count 这个值既有读取操作，也有写入操作，所以这段代码是临界区，多线程情况下会发生竞态条件
     * 解决方案： 给this对象加锁
     *
     * @param amount
     * @return
     */
    public int sell(int amount) {
        synchronized (this) {
            // 临界区开始
            if (this.count >= amount) {
                this.count -= amount;
                return amount;
            } else {
                return 0;
            }
            // 临界区结束
        }
    }
}
