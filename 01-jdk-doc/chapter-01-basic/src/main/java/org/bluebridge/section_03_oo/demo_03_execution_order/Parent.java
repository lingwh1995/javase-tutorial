package org.bluebridge.section_03_oo.demo_03_execution_order;

import lombok.extern.slf4j.Slf4j;

/**
 * 父类 - 演示继承中静态代码块、实例代码块、构造方法的执行顺序
 *
 * @author lingwh
 * @date 2026/08/18 9:23
 */
@Slf4j
public class Parent {

    static {
        log.info("父类-静态代码块执行......");
    }

    {
        log.info("父类-实例代码块执行......");
    }

    Parent() {
        log.info("父类-构造方法执行......");
    }
}
