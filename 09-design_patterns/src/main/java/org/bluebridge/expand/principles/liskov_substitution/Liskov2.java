package org.bluebridge.expand.principles.liskov_substitution;

/**
 * 使用里氏替换原则
 *
 * 1. B2 类不再继承 A2 类，改为 A2 类和 B2 类都继承一个基类 Base
 * 2. 这样调用者通过查看继承关系，明显的就知道了 B2 类中的 fun1() 里面的逻辑是 a+b，而不是 a-b
 * 3. 把 A2 和 B2 中的公用方法 printHelloWorld() 抽取到了 Base 中
 *
 * 注意：本来 B2 继承了 A2，可以直接调用父类 A2 中的方法，里氏替换原则修改代码后，B2 不再继承 A2，B2 想要调用 A2
 * 中的方法则可以通过依赖 A2 来实现调用
 *
 * @author lingwh
 * @date 2026/7/22 15:33
 */
public class Liskov2 {

    /**
     * 将 A2 和 B2 中的公用方法 printHelloWorld() 抽取到了 Base 中
     */
    public void printHelloWorld() {
        System.out.println("Hello World!");
    }
}

class Base {
    // 把更加基础的方法和成员写到 Base 类中
}

class A2 extends Base {
    /**
     * 返回 a 与 b 的差
     *
     * @param a
     * @param b
     * @return
     */
    public int fun1(int a, int b) {
        return a - b;
    }
}

class B2 extends Base {

    // B 通过这个方式依赖 A
    private A2 a2 = new A2();

    /**
     * 返回 a 与 b 的和
     *
     * @param a
     * @param b
     * @return
     */
    public int fun1(int a, int b) {
        return a + b;
    }

    /**
     * B2 独有方法，非继承自 A2 返回 a 与 b 的和加上 9
     *
     * @param a
     * @param b
     * @return
     */
    public int fun2(int a, int b) {
        return fun1(a, b) + 9;
    }

    /**
     * 里氏替换原则，如果 B2 和 A2 同时继承了基类 Base，那么当 B2 想要使用 A2 类中的方法的时候，可以通过依赖的方式来调用 A 类 2
     * 中的方法
     *
     * @param a
     * @param b
     * @return
     */
    public int fun3(int a, int b) {
        return this.a2.fun1(a, b);
    }
}
