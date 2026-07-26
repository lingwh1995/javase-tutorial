package org.bluebridge.expand.principles.dependenceinversion;

/**
 * 依赖反转原则测试
 *
 * @author lingwh
 * @date 2026/7/22 10:18
 */
public class DependenceDeliver3Test {

    public static void main(String[] args) {
        A3 a3 = new A4();
        a3.fun();
    }
}

class A3 {
    public void fun() {
        System.out.println("吃之前先喝水......");
        System.out.println("吃....");
    }
}

class A4 extends A3 {
    @Override
    public void fun() {
        System.out.println("吃....");
        System.out.println("吃之后吃水果....");
    }
}
