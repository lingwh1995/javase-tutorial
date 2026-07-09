package org.bluebridge.oo._01_this_and_super;

import lombok.extern.slf4j.Slf4j;

/**
 * @author lingwh
 * @desc 面向对象测试
 * @date 2025/11/12 10:05
 */
@Slf4j
public class OOTest {

    public static void main(String[] args) {
        Dog dog = new Dog();
        log.info("------------------");
        dog.eat();
        dog.drink();
        dog.sleep();
        log.info("------------------");
        dog.testThis();
        log.info("------------------");
        dog.testSuper();
        log.info("------------------");
    }
}
