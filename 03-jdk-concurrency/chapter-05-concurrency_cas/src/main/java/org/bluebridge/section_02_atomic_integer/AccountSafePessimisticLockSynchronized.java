package org.bluebridge.section_02_atomic_integer;

/**
 * 线程安全的实现-悲观锁（synchronized）
 *
 * @author lingwh
 * @date 2026/4/21 12:00
 */
public class AccountSafePessimisticLockSynchronized implements Account {

    private Integer balance;

    public AccountSafePessimisticLockSynchronized(Integer balance) {
        this.balance = balance;
    }

    @Override
    public synchronized Integer getBalance() {
        return balance;
    }

    @Override
    public synchronized void withdraw(Integer amount) {
        balance -= amount;
    }
}
