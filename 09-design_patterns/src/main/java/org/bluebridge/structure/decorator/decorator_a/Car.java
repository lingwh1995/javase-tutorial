package org.bluebridge.structure.decorator.decorator_a;

/**
 * 真实对象
 *
 * @author lingwh
 * @date 2019/3/23 19:02
 */
public class Car implements ICar {

    @Override
    public void run() {
        System.out.println("陆地上跑......");
    }
}
