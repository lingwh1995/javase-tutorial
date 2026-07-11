package org.bluebridge;

import org.junit.Test;

/**
 * final关键字使用
 *
 * 1. final关键字的好处
 *    - final关键字提高了性能，JVM和Java应用都会缓存final变量
 *    - final变量可以安全的在多线程环境下进行共享，而不需要额外的同步开销
 *    - 使用final关键字，JVM会对方法、变量及类进行优化
 *
 * 2. 关于final的重要知识点
 *    - final关键字可以用于成员变量、本地变量、方法以及类
 *    - final成员变量必须在声明的时候初始化或者在构造器中初始化，否则就会报编译错误
 *    - 你不能够对final变量再次赋值
 *    - 本地变量必须在声明时赋值
 *    - 在匿名类中所有变量都必须是final变量
 *    - final方法不能被重写
 *    - final类不能被继承
 *    - final关键字不同于finally关键字，后者用于异常处理
 *    - final关键字容易与finalize()方法搞混，后者是在Object类中定义的方法，是在垃圾回收之前被JVM调用的方法
 *    - 接口中声明的所有变量本身是final的
 *    - final和abstract这两个关键字是反相关的，final类就不可能是abstract的
 *    - final方法在编译阶段绑定，称为静态绑定(static binding)
 *    - 没有在声明时初始化final变量的称为空白final变量(blank final variable)，它们必须在构造器中初始化，或者调用this()初始化。不这么做的话，编译器会报错“final变量(变量名)需要进行初始化”
 *    - 将类、方法、变量声明为final能够提高性能，这样JVM就有机会进行估计，然后优化
 *    - 按照Java代码惯例，final变量就是常量，而且通常常量名要大写
 *    - 对于集合对象声明为final指的是引用不能被更改，但是你可以向其中增加，删除或者改变内容
 *
 * @author lingwh
 * @date 2026/7/9 00:00
 */
public class FinalTest {
    static final int A = 10;
    static final int B = Short.MAX_VALUE + 1;

    final int a = 20;
    final int b = Integer.MAX_VALUE;

    @Test
    public final void test1() {
        final int c = 30;
        new Thread(()->{
            System.out.println(c);
        }).start();

        final int d = 30;
        class Task implements Runnable {

            @Override
            public void run() {
                System.out.println(d);
            }
        }
        new Thread(new Task()).start();
    }
}
