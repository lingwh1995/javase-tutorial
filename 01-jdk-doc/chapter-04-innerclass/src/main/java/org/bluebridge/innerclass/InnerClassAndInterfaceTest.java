package org.bluebridge.innerclass;

/**
 * 同时内部类实现一个接口
 *
 * @author lingwh
 * @date 2019/7/3 10:30
 */
public class InnerClassAndInterfaceTest {

    public static void main(String[] args) {
        new AA().test();
    }
}

interface A {
    void eat();

    void drink();
}

class AA {
    // 接口中只有一个方法
    /*
     public void test(){
     new A(){
     public void eat(){
     System.out.println("eat...");
     }
     }.eat();
     }*/

    // 接口中有多个方法
    public void test() {
        A a = new A() {
            public void eat() {
                System.out.println("eat...");
            }

            public void drink() {
                System.out.println("drink...");
            }
        };
        a.eat();
        a.drink();
    }
}
