package org.bluebridge.cas_02_atomic_integer;

/**
 * @author lingwh
 * @desc 线程不安全的实现
 * @date 2026/7/9 00:00
 */
public class AccountUnsafe implements Account {

    private Integer balance;

    public AccountUnsafe(Integer balance) {
        this.balance = balance;
    }

    @Override
    public Integer getBalance() {
        return balance;
    }

    @Override
    public void withdraw(Integer amount) {
        balance -= amount;
    }
}
