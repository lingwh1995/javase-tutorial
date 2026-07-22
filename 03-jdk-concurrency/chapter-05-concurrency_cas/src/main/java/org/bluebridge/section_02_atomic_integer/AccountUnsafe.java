package org.bluebridge.section_02_atomic_integer;

/**
 * 线程不安全的实现
 *
 * @author lingwh
 * @date 2026/4/21 19:02
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
