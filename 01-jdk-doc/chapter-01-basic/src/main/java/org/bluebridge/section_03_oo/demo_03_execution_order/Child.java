package org.bluebridge.section_03_oo.demo_03_execution_order;

import lombok.extern.slf4j.Slf4j;

/**
 * 子类 - 演示继承中静态代码块、实例代码块、构造方法的执行顺序
 *
 * @author lingwh
 * @date 2026/08/18 9:29
 */
@Slf4j
public class Child extends Parent {

    static {
        log.info("子类-静态代码块执行......");
    }

    {
        log.info("子类-实例代码块执行......");
    }

    Child() {
        log.info("子类-构造方法执行......");
    }
}
