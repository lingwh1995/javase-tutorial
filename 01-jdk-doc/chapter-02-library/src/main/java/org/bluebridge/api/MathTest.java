package org.bluebridge.api;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Math 测试类
 *
 * @author lingwh
 * @date 2026/7/29 14:22
 */
@Slf4j
public class MathTest {

    @Test
    public void testMath() {
        /**
         * floorMod：地板取（向负无穷方向取）模，得到余数
         * floorDiv：地板除法，向地板取（向负无穷方向取）整得到商
         */
        // 10÷3≈3.33，向下取整商=3；10 - 3*3 = 1
        log.info("Math.floorMod(10, 3) = {}", Math.floorMod(10, 3));
        // -10÷3≈-3.33，向下取整商=-4；-10 - (-4*3) = 2
        log.info("Math.floorMod(-10, 3) = {}", Math.floorMod(-10, 3));
        // 10÷3≈3.33，向下取整，结果3
        log.info("Math.floorDiv(10, 3) = {}", Math.floorDiv(10, 3));
        // -10÷3≈-3.33，向下取整，结果-4
        log.info("Math.floorDiv(-10, 3) = {}", Math.floorDiv(-10, 3));
        log.info("Math.PI = {}", Math.PI);
    }
}
