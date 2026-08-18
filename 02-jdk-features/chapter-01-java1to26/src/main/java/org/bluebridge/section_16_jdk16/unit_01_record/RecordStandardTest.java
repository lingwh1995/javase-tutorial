package org.bluebridge.section_16_jdk16.unit_01_record;

import org.junit.Test;

/**
 * JDK 16 Record 测试(STANDARD 正式特性)
 *
 * Record(JEP 395) 在 JDK 16 中转正为 STANDARD 正式特性, 不再需要 --enable-preview 参数。
 * record 是一种不可变数据载体, 编译器会根据组件(component)自动生成:
 *   构造器、访问器 x()/y()、equals()、hashCode()、toString() 等方法。
 *
 * 本类直接使用真实 record 语法编写, 在测试类内部定义嵌套 record(隐式 static):
 *   1. Point(int x, int y): 演示紧凑构造器(compact constructor)参数校验
 *   2. Rectangle(Point topLeft, Point bottomRight): 演示 record 的嵌套组合
 *
 * 演化历程: Record JDK 14(1st PREVIEW) → JDK 15(2nd PREVIEW) → JDK 16(JEP 395, STANDARD)
 *
 * @author lingwh
 * @date 2026/08/05 18:38
 */
public class RecordStandardTest {

    /**
     * 定义 record: Point(int x, int y)
     * 通过紧凑构造器(compact constructor)在字段赋值前完成参数校验
     */
    public record Point(int x, int y) {
        // 紧凑构造器: 参数列表为空, 在字段赋值前执行校验逻辑
        // 注意: 嵌套 record 的紧凑构造器必须显式声明 public
        public Point {
            if (x < 0 || y < 0) {
                throw new IllegalArgumentException("坐标不能为负数: x = " + x + ", y = " + y);
            }
        }
    }

    /**
     * 定义嵌套 record: Rectangle(Point topLeft, Point bottomRight)
     * record 的组件可以是另一个 record, 体现 record 的数据组合能力
     */
    public record Rectangle(Point topLeft, Point bottomRight) {
        // record 中的自定义实例方法: 计算矩形面积
        public int area() {
            int width = bottomRight.x() - topLeft.x();
            int height = bottomRight.y() - topLeft.y();
            return width * height;
        }
    }

    // ===== 旧版实现方式(JDK 16 之前): 传统 POJO 类, 需要手动编写大量样板代码 =====
    // static final class PointOld {
    //     private final int x;
    //     private final int y;
    //
    //     public PointOld(int x, int y) {
    //         if (x < 0 || y < 0) {
    //             throw new IllegalArgumentException("坐标不能为负数: x = " + x + ", y = " + y);
    //         }
    //         this.x = x;
    //         this.y = y;
    //     }
    //
    //     public int getX() { return x; }
    //     public int getY() { return y; }
    //
    //     @Override
    //     public boolean equals(Object o) {
    //         if (this == o) return true;
    //         if (o == null || getClass() != o.getClass()) return false;
    //         PointOld that = (PointOld) o;
    //         return x == that.x && y == that.y;
    //     }
    //
    //     @Override
    //     public int hashCode() { return Objects.hash(x, y); }
    //
    //     @Override
    //     public String toString() { return "PointOld[x=" + x + ", y=" + y + "]"; }
    // }
    // ===== 新版实现方式(JDK 16 起): record 一行声明, 编译器自动生成构造器/访问器/equals/hashCode/toString =====
    // public record Point(int x, int y) { ... }

    /**
     * 测试 Record 的创建与 toString()(STANDARD)
     * 编译器自动生成的 toString() 格式为: 类名[组件1=值1, 组件2=值2, ...]
     */
    @Test
    public void testRecordCreateAndToString() {
        Point point = new Point(10, 20);
        System.out.println("record 自动生成的 toString(): " + point);
    }

    /**
     * 测试 Record 自动生成的访问器(component accessor)(STANDARD)
     * record 访问器的命名规则: 组件名 + (), 如 x()、y(), 而不是 getX()
     */
    @Test
    public void testRecordAccessors() {
        Point point = new Point(3, 4);
        System.out.println("point.x() = " + point.x());
        System.out.println("point.y() = " + point.y());
    }

    /**
     * 测试 Record 自动生成的 equals() 与 hashCode()(STANDARD)
     * record 基于所有组件生成 equals() 和 hashCode(), 组件值相同的两个 record 对象相等
     */
    @Test
    public void testRecordEqualsAndHashCode() {
        Point p1 = new Point(1, 2);
        Point p2 = new Point(1, 2);
        Point p3 = new Point(1, 3);
        System.out.println("p1.equals(p2) = " + p1.equals(p2));
        System.out.println("p1.equals(p3) = " + p1.equals(p3));
        System.out.println("p1.hashCode() = " + p1.hashCode());
        System.out.println("p2.hashCode() = " + p2.hashCode());
        System.out.println("组件值相同的 record hashCode 相等: " + (p1.hashCode() == p2.hashCode()));
    }

    /**
     * 测试 Record 紧凑构造器(compact constructor)的参数校验(STANDARD)
     * 紧凑构造器的参数列表为空, 在字段赋值前执行校验逻辑, 校验失败时抛出 IllegalArgumentException
     */
    @Test
    public void testRecordCompactConstructorValidation() {
        Point point = new Point(3, 4);
        System.out.println("合法参数创建: " + point);
        try {
            // 传入负数, 触发紧凑构造器中的参数校验
            Point negative = new Point(-1, 2);
            System.out.println("非法参数创建: " + negative);
        } catch (IllegalArgumentException e) {
            System.out.println("捕获紧凑构造器抛出的异常: " + e.getMessage());
        }
    }

    /**
     * 测试嵌套 record(STANDARD)
     * record 可以嵌套定义, 组件也可以是其他 record, 实现数据的组合
     */
    @Test
    public void testNestedRecord() {
        Rectangle rectangle = new Rectangle(new Point(1, 1), new Point(5, 4));
        System.out.println("嵌套 record Rectangle: " + rectangle);
        System.out.println("Rectangle 的左上角: " + rectangle.topLeft());
        System.out.println("Rectangle 的右下角: " + rectangle.bottomRight());
        System.out.println("Rectangle 的面积: " + rectangle.area());
    }
}
