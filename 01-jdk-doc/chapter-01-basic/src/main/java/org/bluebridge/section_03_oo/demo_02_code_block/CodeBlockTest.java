package org.bluebridge.section_03_oo.demo_02_code_block;

import lombok.extern.slf4j.Slf4j;

/**
 * 静态代码块和实例代码块测试
 *
 * @author lingwh
 * @date 2026/08/18 19:50
 */
@Slf4j
public class CodeBlockTest {

    public static void main(String[] args) {
        log.info("第一次 new:");
        new User();

        log.info("");
        log.info("第二次 new:");
        new User();
    }
}
