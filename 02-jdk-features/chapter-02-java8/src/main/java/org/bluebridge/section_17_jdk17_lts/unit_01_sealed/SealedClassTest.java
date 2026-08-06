package org.bluebridge.section_17_jdk17_lts.unit_01_sealed;

import org.junit.Test;

/**
 * JDK 17 Sealed 密封类测试(STANDARD 正式特性, JEP 409)
 *
 * Sealed 类在 JDK 17 中转正为 STANDARD 正式特性, 不再需要 --enable-preview 参数。
 * sealed 关键字用于限制类的继承, 通过 permits 子句明确指定哪些子类可以继承该类。
 * 密封类适用于领域建模, 可以精确控制类型层次结构。
 *
 * 密封类(接口)的规则:
 * 1. 使用 sealed 关键字修饰, 并使用 permits 列出允许的子类
 * 2. 子类必须使用 final、sealed 或 non-sealed 修饰
 * 3. 密封类及其子类必须在同一个模块(module)或同一个包中
 *
 * 本类在内部定义密封接口 Shape 及其实现, 演示 sealed 关键字的真实语法。
 *
 * @author lingwh
 * @date 2026/08/05 19:12
 */
public class SealedClassTest {

    /**
     * 密封接口: Shape, 只允许 Circle 和 Rectangle 实现
     */
    public sealed interface Shape permits Circle, Rectangle {
        double area();
    }

    /**
     * Circle 是 Shape 的允许实现, 使用 final 关键字终止密封扩展
     */
    public static final class Circle implements Shape {
        private final double radius;

        public Circle(double radius) {
            this.radius = radius;
        }

        @Override
        public double area() {
            return Math.PI * radius * radius;
        }

        public double radius() {
            return radius;
        }
    }

    /**
     * Rectangle 是 Shape 的允许实现, 使用 final 关键字终止密封扩展
     */
    public static final class Rectangle implements Shape {
        private final double width;
        private final double height;

        public Rectangle(double width, double height) {
            this.width = width;
            this.height = height;
        }

        @Override
        public double area() {
            return width * height;
        }

        public double width() {
            return width;
        }

        public double height() {
            return height;
        }
    }

    /**
     * 测试 Sealed 密封类的基本用法: 创建密封类的子类实例并调用方法
     */
    @Test
    public void testSealedClassBasic() {
        Shape circle = new Circle(5.0);
        Shape rectangle = new Rectangle(3.0, 4.0);

        System.out.println("Circle 面积: " + circle.area());
        System.out.println("Rectangle 面积: " + rectangle.area());
    }

    /**
     * 测试 Sealed 密封类的类型匹配: 使用 instanceof 模式匹配处理密封类
     */
    @Test
    public void testSealedClassPatternMatching() {
        Shape shape = new Circle(2.5);

        // 密封类配合 instanceof 模式匹配, 可以穷举所有子类型
        if (shape instanceof Circle c) {
            System.out.println("圆形: 半径 = " + c.radius() + ", 面积 = " + c.area());
        } else if (shape instanceof Rectangle r) {
            System.out.println("矩形: 宽 = " + r.width() + ", 高 = " + r.height() + ", 面积 = " + r.area());
        }
    }

    /**
     * 测试 Sealed 密封类的 type test 模式: 展示密封类在 switch 表达式中的穷举性
     */
    @Test
    public void testSealedClassSwitch() {
        Shape circle = new Circle(4.0);
        Shape rectangle = new Rectangle(5.0, 6.0);

        printShapeArea(circle);
        printShapeArea(rectangle);
    }

    /**
     * 使用传统 if-else 处理密封类, 编译器可以推断出所有分支已被覆盖
     */
    private void printShapeArea(Shape shape) {
        if (shape instanceof Circle c) {
            System.out.println("圆形面积: " + c.area());
        } else if (shape instanceof Rectangle r) {
            System.out.println("矩形面积: " + r.area());
        }
    }

    /**
     * 测试 Sealed 密封类的类型信息: 通过反射获取类的修饰符
     */
    @Test
    public void testSealedClassReflection() {
        // 检查密封类的修饰符
        System.out.println("Shape 是否为密封接口: " + Shape.class.isSealed());
        System.out.println("Circle 是否为 final 类: " + java.lang.reflect.Modifier.isFinal(Circle.class.getModifiers()));
        System.out.println("Rectangle 是否为 final 类: " + java.lang.reflect.Modifier.isFinal(Rectangle.class.getModifiers()));

        // 获取密封类的允许子类
        Class<?>[] permittedSubclasses = Shape.class.getPermittedSubclasses();
        System.out.println("Shape 允许的子类数量: " + permittedSubclasses.length);
        for (Class<?> subclass : permittedSubclasses) {
            System.out.println("  允许子类: " + subclass.getSimpleName());
        }
    }
}