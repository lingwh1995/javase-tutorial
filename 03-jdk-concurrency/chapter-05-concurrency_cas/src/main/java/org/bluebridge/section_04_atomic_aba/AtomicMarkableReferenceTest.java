package org.bluebridge.section_04_atomic_aba;

import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 * AtomicMarkableReference 测试
 *
 * @author lingwh
 * @date 2026/4/21 14:45
 */
public class AtomicMarkableReferenceTest {

    public static void main(String[] args) {
        // 创建一个 AtomicMarkableReference 实例，初始值为 "Hello" 和标记 false
        AtomicMarkableReference<String> atomicMarkableReference = new AtomicMarkableReference<>("Hello", false);

        // 客户端调用：尝试更新引用和标记
        boolean updated = atomicMarkableReference.compareAndSet("Hello", "World", false, true);
        // 输出：Updated: true
        System.out.println("Updated: " + updated);

        // 获取当前值和标记
        String currentValue = atomicMarkableReference.getReference();
        boolean currentMark = atomicMarkableReference.isMarked();
        // 输出：Current Value: World, Mark: true
        System.out.println("Current Value: " + currentValue + ", Mark: " + currentMark);

        // 尝试基于旧值和旧标记更新，但这次会失败，因为当前值或标记与预期的不匹配
        updated = atomicMarkableReference.compareAndSet("Hello", "Java", false, false);
        // 输出：Updated: false
        System.out.println("Updated: " + updated);

        // 再次获取当前值和标记，以确认没有变化
        currentValue = atomicMarkableReference.getReference();
        currentMark = atomicMarkableReference.isMarked();
        // 输出：Current Value: World, Mark: true
        System.out.println("Current Value: " + currentValue + ", Mark: " + currentMark);
    }
}
