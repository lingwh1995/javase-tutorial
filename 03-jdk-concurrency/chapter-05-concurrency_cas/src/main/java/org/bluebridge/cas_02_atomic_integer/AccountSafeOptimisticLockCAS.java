package org.bluebridge.cas_02_atomic_integer;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lingwh
 * @desc 线程安全的实现-乐观锁（CAS）
 * @date 2026/7/9 00:00
 */
public class AccountSafeOptimisticLockCAS implements Account {

    private AtomicInteger balance;

    public AccountSafeOptimisticLockCAS(Integer balance) {
        this.balance = new AtomicInteger(balance);
    }

    @Override
    public Integer getBalance() {
        return balance.get();
    }

    @Override
    public void withdraw(Integer amount) {
        // 需要不断尝试，直到成功为止
        while (true) {
            // 比如拿到了旧值 1000
            int expectedValue = balance.get();
            // 新的值 = 在这个基础上 1000-10 = 990
            int newValue = expectedValue - amount;
            if (balance.compareAndSet(expectedValue, newValue)) {
                break;
            }
        }
        // 可以简化为下面的方法
        // balance.addAndGet(-1 * amount);
    }
}
