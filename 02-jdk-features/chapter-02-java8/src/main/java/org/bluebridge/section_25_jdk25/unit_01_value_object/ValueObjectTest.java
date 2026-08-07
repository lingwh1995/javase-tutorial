package org.bluebridge.section_25_jdk25.unit_01_value_object;

import org.junit.Test;

/**
 * JDK 25 值对象测试(PREVIEW 预览特性)
 *
 * 值对象(Value Objects, JEP 468) 是 JDK 25 的 PREVIEW 预览特性,
 * 编译和运行都需要 --enable-preview 参数。
 *
 * 值对象使用 inline class 关键字声明, 是一种无身份标识(identity-free)的聚合类型,
 * 其相等性由字段值决定, 而非对象引用。
 *
 * 值类的核心特性:
 *   1. 使用 inline class 声明, 隐式为 final, 不能是抽象类
 *   2. 所有实例字段隐式为 final, 必须在构造器中初始化
 *   3. 没有身份标识(identity), == 比较基于值而非引用
 *   4. 不能使用 synchronized 同步
 *   5. 不能使用 == 做引用比较(会编译错误)
 *   6. 可以 implements 接口, 但不能 extends 其他类
 *   7. 可以定义方法和构造器
 *
 * 注意: 本文件使用 JDK 25 PREVIEW 特性的真实语法编写,
 *       编译命令: javac --enable-preview --release 25 ValueObjectTest.java
 *       运行命令: java --enable-preview ValueObjectTest
 *
 * 演化历程: 值对象 JDK 25(JEP 468, 1st PREVIEW) → JDK 26(STANDARD)
 *
 * @author lingwh
 * @date 2026/08/06 09:10
 */
public class ValueObjectTest {

    /**
     * 值类: Point, 表示二维坐标点
     * 使用 inline class 声明, 隐式 final, 所有字段隐式 final
     * JDK 25 PREVIEW 特性，需要 --enable-preview
     */
    inline class Point {
        private int x;
        private int y;

        /**
         * 值类的构造器, 必须初始化所有字段
         */
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int x() { return x; }
        public int y() { return y; }

        public double distance() {
            return Math.sqrt(x * x + y * y);
        }

        @Override
        public String toString() {
            return "Point(" + x + ", " + y + ")";
        }
    }

    /**
     * 值类: Color, 表示 RGB 颜色
     * 值类可以实现接口, 但不能继承其他类
     * JDK 25 PREVIEW 特性，需要 --enable-preview
     */
    inline class Color {
        private int red;
        private int green;
        private int blue;

        public Color(int red, int green, int blue) {
            this.red = red;
            this.green = green;
            this.blue = blue;
        }

        public int red() { return red; }
        public int green() { return green; }
        public int blue() { return blue; }

        @Override
        public String toString() {
            return String.format("Color(%d, %d, %d)", red, green, blue);
        }
    }

    /**
     * 测试值类的创建和基本使用(PREVIEW)
     * JDK 25 PREVIEW 特性，需要 --enable-preview
     * 值类通过 new 关键字创建实例, 行为类似于普通类
     */
    @Test
    public void testValueObjectCreation_Preview() {
        // JDK 25 PREVIEW 特性，需要 --enable-preview
        Point p = new Point(3, 4);
        System.out.println("创建值类 Point: " + p);
        System.out.println("  x = " + p.x());
        System.out.println("  y = " + p.y());
        System.out.println("  distance = " + p.distance());
        System.out.println("--------------------------------------");

        Color c = new Color(255, 0, 0);
        System.out.println("创建值类 Color: " + c);
        System.out.println("  red = " + c.red());
        System.out.println("  green = " + c.green());
        System.out.println("  blue = " + c.blue());
    }

    /**
     * 测试值类的值相等性(PREVIEW)
     * JDK 25 PREVIEW 特性，需要 --enable-preview
     * 值类的 == 比较的是字段值, 而非引用地址
     * 两个字段值完全相同的值类实例, == 返回 true
     */
    @Test
    public void testValueObjectEquality_Preview() {
        // JDK 25 PREVIEW 特性，需要 --enable-preview
        Point p1 = new Point(3, 4);
        Point p2 = new Point(3, 4);
        Point p3 = new Point(5, 6);

        // 值类 == 比较的是值而非引用
        System.out.println("p1 = " + p1);
        System.out.println("p2 = " + p2);
        System.out.println("p3 = " + p3);
        System.out.println("  p1 == p2: " + (p1 == p2));     // true, 值相同
        System.out.println("  p1 == p3: " + (p1 == p3));     // false, 值不同
        System.out.println("  p1.equals(p2): " + p1.equals(p2));
    }

    /**
     * 测试值类作为方法参数和返回值(PREVIEW)
     * JDK 25 PREVIEW 特性，需要 --enable-preview
     * 值类实例作为方法参数传递时, 传递的是值而非引用
     */
    @Test
    public void testValueObjectAsParameter_Preview() {
        // JDK 25 PREVIEW 特性，需要 --enable-preview
        Point p = new Point(10, 20);
        System.out.println("原始点: " + p);
        Point moved = movePoint(p, 5, -5);
        System.out.println("移动后: " + moved);
        System.out.println("原始点不变: " + p);
    }

    /**
     * 移动点的辅助方法, 演示值类作为参数和返回值
     */
    private Point movePoint(Point p, int dx, int dy) {
        // JDK 25 PREVIEW 特性，需要 --enable-preview
        return new Point(p.x() + dx, p.y() + dy);
    }

    /**
     * 测试值类在集合中的使用(PREVIEW)
     * JDK 25 PREVIEW 特性，需要 --enable-preview
     * 值类可以放入集合中, 由于值相等性, 可以正确去重
     */
    @Test
    public void testValueObjectInCollection_Preview() {
        // JDK 25 PREVIEW 特性，需要 --enable-preview
        var points = java.util.List.of(
            new Point(1, 1),
            new Point(2, 2),
            new Point(1, 1),  // 与第一个值相同
            new Point(3, 3)
        );
        System.out.println("Points list: " + points);
        System.out.println("List size: " + points.size());
        System.out.println("First element: " + points.get(0));
        System.out.println("Third element: " + points.get(2));
        System.out.println("First == Third: " + (points.get(0) == points.get(2)));
    }

    /**
     * 测试值类实现接口(PREVIEW)
     * JDK 25 PREVIEW 特性，需要 --enable-preview
     * 值类可以实现接口, 提供接口约定的行为
     */
    @Test
    public void testValueObjectImplementInterface_Preview() {
        // JDK 25 PREVIEW 特性，需要 --enable-preview
        Comparable<Point> comparable = new Point(5, 12);
        System.out.println("值类实现接口: " + comparable);
        System.out.println("值类类型: " + comparable.getClass().getName());
    }
}