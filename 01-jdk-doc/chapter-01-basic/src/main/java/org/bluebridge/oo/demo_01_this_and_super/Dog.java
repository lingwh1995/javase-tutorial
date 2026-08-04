package org.bluebridge.oo.demo_01_this_and_super;

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
     * 测试在继承关系中使用 this 调用父类的方法
     */
    public void testThis() {
        this.eat();
        this.drink();
        this.sleep();
    }

    /**
     * 测试在继承关系中使用 super 调用父类的方法
     */
    public void testSuper() {
        // 下面一行放开会报错
        // super.eat();
        super.drink();
        super.sleep();
    }
}
