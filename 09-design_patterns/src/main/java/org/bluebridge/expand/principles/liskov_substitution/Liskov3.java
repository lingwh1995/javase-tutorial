package org.bluebridge.expand.principles.liskov_substitution;

/**
 * 里氏替换原则
 *
 * @author lingwh
 * @date 2026/7/22 09:56
 */
public class Liskov3 {

    public static void main(String[] args) {
        X x = new X();
        Y y = new Y();
        Z z = new Z();
        z.z(x);
        // Y 重写 X 方法后，z 调用的时候传入父类对象和子类对象产生的结果不同，即能使用父类的地方不能透明的使用其子类
        z.z(y);
    }
}

class Z {
    void z(X x) {
        x.fun1();
    }
}

class X {
    void fun1() {
        System.out.println("xxx");
    }
}

class Y extends X {
    @Override
    void fun1() {
        System.out.println("yyy");
    }
}
