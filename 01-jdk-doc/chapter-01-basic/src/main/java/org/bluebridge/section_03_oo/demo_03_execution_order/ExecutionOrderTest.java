package org.bluebridge.section_03_oo.demo_03_execution_order;

import lombok.extern.slf4j.Slf4j;

/**
 * 继承中静态代码块、实例代码块、构造方法的执行顺序测试
 *
 * 执行顺序:
 *   父静态 -> 子静态 (类加载, 仅一次)
 *   父实例 -> 父构造 -> 子实例 -> 子构造 (每次 new)
 *
 * 特别注意:
 *   构造方法和实例代码块执行与书写顺序无关，始终是先执行实例代码块，再执行构造方法
 *
 * @author lingwh
 * @date 2026/08/18 9:35
 */
@Slf4j
public class ExecutionOrderTest {

    public static void main(String[] args) {
        log.info("第一次 new Child:");
        new Child();

        log.info("");
        log.info("第二次 new Child:");
        new Child();
    }
}
