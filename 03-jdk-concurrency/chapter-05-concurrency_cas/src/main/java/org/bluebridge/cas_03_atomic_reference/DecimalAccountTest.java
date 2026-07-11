package org.bluebridge.cas_03_atomic_reference;

import java.math.BigDecimal;
import org.junit.Test;

/**
 * @author lingwh
 * @desc BigDecimal账户测试
 * @date 2026/7/9 00:00
 */
public class DecimalAccountTest {

    /**
     * 测试不安全的DecimalAccount
     */
    @Test
    public void testDecimalAccountUnsafe() {
        DecimalAccount.demo(new DecimalAccountUnsafe(new BigDecimal("10000")));
    }

    /**
     * 测试线程安全的实现-悲观锁（synchronized）
     */
    @Test
    public void testDecimalAccountPessimisticLockSynchronized() {
        DecimalAccount.demo(new DecimalAccountPessimisticLockSynchronized(new BigDecimal("10000")));
    }

    /**
     * 测试线程安全的实现-乐观锁（CAS）
     */
    @Test
    public void testDecimalAccountSafeOptimisticLockCAS() {
        DecimalAccount.demo(new DecimalAccountSafeOptimisticLockCAS(new BigDecimal("10000")));
    }
}
