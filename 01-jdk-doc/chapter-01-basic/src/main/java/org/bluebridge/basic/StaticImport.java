package org.bluebridge.basic;

import static java.lang.Math.pow;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author lingwh
 * @desc 静态导入（导入到类级别，要求导入的方法必须是静态方法）测试
 * @date 2019/3/12 16:58
 */
@Slf4j
public class StaticImport {

    @Test
    public void testStaticImport() {
        double pow = pow(0.2, 0.3);
        log.info("pow = {}", pow);
    }
}
