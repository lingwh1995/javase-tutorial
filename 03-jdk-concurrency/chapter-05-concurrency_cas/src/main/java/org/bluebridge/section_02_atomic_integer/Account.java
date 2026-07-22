package org.bluebridge.section_02_atomic_integer;

import java.util.ArrayList;
import java.util.List;

/**
 * 账户接口
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
interface Account {

    /**
     * 获取余额
     * 
     * @return
     */
    Integer getBalance();

    /**
     * 取款
     * 
     * @param amount
     */
    void withdraw(Integer amount);

    /**
     * 方法内会启动 1000 个线程，每个线程做 -10 元 的操作
     * 如果初始余额为 10000 那么正确的结果应当是 0
     */
    static void demo(Account account) {
        long start = System.nanoTime();
        List<Thread> ts = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            ts.add(new Thread(() -> {
                account.withdraw(10);
            }));
        }
        ts.forEach(Thread::start);
        ts.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long end = System.nanoTime();
        System.out.println(account.getBalance() + " cost: " + (end - start) / 1000_000 + " ms");
    }
}
