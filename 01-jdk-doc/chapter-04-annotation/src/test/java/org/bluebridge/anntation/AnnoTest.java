package org.bluebridge.anntation;

import org.bluebridge.annotation.*;

/**
 * 一个简单的注解
 *
 * @author lingwh
 * @date 2019/3/12 16:29
 */
@Anno1
@Anno2(name = "zhangsan", age = 100)
@Anno3(100)
@Anno4(
        a = 100,
        b = "hello",
        c = ColorEnum.BLUE,
        d = String.class,
        e = @Anno2(name = "zhangsan", age = 100),
        // f=100
        f = {100, 500, 1000})
public class AnnoTest {

}
