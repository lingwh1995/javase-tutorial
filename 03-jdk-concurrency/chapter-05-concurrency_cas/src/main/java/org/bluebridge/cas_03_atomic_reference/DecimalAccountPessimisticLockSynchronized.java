package org.bluebridge.cas_03_atomic_reference;

import java.math.BigDecimal;

/**
 * @author lingwh
 * @desc BigDecimal账户悲观锁实现
 * @date 2026/7/9 00:00
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
