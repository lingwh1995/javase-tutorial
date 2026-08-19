package org.bluebridge.section_03_oo.demo_02_code_block;

import lombok.extern.slf4j.Slf4j;

/**
 * 演示静态代码块和实例代码块的执行机制
 *
 * @author lingwh
 * @date 2026/08/18 19:50
 */
@Slf4j
public class User {

    /**
     * 静态代码块: 仅类加载时执行一次，用于处理类似于加载配置文件等操作
     */
    static {
        log.info("静态代码块执行......");
    }

    /**
     * 代码块: 每个对象创建时都会执行，用于执行每个对象创建时都要执行的公共操作
     */
    {
        log.info("实例代码块执行......");
    }

    public User() {
        log.info("构造方法执行......");
    }
}
