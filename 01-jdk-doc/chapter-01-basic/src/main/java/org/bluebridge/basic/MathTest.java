package org.bluebridge.basic;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 *
 * @author lingwh
 * @date 2026/7/29 14:22
 */
@Slf4j
public class MathTest {

    @Test
    public void testMath() {
        log.info("Math.floorMod(10, 3) = {}", Math.floorMod(10, 3));
        log.info("Math.floorMod(-10, 3) = {}", Math.floorMod(-10, 3));
        log.info("Math.floorDiv(10, 3) = {}", Math.floorDiv(10, 3));
        log.info("Math.floorDiv(-10, 3) = {}", Math.floorDiv(-10, 3));
        log.info("Math.PI = {}", Math.PI);
    }
}
