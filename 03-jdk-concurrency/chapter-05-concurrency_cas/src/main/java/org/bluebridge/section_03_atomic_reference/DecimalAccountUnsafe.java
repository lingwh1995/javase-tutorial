package org.bluebridge.section_03_atomic_reference;

import java.math.BigDecimal;

/**
 * BigDecimal 账户不安全实现
 *
 * @author lingwh
 * @date 2026/4/21 13:15
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
