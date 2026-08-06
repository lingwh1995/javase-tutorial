package org.bluebridge.section_01_jdk1.unit_01_inner_class;

import org.junit.Test;

/**
 * JDK 1.1 内部类(Inner Class)特性测试
 *
 * JDK 1.1 引入了内部类机制, 允许在一个类的内部定义另一个类, 内部类分为以下四种:
 * 1. 成员内部类(Member Inner Class): 定义在类内部、方法外部, 与成员变量和成员方法平级, 依赖外部类对象创建
 * 2. 局部内部类(Local Inner Class): 定义在方法体内部, 作用域仅限于所在方法, 方法结束则无法访问
 * 3. 匿名内部类(Anonymous Inner Class): 没有类名的局部内部类, 必须继承一个父类或实现一个接口
 * 4. 静态内部类(Static Nested Class): 使用 static 修饰的成员内部类, 不依赖外部类实例即可创建
 *
 * @author lingwh
 * @date 2026/08/05 18:25
 */
public class InnerClassTest {

    /**
     * 外部类: 演示成员内部类、局部内部类和静态内部类
     */
    static class OuterClass {

        // 外部类成员变量
        private String name = "外部类";

        // 外部类静态成员变量
        private static String staticMessage = "外部类静态变量";

        /**
         * 成员内部类: 可以访问外部类的所有成员(包括私有成员)
         */
        class MemberInnerClass {

            // 成员内部类成员变量
            private String innerName = "成员内部类";

            public String getInnerName() {
                return innerName;
            }

            /**
             * 通过 外部类名.this 访问外部类的成员变量
             */
            public String getOuterName() {
                return OuterClass.this.name;
            }
        }

        /**
         * 静态内部类: 只能访问外部类的静态成员, 不依赖外部类实例
         */
        static class StaticNestedClass {

            // 静态内部类成员变量
            private String nestedName = "静态内部类";

            public String getNestedName() {
                return nestedName;
            }

            /**
             * 静态内部类只能访问外部类的静态成员
             */
            public String getOuterStaticMessage() {
                return staticMessage;
            }
        }

        /**
         * 测试局部内部类: 局部内部类定义在方法内部
         */
        public String testLocalInnerClass() {
            // 局部内部类: 定义在方法内部, 作用域仅限于当前方法
            class LocalInnerClass {

                // 局部内部类成员变量
                private String localName = "局部内部类";

                public String getLocalName() {
                    return localName;
                }
            }
            // 局部内部类只能在方法内部创建和使用
            LocalInnerClass localInnerClass = new LocalInnerClass();
            return localInnerClass.getLocalName();
        }
    }

    /**
     * 测试成员内部类: 通过外部类实例创建成员内部类对象
     */
    @Test
    public void testMemberInnerClass() {
        // 第一步: 先创建外部类对象
        OuterClass outerClass = new OuterClass();
        // 第二步: 通过外部类对象创建成员内部类对象
        OuterClass.MemberInnerClass memberInnerClass = outerClass.new MemberInnerClass();
        System.out.println("成员内部类名称: " + memberInnerClass.getInnerName());
        // 成员内部类可以访问外部类的私有成员
        System.out.println("通过成员内部类访问外部类私有成员: " + memberInnerClass.getOuterName());
        // 编译器为成员内部类生成的类名格式: 外部类$内部类
        System.out.println("成员内部类的类名: " + memberInnerClass.getClass().getName());
    }

    /**
     * 测试局部内部类: 局部内部类定义在方法内部, 只能在方法内使用
     */
    @Test
    public void testLocalInnerClass() {
        OuterClass outerClass = new OuterClass();
        // 局部内部类在方法内部创建并返回结果
        String localName = outerClass.testLocalInnerClass();
        System.out.println("局部内部类名称: " + localName);
        // 局部内部类定义在方法内部, 方法外部无法直接创建
        // LocalInnerClass localInnerClass = new LocalInnerClass();
    }

    /**
     * 测试匿名内部类: 匿名内部类没有类名, 必须实现接口或继承父类
     */
    @Test
    public void testAnonymousInnerClass() {
        // 匿名内部类: 实现 Runnable 接口
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("匿名内部类实现 Runnable 接口");
            }
        };
        runnable.run();
        // 匿名内部类的类名由编译器自动生成, 如 InnerClassTest$1
        System.out.println("匿名内部类的类名: " + runnable.getClass().getName());
        System.out.println("--------------------------------------");
        // 匿名内部类: 继承 Thread 类
        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println("匿名内部类继承 Thread 类");
            }
        };
        thread.start();
    }

    /**
     * 测试静态内部类: 不依赖外部类实例, 直接通过 new 创建
     */
    @Test
    public void testStaticNestedClass() {
        // 静态内部类直接创建, 不需要外部类实例
        OuterClass.StaticNestedClass staticNestedClass = new OuterClass.StaticNestedClass();
        System.out.println("静态内部类名称: " + staticNestedClass.getNestedName());
        // 静态内部类只能访问外部类的静态成员
        System.out.println("通过静态内部类访问外部类静态成员: " + staticNestedClass.getOuterStaticMessage());
    }
}
