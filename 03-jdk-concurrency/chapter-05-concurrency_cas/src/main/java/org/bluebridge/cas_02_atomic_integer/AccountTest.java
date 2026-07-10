package org.bluebridge.cas_02_atomic_integer;

import org.junit.Test;

/**
 * @author lingwh
 * @desc 账户测试
 * @date 2026/7/9 00:00
 */
public class AccountTest {

    /**
     * 测试线程不安全的实现
     */
    @Test
    public void testAccountUnsafe() {
        Account.demo(new AccountUnsafe(10000));
    }

        /**
         * 测试线程安全的实现-悲观锁（synchronized）
         */
        @Test
        public void testAccountSafePessimisticLockSynchronized() {
                Account.demo(new AccountSafePessimisticLockSynchronized(10000));
        }

        /**
         * 测试线程安全的实现-乐观锁（CAS）
         */
        @Test
        public void testAccountSafeOptimisticLockCAS() {
                Account.demo(new AccountSafeOptimisticLockCAS(10000));
        }
}
