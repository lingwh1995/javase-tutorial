package org.bluebridge.section_15_jdk15.unit_02_sealed;

import org.junit.Test;

/**
 * JDK 15 密封类（PREVIEW 特性，JEP 360）
 * 密封类允许一个类或接口指定哪些类可以实现或继承它，提供更精细的继承控制
 * 注意：该特性在 JDK 15 为预览特性，编译和运行需要 --enable-preview
 *
 * 演化历程: Sealed 类 JDK 15(JEP 360, 1st PREVIEW) → JDK 16(JEP 397, 2nd PREVIEW) → JDK 17(JEP 409, STANDARD)
 *
 * @author lingwh
 * @date 2026/08/06 02:18
 */
public class SealedPreviewTest {

    // 定义密封接口 Shape，只允许 Circle 和 Square 实现
    sealed interface Shape permits Circle, Square { }

    // Circle 实现 Shape，标记为 final 禁止继续继承
    static final class Circle implements Shape {
        private final double radius;

        Circle(double radius) {
            this.radius = radius;
        }

        public double getRadius() {
            return radius;
        }

        public double area() {
            return Math.PI * radius * radius;
        }
    }

    // Square 实现 Shape，标记为 final 禁止继续继承
    static final class Square implements Shape {
        private final double side;

        Square(double side) {
            this.side = side;
        }

        public double getSide() {
            return side;
        }

        public double area() {
            return side * side;
        }
    }

    // 密封类层级：Animal -> Mammal -> (Dog, Cat)
    sealed abstract class Animal permits Mammal { }

    // Mammal 既是 sealed 的子类，也是 permits 的父类
    sealed abstract class Mammal extends Animal permits Dog, Cat { }

    static final class Dog extends Mammal {
        public String bark() {
            return "汪汪";
        }
    }

    static final class Cat extends Mammal {
        public String meow() {
            return "喵喵";
        }
    }

    /**
     * 测试密封接口的基本用法（JDK 15 PREVIEW 特性，需要 --enable-preview）
     */
    @Test
    public void testSealedInterface_Preview() {
        // 创建密封接口的实现类实例
        Shape circle = new Circle(5.0);
        Shape square = new Square(4.0);

        // 通过 sealed 接口调用方法
        System.out.println("Circle area: " + ((Circle) circle).area());
        System.out.println("Square area: " + ((Square) square).area());

        // 使用 instanceof 模式匹配（JDK 14 预览特性）
        if (circle instanceof Circle c) {
            System.out.println("Circle radius: " + c.getRadius());
        }
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试密封继承层级（JDK 15 PREVIEW 特性，需要 --enable-preview）
     */
    @Test
    public void testSealedHierarchy_Preview() {
        // 多层密封架构：Animal -> Mammal -> Dog/Cat
        Mammal dog = new Dog();
        Mammal cat = new Cat();

        // 验证密封层级中的方法调用
        System.out.println("Dog says: " + ((Dog) dog).bark());
        System.out.println("Cat says: " + ((Cat) cat).meow());

        // 验证密封类禁止未知子类扩展
        System.out.println("Dog is Mammal: " + (dog instanceof Mammal));
        System.out.println("Cat is Mammal: " + (cat instanceof Mammal));
        System.out.println("Dog is Animal: " + (dog instanceof Animal));
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试密封类的模式匹配与 exhaustiveness（JDK 15 PREVIEW 特性，需要 --enable-preview）
     * 密封类配合 switch 模式匹配可以实现穷举检查
     */
    @Test
    public void testSealedWithPatternMatching_Preview() {
        // 使用密封类实现形状面积计算
        Shape circle = new Circle(3.0);
        Shape square = new Square(2.5);

        printArea(circle);
        printArea(square);
        System.out.println("--- 分割线 ---");
    }

    private void printArea(Shape shape) {
        // 使用 instanceof 模式匹配处理密封类的所有子类型
        if (shape instanceof Circle c) {
            System.out.println("Circle area: " + c.area());
        } else if (shape instanceof Square s) {
            System.out.println("Square area: " + s.area());
        }
    }
}