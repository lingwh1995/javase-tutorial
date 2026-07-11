package org.bluebridge.cas_03_atomic_reference;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lingwh
 * @desc BigDecimal账户接口
 * @date 2026/7/9 00:00
 */
public interface DecimalAccount {
    /**
     * 获取余额
     *
     * @return
     */
    BigDecimal getBalance();

    /**
     * 取款
     *
     * @param amount
     */
    void withdraw(BigDecimal amount);

    /**
     * 方法内会启动 1000 个线程，每个线程做 -10 元 的操作 如果初始余额为 10000 那么正确的结果应当是 0
     *
     * @param account
     */
    static void demo(DecimalAccount account) {
        long start = System.nanoTime();
        List<Thread> ts = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            ts.add(new Thread(() -> {
                account.withdraw(BigDecimal.TEN);
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
