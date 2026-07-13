package org.bluebridge.cas_01_helloworld;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicInteger用法测试
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class AtomicIntegerTest {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);

        // get()：获取当前值
        System.out.println("初始值 (get): " + atomicInteger.get());

        // set(int newValue)：设置当前值
        atomicInteger.set(10);
        System.out.println("设置新值后的值: " + atomicInteger.get());

        // incrementAndGet()：自增操作并返回新的值
        System.out.println("自增后的值 (incrementAndGet): " + atomicInteger.incrementAndGet());

        // decrementAndGet()：自减操作并返回新的值
        System.out.println("自减后的值 (decrementAndGet): " + atomicInteger.decrementAndGet());

        // getAndIncrement()：获取当前值然后自增
        System.out.println("先获取当前值然后自增后的值 (getAndIncrement): " + atomicInteger.getAndIncrement());

        // getAndDecrement()：获取当前值然后自减
        System.out.println("先获取当前值然后自减后的值 (getAndDecrement): " + atomicInteger.getAndDecrement());

        // getAndAdd(int delta)：获取当前值然后加上delta
        System.out.println("先获取当前值然后加上delta (5): " + atomicInteger.getAndAdd(5));

        // addAndGet(int delta)：加上delta并返回新的值
        System.out.println("加上delta后的值 (5) (addAndGet): " + atomicInteger.addAndGet(5));

        // compareAndSet(expectedValue, updateValue)：如果当前值等于expectedValue，则将其设置为updateValue
        boolean flag = atomicInteger.compareAndSet(20, 22);
        System.out.println("compareAndSet操作结果 (应为true): " + flag + ", 新值: " + atomicInteger.get());

        // getAndSet(int newValue)：获取当前值然后设置为newValue
        System.out.println("先获取当前值然后设置新值 (25): " + atomicInteger.getAndSet(25));

        // updateAndGet(IntUnaryOperator updateFunction)：用给定的函数更新值并返回
        System.out.println(
                "使用updateAndGet更新后的值 (x -> x * 2): " + atomicInteger.updateAndGet(x -> x * 2));

        // accumulateAndGet(int x, BinaryOperator< Integer > accumulatorFunction)：将给定值与当前值合并，然后返回
        System.out.println(
                "accumulateAndGet合并后的值 (10, (x, y) -> x + y): "
                        + atomicInteger.accumulateAndGet(10, (x, y) -> x + y));

        // getAndUpdate(IntUnaryOperator updateFunction)：用给定的函数更新值但不返回新值
        atomicInteger.getAndUpdate(x -> x * 3);
        System.out.println("使用getAndUpdate更新后的值 (x -> x * 3): " + atomicInteger.get());
    }
}
