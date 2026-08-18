package org.bluebridge.section_01_basic;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Objects;

/**
 * Objects 工具类，1.7 版本引入的，Objects 工具类方法特点
 *
 * 1. 防止空指针异常：Objects 类中的方法通常会进行非空检查，以避免调用方法时出现空指针异常（NPE）。例如，Objects.equals(a, b) 在比较前会检查对象是否为 null，从而安全地执行相等性判断。
 * 2. 无需实例化：由于 Objects 是一个 final 类且构造函数为 private，它不能被继承或实例化，全部方法都是静态的，因此使用起来非常方便，直接通过类名调用即可。
 * 3. 常用方法：除了 equals 方法外，Objects 类还提供了如 isNull、nonNull、toString 等方法，这些方法对于日常的对象操作非常有用。比如 isNull 用于判断对象是否为 null，toString 可以返回对象的字符串表示形式，类似于调用对象的 toString() 方法，但在对象为 null 时会返回字符串 "null" 而不是抛出异常。
 *
 * @author lingwh
 * @date 2026/4/23 16:29
 */
@Slf4j
public class ObjectsTest {

    /**
     * 测试 equals()
     * 不会报空指针异常
     */
    @Test
    public void testObjectsEquals() {
        Integer i = 1;
        Integer j = 1;
        log.debug("equals: {}", Objects.equals(i, j));

        i = null;
        j = 10;
        log.debug("deepEquals: {}", Objects.deepEquals(i, j));
    }

    /**
     * 测试 isNull()
     */
    @Test
    public void testObjectsIsNull() {
        String s = null;
        log.debug("isNull: {}", Objects.isNull(s));
    }

    /**
     * 测试 nonNull()
     */
    @Test
    public void testObjectsNonNull() {
        String s = null;
        log.debug("nonNull: {}", Objects.nonNull(s));
    }

    /**
     * 测试 toString()
     */
    @Test
    public void testObjectsToString() {
        Integer i = 100;
        log.debug("toString: {}", Objects.toString(i));
    }

    /**
     * 测试 hashCode()
     */
    @Test
    public void testObjectsHashCode() {
        String s = "hello";
        log.debug("hashCode: {}", s.hashCode());
        log.debug("hashCode: {}", Objects.hashCode(s));
    }

    /**
     * 测试 deepEquals()，不会报空指针异常
     */
    @Test
    public void testObjectsDeepEquals() {
        String s1 = "hello";
        String s2 = "hello";
        log.debug("deepEquals: {}", Objects.deepEquals(s1, s2));

        s1 = null;
        s2 = "hello";
        log.debug("deepEquals: {}", Objects.deepEquals(s1, s2));
    }

    /**
     * 测试 requireNonNull() 用于判断参数是否为 null，为 null 会抛出 NullPointerException
     *
     * Objects.requireNonNull(T obj)
     * Objects.requireNonNull(T obj, String message)
     * Objects.requireNonNull(T obj, Supplier<String> messageSupplier)
     */
    @Test
    public void testObjectsRequireNonNull() {
        String param = null;
        // log.debug("requireNonNull: {}", Objects.requireNonNull(param));
        // log.debug("requireNonNull: {}", Objects.requireNonNull(param, "参数不能为空！"));
        log.debug("requireNonNull: {}", Objects.requireNonNull(param, () -> "参数不能为空！"));
    }
}
