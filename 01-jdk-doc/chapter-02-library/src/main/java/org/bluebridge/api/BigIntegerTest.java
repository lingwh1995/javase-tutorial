package org.bluebridge.api;

import java.math.BigInteger;

/**
 * BigInteger 测试
 *
 * @author lingwh
 * @date 2026/4/23 16:29
 */
public class BigIntegerTest {

    public static void main(String[] args) {
        // 通过 字符串类型数据 创建一个 BigInteger 类型对象
        BigInteger bigInteger = new BigInteger("20");
        System.out.println(bigInteger);

        // 通过 long 类型数据 创建一个 BigInteger 类型对象
        bigInteger = BigInteger.valueOf(20);
        System.out.println(bigInteger);

        // 确定一个数是否为素数
        boolean probablePrime = bigInteger.isProbablePrime(100);
        System.out.println(probablePrime);
    }
}
