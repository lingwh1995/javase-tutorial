package org.bluebridge.basic;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Final关键字测试
 *
 * @author lingwh
 * @date 2019/3/12 16:58
 */
@Slf4j
public class FinalTest {

	/**
	 * final修饰变量的初始化时机
     *
	 * 1. 被final修饰的变量只能赋值一次
	 * 2. 在构造方法完毕前(非静态的常量)
	 */

	int num1 = 10;
	final int num2 = 100;

	/**
	 * 没有被final修饰的全部变量可以不初始化，但是被final修饰的全局变量一定要初始化
	 */
	int num3 ;

	@Test
	public void testFinal1(){
		num1 = 120;
		// 如果要改变被final修饰的num2的值，则会报错
		//num2 = 200;
	}

    @Test
    public void testFinal2() {
        String str = "hello2";
        final String a = "hello";
        String b = "hello";
        String c = a + 2;
        String d = b + 2;
        String e = a + getHello();
        String f = b + getHello();
        log.info("str == c: {}", str == c);
        log.info("str == d: {}", str == d);
        log.info("str == e: {}", str == e);
        log.info("str == f: {}", str == f);
    }

    public static String getHello() {
        return "hello";
    }
}
