package org.bluebridge.cas_03_atomic_reference;

import java.math.BigDecimal;

/**
 * @author lingwh
 * @desc BigDecimal账户不安全实现
 * @date 2026/7/9 00:00
 */
public class DecimalAccountUnsafe implements DecimalAccount {
    BigDecimal balance;

    public DecimalAccountUnsafe(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public void withdraw(BigDecimal amount) {
        BigDecimal balance = this.getBalance();
        this.balance = balance.subtract(amount);
    }
}
