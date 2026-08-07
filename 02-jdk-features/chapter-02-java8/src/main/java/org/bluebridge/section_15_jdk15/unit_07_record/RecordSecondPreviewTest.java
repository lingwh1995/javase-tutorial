package org.bluebridge.section_15_jdk15.unit_07_record;

import org.junit.Test;

/**
 * JDK 15 Record 第二次预览（PREVIEW 特性，JEP 384）
 * Record(JEP 359) 在 JDK 14 作为第一次预览特性引入，JDK 15 进行第二次预览（JEP 384），JDK 16 正式转正（JEP 395, STANDARD）
 * 相比 JDK 14 的变化：支持密封类型（record 可实现 sealed 接口）、record 嵌套、局部 record、本地枚举
 * 注意：该特性在 JDK 15 为预览特性，编译和运行需要 --enable-preview
 *
 * 演化历程：JDK 14(JEP 359, 1st PREVIEW) → JDK 15(JEP 384, 2nd PREVIEW) → JDK 16(JEP 395, STANDARD)
 *
 * @author lingwh
 * @date 2026/08/06 18:39
 */
public class RecordSecondPreviewTest {

    /**
     * JDK 15 PREVIEW 特性的真实 record 定义（嵌套 record，隐式 static）
     * 编译器自动生成规范构造器、访问器、equals()、hashCode()、toString()
     */
    record Point(int x, int y) { }

    // 密封接口 Shape，只允许 Circle 和 Rectangle 两个 record 实现
    sealed interface Shape permits Circle, Rectangle { }

    // record 实现密封接口（record 隐式 final，满足 sealed 的约束）
    record Circle(double radius) implements Shape { }

    // record 实现密封接口
    record Rectangle(double width, double height) implements Shape { }

    /**
     * 测试 Record 基本用法（JDK 15 PREVIEW 特性，需要 --enable-preview）
     */
    @Test
    public void testRecordBasic_Preview() {
        // 通过构造器创建 record 对象
        Point point = new Point(10, 20);
        System.out.println("record 创建的 Point: " + point);
        System.out.println("x() = " + point.x() + ", y() = " + point.y());
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试局部 record（JDK 15 新增能力，在方法内定义 record）
     */
    @Test
    public void testLocalRecord_Preview() {
        // 局部 record：在方法内定义，仅在本方法作用域内可用
        record LocalRecord(int id, String name) { }
        LocalRecord local = new LocalRecord(1, "Java 15");
        System.out.println("局部 record: " + local);
        System.out.println("id() = " + local.id() + ", name() = " + local.name());
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试本地枚举（JDK 15 新增能力，在方法内定义 enum）
     */
    @Test
    public void testLocalEnum_Preview() {
        // 本地枚举：在方法内定义
        enum Color { RED, GREEN, BLUE }
        Color color = Color.RED;
        System.out.println("本地枚举: " + color);
        System.out.println("枚举序号: " + color.ordinal());
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试 record 实现密封接口（JDK 15 新增能力）
     */
    @Test
    public void testRecordWithSealedInterface_Preview() {
        // 通过密封接口引用 record 实现类
        Shape circle = new Circle(5.0);
        Shape rectangle = new Rectangle(4.0, 3.0);
        System.out.println("Circle: " + circle);
        System.out.println("Rectangle: " + rectangle);
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试 record 嵌套（record 作为其他 record 的组件）
     */
    @Test
    public void testNestedRecord_Preview() {
        // record 嵌套：一个 record 作为另一个 record 的组件
        record Address(String city, String street) { }
        record Person(String name, Address address) { }
        Address address = new Address("北京", "中关村大街");
        Person person = new Person("张三", address);
        System.out.println("嵌套 record: " + person);
        System.out.println("城市: " + person.address().city());
        System.out.println("--- 分割线 ---");
    }
}
