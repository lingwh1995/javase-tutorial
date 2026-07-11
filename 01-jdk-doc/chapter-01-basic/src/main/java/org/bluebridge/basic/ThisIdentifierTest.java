package org.bluebridge.basic;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author lingwh
 * @desc this关键字测试
 * @date 2019/4/10 13:39
 */
@Slf4j
public class ThisIdentifierTest {

    @Test
    public void test() {
        /**
         * 测试区分二义性
         */
        ThisIdentifier thisIdentifier = new ThisIdentifier();
        thisIdentifier.say(5);

        /**
         * 测试调用有参无参方法
         */
        new ThisIdentifier(1, 2, 3);
    }
}

@Slf4j
class ThisIdentifier {

    /**
     * this的用法: 1.区分二义性 2.调用当前类有参或者无参方法
     */
    int a = 10;

    public void say(int a) {
        log.info("this.a: {}", this.a);
        log.info("a: {}", a);
    }

    /**
     * 创建一个新的实例 This_Identifier.
     */
    public ThisIdentifier() {
        log.info("我是无参构造方法......");
    }

    /**
     * 创建一个新的实例 This_Identifier.
     */
    public ThisIdentifier(int a, int b) {
        this();
        log.info("我是第一个有参构造方法......");
    }

    /**
     * 创建一个新的实例 This_Identifier.
     */
    public ThisIdentifier(int a, int b, int c) {
        this(a, b);
        log.info("我是第二个有参构造方法......");
    }
}
