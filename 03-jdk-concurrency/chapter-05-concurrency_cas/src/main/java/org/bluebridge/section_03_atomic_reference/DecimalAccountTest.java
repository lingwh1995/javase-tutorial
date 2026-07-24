package org.bluebridge.section_03_atomic_reference;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * BigDecimal 账户测试
 *
 * @author lingwh
 * @date 2026/4/21 13:30
 */
public class DecimalAccountTest {

    /**
     * 测试不安全的 DecimalAccount
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
