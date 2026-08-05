package org.bluebridge.java9.section_02_diamond;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Java9 钻石操作符测试
 *
 * @author lingwh
 * @date 2025/1/24 13:20
 */
public class DiamondTest {

    @Test
    public void testDiamondOperator() {
        /**
         * java8 中同时使用钻石操作符和匿名内部类的时候，匿名内部类泛型处报下面错误 Cannot use '<>' with anonymous inner
         * classes
         * java9 及以上版本中钻石操作符和匿名内部类可以同时存在
         */
        Comparator<Object> comparator = new Comparator<>() {
            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }
        };

        // java7 中新特性 类型推断
        List<Integer> nums = new ArrayList<>();
    }
}
