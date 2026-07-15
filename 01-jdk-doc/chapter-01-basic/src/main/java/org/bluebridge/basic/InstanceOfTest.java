package org.bluebridge.basic;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * instanceof关键字测试
 *
 * @author lingwh
 * @date 2019/3/12 16:58
 */
@Slf4j
public class InstanceOfTest {

    /**
     * 测试继承时子类是不是父类
     */
    @Test
    public void testClassExtend() {
        A aa = new AA();
        log.info("aa instanceof AA: {}", aa instanceof AA); // true
        log.info("aa instanceof  A: {}", aa instanceof A); // true
    }

    /**
     * 测试实现时实现类是不是instanceof父接口
     */
    @Test
    public void testImplementInterface() {
        B bb = new BB();
        log.info("bb instanceof BB: {}", bb instanceof BB); // true
        log.info("bb instanceof  B: {}", bb instanceof B); // true
    }
}

class A {}

class AA extends A {}

interface B {}

class BB implements B {}
