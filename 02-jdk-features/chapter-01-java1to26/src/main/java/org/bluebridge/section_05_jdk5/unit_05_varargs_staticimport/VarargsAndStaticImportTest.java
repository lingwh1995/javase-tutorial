package org.bluebridge.section_05_jdk5.unit_05_varargs_staticimport;

import org.junit.Test;

import java.util.Arrays;

// 静态导入: 导入 Math 类的静态成员, 使用时可以省略类名前缀
import static java.lang.Math.PI;
import static java.lang.Math.abs;
import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * JDK1.5 可变参数与静态导入测试
 *
 * 1. 可变参数(Varargs): 允许方法接收不定数量的参数, 使用 类型... 参数名 声明,
 *    可变参数本质上是一个数组, 只能作为方法的最后一个参数
 * 2. 静态导入(Static Import): 通过 import static 导入类的静态成员(静态方法、静态常量),
 *    使用时可以省略类名前缀, 如直接使用 max() 代替 Math.max()
 *    注意: 静态导入会降低代码可读性(无法直接看出成员所属的类), 应谨慎使用
 *
 * @author lingwh
 * @date 2026/08/05 18:27
 */
public class VarargsAndStaticImportTest {

    /**
     * 测试可变参数: 计算任意数量整数之和
     */
    @Test
    public void testVarargs() {
        // 可以传入任意数量的参数
        System.out.println("sum(1, 2, 3) = " + sum(1, 2, 3));
        System.out.println("sum(1, 2, 3, 4, 5) = " + sum(1, 2, 3, 4, 5));
        // 可以不传参数
        System.out.println("sum() = " + sum());
        // 也可以直接传入数组
        int[] nums = {10, 20, 30};
        System.out.println("sum(new int[]{10, 20, 30}) = " + sum(nums));
    }

    /**
     * 可变参数方法: 本质是 int 数组
     *
     * @param nums 数量不定的整数
     * @return 所有整数之和
     */
    private int sum(int... nums) {
        int total = 0;
        for (int num : nums) {
            total += num;
        }
        return total;
    }

    /**
     * 测试可变参数的数组本质
     */
    @Test
    public void testVarargsArrayEssence() {
        // 可变参数本质上就是数组, 可以直接把数组传给可变参数方法
        printArgs(1, 2, 3);
        printArgs(new Integer[]{4, 5, 6});
        // 数组中包含可变参数时, 遍历与操作方式与普通数组一致
        System.out.println("可变参数转数组后的内容: " + Arrays.toString(new Integer[]{7, 8}));
    }

    /**
     * 可变参数方法: 接收任意数量的 Integer
     *
     * @param nums 数量不定的整数
     */
    private void printArgs(Integer... nums) {
        System.out.println("参数个数: " + nums.length + ", 内容: " + Arrays.toString(nums));
    }

    /**
     * 测试可变参数与固定参数混合使用: 可变参数必须在参数列表的最后
     */
    @Test
    public void testVarargsWithFixedParams() {
        String message = buildMessage("求和结果", 1, 2, 3);
        System.out.println(message);
    }

    /**
     * 混合参数方法: 固定参数在前, 可变参数在最后
     *
     * @param prefix 前缀字符串
     * @param nums   数量不定的整数
     * @return 拼接后的字符串
     */
    private String buildMessage(String prefix, int... nums) {
        StringBuilder sb = new StringBuilder(prefix + ": ");
        for (int num : nums) {
            sb.append(num).append(" ");
        }
        return sb.toString();
    }

    /**
     * 测试静态导入: 直接使用 Math 类的静态成员, 无需写 Math. 前缀
     */
    @Test
    public void testStaticImport() {
        // 静态导入后可以直接使用 PI、max、min、abs
        System.out.println("PI = " + PI);
        System.out.println("max(10, 20) = " + max(10, 20));
        System.out.println("min(10, 20) = " + min(10, 20));
        System.out.println("abs(-5) = " + abs(-5));
        // 对比: 未静态导入的成员需要写类名前缀
        System.out.println("Math.sqrt(16) = " + Math.sqrt(16));
    }
}
