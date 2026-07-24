package org.bluebridge;

import org.junit.Test;

/**
 * final 关键字使用
 *
 * 1. final 关键字的好处
 * - final 关键字提高了性能，JVM 和 Java 应用都会缓存 final 变量
 * - final 变量可以安全的在多线程环境下进行共享，而不需要额外的同步开销
 * - 使用 final 关键字，JVM 会对方法、变量及类进行优化
 *
 * 2. 关于 final 的重要知识点
 * - final 关键字可以用于成员变量、本地变量、方法以及类
 * - final 成员变量必须在声明的时候初始化或者在构造器中初始化，否则就会报编译错误
 * - 你不能够对 final 变量再次赋值
 * - 本地变量必须在声明时赋值
 * - 在匿名类中所有变量都必须是 final 变量
 * - final 方法不能被重写
 * - final 类不能被继承
 * - final 关键字不同于 finally 关键字，后者用于异常处理
 * - final 关键字容易与 finalize() 方法搞混，后者是在 Object 类中定义的方法，是在垃圾回收之前被 JVM 调用的方法
 * - 接口中声明的所有变量本身是 final 的
 * - final 和 abstract 这两个关键字是反相关的，final 类就不可能是 abstract 的
 * - final 方法在编译阶段绑定，称为静态绑定(static binding)
 * - 没有在声明时初始化 final 变量的称为空白 final 变量(blank final variable)，它们必须在构造器中初始化，或者调用 this() 初始化。不这么做的话，编译器会报错“final 变量(变量名)需要进行初始化”
 * - 将类、方法、变量声明为 final 能够提高性能，这样 JVM 就有机会进行估计，然后优化
 * - 按照 Java 代码惯例，final 变量就是常量，而且通常常量名要大写
 * - 对于集合对象声明为 final 指的是引用不能被更改，但是你可以向其中增加，删除或者改变内容
 *
 * @author lingwh
 * @date 2026/4/21 16:20
 */
public class FinalTest {

    static final int A = 10;
    static final int B = Short.MAX_VALUE + 1;

    final int a = 20;
    final int b = Integer.MAX_VALUE;

    @Test
    public final void test1() {
        final int c = 30;
        new Thread(() -> {
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
