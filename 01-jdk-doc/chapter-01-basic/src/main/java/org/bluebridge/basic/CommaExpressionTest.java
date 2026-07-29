package org.bluebridge.basic;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 逗号表达式
 *
 * @author lingwh
 * @date 2025/5/3 16:58
 */
@Slf4j
public class CommaExpressionTest {

    /**
     * 测试逗号表达式
     */
    @Test
    public void testCommaExpression() {
        // 下面代码在 c 语言中是可以执行成功的，在 java 中无法通过编译
        // 原因：java 支持的逗号表达式能力是有限的，一次性定义并赋值多个变量是可以的，但是赋值时使用多个表达式是不允许的
//        int a = 3, b = 5, c = 7;
//        int i = 0;
//        // 取最左边的表达式的运算结果
//        i = a+b, b+=3, c+=8;
//        printf("i = %d, b= %d\n", i, b);
//
//        int x = 3, y = 5, z = 7;
//        int j = 0;
//        // 取最右边的表达式的运算结果
//        j = (x+y, y+=3, z+=8);
//        printf("j = %d, y = %d\n", j, y);
    }
}
