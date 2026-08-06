package org.bluebridge.section_17_jdk17.unit_03_record_enhancement;

import org.junit.Test;

/**
 * JDK 17 Record 增强测试(STANDARD 正式特性)
 *
 * Record(JEP 395) 在 JDK 16 中转正为 STANDARD 正式特性, JDK 17 中继续完善。
 * 本类演示 record 在组合与嵌套场景下的增强用法:
 *   1. 嵌套 record: record 的组件可以是另一个 record, 实现数据的分层组合
 *   2. record 实现接口: record 可以实现一个或多个接口, 提供接口方法的实现
 *   3. 局部 record: 在方法体内定义 record(隐式 static), 适合方法内部临时数据载体
 *
 * @author lingwh
 * @date 2026/08/05 18:46
 */
public class RecordEnhancementTest {

    /**
     * 定义接口: Describable, 提供描述能力
     */
    public interface Describable {
        String describe();
    }

    /**
     * 定义嵌套 record: Address(城市, 街道), 作为 Person 的组件
     */
    public record Address(String city, String street) { }

    /**
     * 定义嵌套 record: Person(姓名, 年龄, 地址)
     * 组件 address 是另一个 record Address, 体现 record 的嵌套组合
     */
    public record Person(String name, int age, Address address) { }

    /**
     * 定义 record 并实现接口: Book(书名, 作者) implements Describable
     * record 通过重写接口抽象方法 describe() 提供自定义描述
     */
    public record Book(String title, String author) implements Describable {
        @Override
        public String describe() {
            return "《" + title + "》, 作者: " + author;
        }
    }

    /**
     * 测试嵌套 record(STANDARD)
     * record 的组件可以是另一个 record, 实现数据的分层组合
     */
    @Test
    public void testNestedRecord() {
        // 内层 record: Address
        Address address = new Address("北京", "中关村大街");
        // 外层 record: Person, 组件中包含内层 record
        Person person = new Person("张三", 25, address);
        System.out.println("嵌套 record Person: " + person);
        System.out.println("person.name() = " + person.name());
        System.out.println("person.age() = " + person.age());
        System.out.println("person.address() = " + person.address());
        System.out.println("嵌套访问地址城市: " + person.address().city());
        System.out.println("嵌套访问地址街道: " + person.address().street());
    }

    /**
     * 测试 record 实现接口(STANDARD)
     * record 可以实现接口, 并通过实例方法实现接口的抽象方法
     */
    @Test
    public void testRecordImplementsInterface() {
        Book book = new Book("Java 核心技术", "Cay S. Horstmann");
        System.out.println("record 自动生成的 toString(): " + book);
        System.out.println("record 实现接口的 describe(): " + book.describe());
        // record 实例可以赋值给接口类型
        Describable describable = new Book("Effective Java", "Joshua Bloch");
        System.out.println("接口类型调用 describe(): " + describable.describe());
    }

    /**
     * 测试局部 record(STANDARD)
     * record 可以在方法体内定义(局部 record, 隐式 static), 适用于方法内部的临时数据载体
     */
    @Test
    public void testLocalRecord() {
        // 方法内定义局部 record: Point(横坐标, 纵坐标)
        record Point(int x, int y) { }

        // 局部 record 在方法内部使用
        Point p1 = new Point(3, 4);
        Point p2 = new Point(3, 4);
        System.out.println("局部 record Point: " + p1);
        System.out.println("p1.x() = " + p1.x() + ", p1.y() = " + p1.y());
        System.out.println("局部 record 自动生成 equals(): " + p1.equals(p2));
        System.out.println("局部 record 自动生成 hashCode(): " + p1.hashCode());
    }
}
