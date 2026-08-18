package org.bluebridge.section_05_jdk5.unit_03_autoboxing;

import org.junit.Test;

import java.util.Arrays;

/**
 * JDK1.5 自动装箱/拆箱测试
 *
 * 自动装箱(Auto Boxing): 基本类型自动转换为对应的包装类型, 如 int -> Integer
 * 自动拆箱(Auto Unboxing): 包装类型自动转换为对应的基本类型, 如 Integer -> int
 * 底层原理: 自动装箱本质是调用包装类的 valueOf() 方法, 自动拆箱本质是调用包装类的 xxxValue() 方法
 * 注意事项:
 * 1. Integer 缓存: Integer 类默认缓存了 -128~127 之间的整数, valueOf() 直接返回缓存对象,
 *    因此该范围内的两个相同值的 Integer 使用 == 比较为 true, 超出范围则为 false
 *    (可通过 -XX:AutoBoxCacheMax 调整缓存上限, 仅对 Integer 生效)
 * 2. == 与 equals 的区别: 基本类型 == 比较的是数值, 引用类型 == 比较的是引用地址,
 *    equals 比较的是对象内容
 * 3. 自动拆箱在包装类型为 null 时会抛出 NullPointerException
 *
 * @author lingwh
 * @date 2026/08/05 18:26
 */
public class AutoBoxingTest {

    /**
     * 测试基本类型与包装类型的自动转换(自动装箱/拆箱)
     */
    @Test
    public void testAutoBoxingAndUnboxing() {
        // 自动装箱: int -> Integer, 等价于 Integer.valueOf(100)
        Integer integer = 100;
        System.out.println("自动装箱 int -> Integer: " + integer);
        // 自动拆箱: Integer -> int, 等价于 integer.intValue()
        int i = integer;
        System.out.println("自动拆箱 Integer -> int: " + i);
        // 拆箱参与运算: 包装类型参与算术运算时会自动拆箱
        int result = integer + 100;
        System.out.println("拆箱参与运算: " + integer + " + 100 = " + result);
        // 包装类型与基本类型比较: 自动拆箱后比较数值
        Integer a = 200;
        int b = 200;
        System.out.println("Integer(200) == int(200): " + (a == b));
        // 基本类型数组转换为包装类型数组需要手动遍历(自动装箱)
        int[] intArray = {1, 2, 3};
        Integer[] integerArray = new Integer[intArray.length];
        for (int j = 0; j < intArray.length; j++) {
            integerArray[j] = intArray[j];
        }
        System.out.println("基本类型数组转换为包装类型数组: " + Arrays.toString(integerArray));
    }

    /**
     * 测试 Integer 缓存问题: -128~127 之间使用缓存, 之外每次创建新对象
     */
    @Test
    public void testIntegerCache() {
        // -128~127 范围内: valueOf() 返回缓存中的同一个对象
        Integer a = 100;
        Integer b = 100;
        System.out.println("100 == 100: " + (a == b));       // true, 命中缓存
        // 超出 -128~127 范围: valueOf() 每次创建新对象
        Integer c = 200;
        Integer d = 200;
        System.out.println("200 == 200: " + (c == d));       // false, 未命中缓存
        // 使用 new 创建: 永远是新对象, 即使值在缓存范围内
        Integer e = new Integer(100);
        System.out.println("new Integer(100) == Integer(100): " + (e == a));  // false
        // 通过 valueOf() 手动验证缓存范围
        Integer v1 = Integer.valueOf(127);
        Integer v2 = Integer.valueOf(127);
        System.out.println("valueOf(127) == valueOf(127): " + (v1 == v2));    // true
        Integer v3 = Integer.valueOf(128);
        Integer v4 = Integer.valueOf(128);
        System.out.println("valueOf(128) == valueOf(128): " + (v3 == v4));    // false
    }

    /**
     * 测试 == 与 equals 的区别
     */
    @Test
    public void testEqualsAndOperator() {
        // 基本类型: == 比较的是数值
        int x = 100;
        int y = 100;
        System.out.println("基本类型 100 == 100: " + (x == y));    // true
        // 包装类型: == 比较的是引用地址(是否同一个对象)
        Integer p = new Integer(100);
        Integer q = new Integer(100);
        System.out.println("new Integer(100) == new Integer(100): " + (p == q));   // false
        // 包装类型: equals() 比较的是数值内容
        System.out.println("new Integer(100).equals(new Integer(100)): " + p.equals(q));   // true
        // 缓存范围内的包装类型: == 与 equals 都为 true
        Integer m = 100;
        Integer n = 100;
        System.out.println("缓存范围内 100 == 100: " + (m == n));    // true
        // 缓存范围外的包装类型: == 为 false, equals 为 true
        Integer u = 200;
        Integer v = 200;
        System.out.println("缓存范围外 200 == 200: " + (u == v));    // false
        System.out.println("缓存范围外 200.equals(200): " + u.equals(v));    // true
        // 包装类型与基本类型比较: 包装类型自动拆箱, == 比较的是数值
        Integer boxed = 300;
        int primitive = 300;
        System.out.println("包装类型与基本类型 300 == 300: " + (boxed == primitive));    // true
        // 注意: 包装类型为 null 时自动拆箱会抛出 NullPointerException
        // Integer nullValue = null;
        // int unboxingNull = nullValue;    // 抛 NullPointerException
    }
}
