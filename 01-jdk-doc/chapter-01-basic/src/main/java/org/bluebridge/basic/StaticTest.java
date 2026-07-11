package org.bluebridge.basic;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author lingwh
 * @desc static关键字测试
 * @date 2019/7/10 13:39
 */
@Slf4j
public class StaticTest {

    @Test
    public void testStaticMethod() {
        // 调用eat方法，构造方法并未执行，验证被static修饰的方法是和类绑定的，不是和类的实例对象绑定的
        Cat.eat();
    }
}

@Slf4j
class Cat {

    public Cat() {
        log.info("Cat Constructors......");
    }

    public static void eat() {
        log.info("Cat eat......");
    }
}
