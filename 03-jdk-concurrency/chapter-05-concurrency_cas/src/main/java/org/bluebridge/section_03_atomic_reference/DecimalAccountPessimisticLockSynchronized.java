package org.bluebridge.section_03_atomic_reference;

import java.math.BigDecimal;

/**
 * BigDecimal账户悲观锁实现
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class DecimalAccountPessimisticLockSynchronized implements DecimalAccount {

    private final Object lock = new Object();
    BigDecimal balance;

    public DecimalAccountPessimisticLockSynchronized(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public void withdraw(BigDecimal amount) {
        synchronized (lock) {
            BigDecimal balance = this.getBalance();
            this.balance = balance.subtract(amount);
        }
    }
}
