/**
 * @Title: AnnoTester.java @Package com.dragonsoft.anno
 *
 * @author ronin
 * @date 2019年3月12日
 * @version V1.0
 */
package org.bluebridge.anntation;

import org.bluebridge.annotation.*;

/**
 * @author lingwh
 * @desc 一个简单的注解
 * @date 2019/3/12 00:00
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
public class AnnoTest {}
