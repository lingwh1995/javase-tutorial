package org.bluebridge.unsafe_10_unsafe_atomic_integer;

import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;
import org.bluebridge.util.UnsafeAccessor;
import sun.misc.Unsafe;

/**
 * @author lingwh
 * @desc 基于Unsafe实现的原子整数
 * @date 2026/7/9 00:00
 */
public class UnsafeAtomicInteger {
    // 将value声明为volatile，因为乐观锁需要可见性。
    private volatile int value;
    // 需要Unsafe的cas本地方法实现操作。
    private static final Unsafe unsafe;
    // 偏移量，这两个变量很重要且通用、不可变，所以均声明为private static final
    private static final long offset;

    static {
        // 静态代码块初始化unsafe
        unsafe = UnsafeAccessor.getUnsafe();

        try {
            // 获取value在当前类中的偏移量
            offset = unsafe.objectFieldOffset(UnsafeAtomicInteger.class.getDeclaredField("value"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            // 待研究
            throw new Error(e);
        }
    }

    public UnsafeAtomicInteger() {}

    public UnsafeAtomicInteger(int value) {
        this.value = value;
    }

    public final int get() {
        return value;
    }

    public final boolean compareAndSet(int expext, int update) {
        return unsafe.compareAndSwapInt(this, offset, expext, update);
    }

    public final int getAndIncrement() {
        // 局部变量是必须的，因为多次从主存中读取value的值不可靠。
        int oldValue;
        while (true) {
            oldValue = value;
            if (unsafe.compareAndSwapInt(this, offset, oldValue, oldValue + 1)) {
                return oldValue;
            }
        }
    }

    public final int incrementAndGet() {
        int oldValue;
        while (true) {
            oldValue = value;
            if (unsafe.compareAndSwapInt(this, offset, oldValue, oldValue + 1)) {
                return oldValue + 1;
            }
        }
    }

    public final int getAndDecrement() {
        int oldValue;
        while (true) {
            oldValue = value;
            if (unsafe.compareAndSwapInt(this, offset, oldValue, oldValue - 1)) {
                return oldValue;
            }
        }
    }

    public final int decrementAndGet() {
        int oldValue;
        while (true) {
            oldValue = value;
            if (unsafe.compareAndSwapInt(this, offset, oldValue, oldValue - 1)) {
                return oldValue - 1;
            }
        }
    }

    public final int getAndUpdate(IntUnaryOperator operator) {
        int oldValue;
        int newValue;
        while (true) {
            oldValue = value;
            newValue = operator.applyAsInt(oldValue);
            if (unsafe.compareAndSwapInt(this, offset, oldValue, newValue)) {
                return oldValue;
            }
        }
    }

    public final int updateAndGet(IntUnaryOperator operator) {
        int oldValue;
        int newValue;
        while (true) {
            oldValue = value;
            newValue = operator.applyAsInt(oldValue);
            if (unsafe.compareAndSwapInt(this, offset, oldValue, newValue)) {
                return newValue;
            }
        }
    }

    public final int getAndAccumulate(int x, IntBinaryOperator operator) {
        int oldValue;
        int newValue;
        while (true) {
            oldValue = value;
            newValue = operator.applyAsInt(x, oldValue);
            if (unsafe.compareAndSwapInt(this, offset, oldValue, newValue)) {
                return newValue;
            }
        }
    }

    public final int accumulateAndGet(int x, IntBinaryOperator operator) {
        int oldValue;
        int newValue;
        while (true) {
            oldValue = value;
            newValue = operator.applyAsInt(x, oldValue);
            if (unsafe.compareAndSwapInt(this, offset, oldValue, newValue)) {
                return oldValue;
            }
        }
    }
}
