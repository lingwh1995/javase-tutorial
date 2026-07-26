package org.bluebridge.expand.principles.liskov_substitution;

/**
 * 未使用里氏替换原则
 *
 * 1. B1 类继承了 A1 类，在不知道的情况下重下了父类 A1 的 fun1() 方法，外部调用子类 B1 类中中 fun1() 的时候以为
 * 里面的逻辑还是 a-b，其实逻辑已经在不知道的情况下被重写成 a+b 了，导致计算结果出了问题
 * 2. 缺点是 A1 和 B1 的耦合性太高了，修改 B 很容易对 A 造成影响
 *
 * @author lingwh
 * @date 2026/7/22 08:42
 */
public class Liskov1 {

    public static void main(String[] args) {
        A1 a1 = new A1();
        System.out.println("11-3 = " + a1.fun1(11, 3));
        System.out.println("1-8 = " + a1.fun1(1, 8));

        A1 b1 = new B1();
        System.out.println("11-3 = " + b1.fun1(11, 3));
        System.out.println("1-8 = " + b1.fun1(1, 8));
    }
}

class A1 {
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

    public void printHelloWorld() {
        System.out.println("Hello World!");
    }
}

/**
 * B1 类继承了 A1 类，在不知道的情况下重下了父类 A 的 fun1() 方法
 */
class B1 extends A1 {

    /**
     * 返回 a 与 b 的和
     *
     * @param a
     * @param b
     * @return
     */
    @Override
    public int fun1(int a, int b) {
        return a + b;
    }

    /**
     * B1 独有方法，非继承自 A1
     * 返回 a 与 b 的和加上 9
     *
     * @param a
     * @param b
     * @return
     */
    public int fun2(int a, int b) {
        return fun1(a, b) + 9;
    }

    @Override
    public void printHelloWorld() {
        System.out.println("Hello World!");
    }
}
