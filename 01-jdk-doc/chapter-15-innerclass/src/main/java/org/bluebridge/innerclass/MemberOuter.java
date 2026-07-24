package org.bluebridge.innerclass;

/**
 * 成员内部类
 *
 * @author lingwh
 * @date 2019/7/3 10:26
 */
public class MemberOuter {

    public static void main(String[] args) {
        // 外部类对象
        MemberOuter memberOuter = new MemberOuter();
        // 直接通过调用外部类方法，在通过外部类方法调用内部类方法，实现外部类直接访问内部类方法
        memberOuter.outerShow();
        // 创造内部类对象
        MemberInner memberInner = memberOuter.new MemberInner();
        // 通过内部类访问内部类方法
        // memberInner.innerShow();

        /*
         * 可在 Outer 中定义 get 方法，获得 Inner 对象，那么使用时，只需outer.getInnerInstance()即可。
         * public Inner getInnerInstance(Inner 类的构造方法参数){
         * return new Inner(参数);
         * }
         */
    }

    private int outerVariable = 1;
    private int commonVariable = 2;
    private static int outerStaticVariable = 3;

    // 省略 getter/setter

    /**
     * 成员方法
     */
    public void outerMethod() {
        System.out.println("我是外部类的outerMethod方法");
    }

    /**
     * 静态方法
     */
    public static void outerStaticMethod() {
        System.out.println("我是外部类的outerStaticMethod静态方法");
    }

    /**
     * 内部类
     */
    public class MemberInner {
        private int commonVariable = 20;

        /**
         * 构造方法
         */
        public MemberInner() {
        }

        /**
         * 成员方法，访问外部类信息（属性、方法）
         */
        public void innerShow() {
            // 当和外部类冲突时，直接引用属性名，是内部类的成员属性
            System.out.println("内部的commonVariable:" + commonVariable);
            System.out.println("内部的commonVariable:" + this.commonVariable);
            System.out.println("内部的commonVariable:" + MemberInner.this.commonVariable);
            // 内部类访问外部属性
            System.out.println("outerVariable:" + outerVariable);
            // 当和外部类属性名重叠时，可通过外部类名.this.属性名
            System.out.println("外部的commonVariable:" + MemberOuter.this.commonVariable);
            System.out.println("outerStaticVariable:" + outerStaticVariable);
            // 访问外部类的方法
            outerMethod();
            outerStaticMethod();
        }
    }

    /**
     * 外部类访问内部类信息
     */
    public void outerShow() {
        MemberInner memberInner = new MemberInner();
        memberInner.innerShow();
    }
}
