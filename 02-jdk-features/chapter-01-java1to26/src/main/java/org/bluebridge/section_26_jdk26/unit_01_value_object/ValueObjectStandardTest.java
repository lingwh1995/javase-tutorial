package org.bluebridge.section_26_jdk26.unit_01_value_object;

import org.junit.Test;

/**
 * JDK 26 值对象测试(STANDARD 正式特性)
 *
 * 值对象(Value Objects, JEP 468) 在 JDK 26 中转正为 STANDARD 正式特性。
 * 值对象是通过 value class 声明的类, 实例没有对象标识(identity), 两个值对象
 * 如果字段值相同则视为相等(== 比较值而非引用)。
 *
 * 值类特性:
 * 1. 使用 value 关键字修饰类声明
 * 2. 所有字段隐式为 final
 * 3. 不能使用 synchronized 关键字
 * 4. == 运算符比较值相等性而非引用相等性
 * 5. 不能有 extends 子句(隐式继承 ValueObject)
 * 6. 数组存储时 JVM 可扁平化布局, 提升缓存局部性
 *
 * 演化历程: 值对象 JDK 25(1st PREVIEW) → JDK 26(JEP 468, STANDARD)
 *
 * @author lingwh
 * @date 2026/08/06 09:10
 */
public class ValueObjectStandardTest {

    // 定义值类 Point, 表示二维坐标点
    value class Point {
        private final int x;
        private final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int x() {
            return x;
        }

        public int y() {
            return y;
        }

        public double distanceToOrigin() {
            return Math.sqrt(x * x + y * y);
        }

        @Override
        public String toString() {
            return "Point(" + x + ", " + y + ")";
        }
    }

    // 定义值类 Money, 表示金额
    value class Money {
        private final long amount;
        private final String currency;

        public Money(long amount, String currency) {
            this.amount = amount;
            this.currency = currency;
        }

        public long amount() {
            return amount;
        }

        public String currency() {
            return currency;
        }

        public Money add(Money other) {
            if (!this.currency.equals(other.currency)) {
                throw new IllegalArgumentException("币种不匹配");
            }
            return new Money(this.amount + other.amount, this.currency);
        }

        @Override
        public String toString() {
            return amount + " " + currency;
        }
    }

    /**
     * 测试值类的基本创建和使用(STANDARD)
     * 值类使用 new 关键字创建实例, 行为与普通类相似,
     * 但 JVM 内部会优化为扁平化内存布局
     */
    @Test
    public void testValueObjectBasic() {
        // 创建值类实例
        Point p1 = new Point(3, 4);
        Point p2 = new Point(3, 4);
        Point p3 = new Point(5, 6);

        System.out.println("p1 = " + p1);
        System.out.println("p2 = " + p2);
        System.out.println("p3 = " + p3);
        System.out.println("--------------------------------------");

        // 值类 == 比较的是值相等性, 而非引用相等性
        System.out.println("p1 == p2: " + (p1 == p2) + " (值相等, 返回 true)");
        System.out.println("p1 == p3: " + (p1 == p3) + " (值不等, 返回 false)");
        System.out.println("--------------------------------------");

        // 调用值类方法
        System.out.println("p1 到原点的距离: " + p1.distanceToOrigin());
        System.out.println("p3 到原点的距离: " + p3.distanceToOrigin());
    }

    /**
     * 测试值类的不可变性和操作方法(STANDARD)
     * 值类的所有字段都是 final 的, 实例不可变。
     * 操作方法应返回新的实例而非修改当前实例。
     */
    @Test
    public void testValueObjectImmutability() {
        // 创建金额值类实例
        Money price = new Money(100, "CNY");
        Money tax = new Money(13, "CNY");

        System.out.println("price = " + price);
        System.out.println("tax = " + tax);
        System.out.println("--------------------------------------");

        // 调用 add 方法返回新实例, 原实例不变
        Money total = price.add(tax);
        System.out.println("price + tax = " + total);
        System.out.println("原始 price 不变: " + price);
        System.out.println("原始 tax 不变: " + tax);
        System.out.println("--------------------------------------");

        // 值类实例之间 == 比较
        Money samePrice = new Money(100, "CNY");
        System.out.println("price == samePrice: " + (price == samePrice) + " (值相等)");
    }

    /**
     * 测试值类在数组中的性能优势(STANDARD)
     * 值类数组在 JVM 中采用扁平化布局, 字段连续存储,
     * 无需对象头和对齐填充, 可以有效提升缓存局部性
     */
    @Test
    public void testValueObjectArray() {
        // 创建值类数组
        Point[] points = new Point[5];
        points[0] = new Point(0, 0);
        points[1] = new Point(1, 1);
        points[2] = new Point(2, 2);
        points[3] = new Point(3, 3);
        points[4] = new Point(4, 4);

        // 遍历数组
        for (int i = 0; i < points.length; i++) {
            System.out.println("points[" + i + "] = " + points[i]
                    + ", 到原点距离: " + points[i].distanceToOrigin());
        }
        System.out.println("--------------------------------------");

        // 值类数组的元素 == 比较
        System.out.println("points[0] == new Point(0, 0): "
                + (points[0] == new Point(0, 0)) + " (值相等)");
    }

    /**
     * 测试值类在集合框架中的使用(STANDARD)
     * 值类可以作为泛型类型参数使用, 在集合中存储时
     * JVM 会进行必要的装箱/拆箱操作
     */
    @Test
    public void testValueObjectInCollection() {
        // 值类在 List 中使用
        java.util.List<Point> pointList = java.util.List.of(
                new Point(1, 2),
                new Point(3, 4),
                new Point(5, 6)
        );
        System.out.println("值类 List: " + pointList);
        System.out.println("--------------------------------------");

        // 值类在 Map 中作为 key 使用
        java.util.Map<Point, String> pointMap = new java.util.HashMap<>();
        pointMap.put(new Point(0, 0), "原点");
        pointMap.put(new Point(1, 0), "X轴正方向单位点");
        pointMap.put(new Point(0, 1), "Y轴正方向单位点");

        System.out.println("值类 Map: " + pointMap);
        System.out.println("查找 Point(0,0): " + pointMap.get(new Point(0, 0)));
    }
}