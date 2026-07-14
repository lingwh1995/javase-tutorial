package org.bluebridge.oo._01_this_and_super;

import lombok.extern.slf4j.Slf4j;

/**
 * 狗类
 *
 * @author lingwh
 * @date 2025/11/12 10:04
 */
@Slf4j
public class Dog extends Animal {

    @Override
    void eat() {
        log.info("胖狗正在吃饭......");
    }

    @Override
    public void drink() {
        log.info("胖狗正在喝水......");
    }

    /**
     * 测试在继承关系中使用this调用父类的方法
     */
    public void testThis() {
        this.eat();
        this.drink();
        this.sleep();
    }

    /**
     * 测试在继承关系中使用super调用父类的方法
     */
    public void testSuper() {
        // 下面一行放开会报错
        // super.eat();
        super.drink();
        super.sleep();
    }
}
