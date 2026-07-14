package org.bluebridge.cas_03_atomic_reference;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicReference;

/**
 * BigDecimal账户乐观锁实现
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class DecimalAccountSafeOptimisticLockCAS implements DecimalAccount {

    AtomicReference<BigDecimal> ref;

    public DecimalAccountSafeOptimisticLockCAS(BigDecimal balance) {
        ref = new AtomicReference<>(balance);
    }

    @Override
    public BigDecimal getBalance() {
        return ref.get();
    }

    @Override
    public void withdraw(BigDecimal amount) {
        while (true) {
            BigDecimal prev = ref.get();
            BigDecimal next = prev.subtract(amount);
            if (ref.compareAndSet(prev, next)) {
                break;
            }
        }
    }
}
