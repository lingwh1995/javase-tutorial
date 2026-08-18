package org.bluebridge.section_17_jdk17_lts.unit_03_record;

import org.junit.Test;

/**
 * JDK 17 Record 测试(STANDARD 正式特性, JEP 395, 从 JDK 16 转正)
 *
 * record 在 JDK 16 中转正为 STANDARD 正式特性, 不再需要 --enable-preview 参数。
 * record 是一种不可变数据载体, 编译器自动生成构造器、访问器、equals()、hashCode()、toString()。
 *
 * record 的特点:
 * 1. 所有字段默认为 private final
 * 2. 自动生成全参构造器(可定义紧凑构造器增加校验逻辑)
 * 3. 自动生成组件访问器(如 x(), 而不是 getX())
 * 4. 可以定义静态方法、静态字段、实例方法
 * 5. 不可继承(隐式 final), 但可以实现接口
 * 6. 嵌套 record 隐式为 static
 *
 * @author lingwh
 * @date 2026/08/06 09:18
 */
public class RecordTest {

    /**
     * 定义 record: Person(String name, int age)
     * 使用紧凑构造器在字段赋值前完成参数校验
     */
    public record Person(String name, int age) {
        // 紧凑构造器: 参数列表为空, 在字段赋值前执行校验逻辑
        Person {
            if (name == null || name.isBlank()) {
                throw new IllegalArgumentException("姓名不能为空");
            }
            if (age < 0 || age > 150) {
                throw new IllegalArgumentException("年龄必须在 0-150 之间: " + age);
            }
        }

        // record 中的静态方法
        public static Person of(String name, int age) {
            return new Person(name, age);
        }

        // record 中的静态方法: 返回默认 Person 实例
        public static Person unknown() {
            return new Person("未知", 0);
        }

        // record 中的实例方法
        public String greeting() {
            return "你好, 我是 " + name() + ", 今年 " + age() + " 岁";
        }
    }

    /**
     * 定义 record: OrderItem(String productName, int quantity, double price)
     * 演示 record 的静态方法、实例方法以及数据计算能力
     */
    public record OrderItem(String productName, int quantity, double price) {
        // 紧凑构造器: 校验数量为正数
        public OrderItem {
            if (quantity <= 0) {
                throw new IllegalArgumentException("数量必须大于 0: " + quantity);
            }
            if (price < 0) {
                throw new IllegalArgumentException("价格不能为负数: " + price);
            }
        }

        // 实例方法: 计算小计金额
        public double subtotal() {
            return quantity * price;
        }

        // 静态工厂方法
        public static OrderItem of(String productName, int quantity, double price) {
            return new OrderItem(productName, quantity, price);
        }
    }

    /**
     * 测试 Record 的创建与自动生成的 toString()(STANDARD)
     * toString() 格式为: 类名[组件1=值1, 组件2=值2, ...]
     */
    @Test
    public void testRecordCreateAndToString() {
        Person person = new Person("张三", 25);
        System.out.println("Person record 自动生成的 toString(): " + person);

        OrderItem item = new OrderItem("笔记本电脑", 2, 5999.99);
        System.out.println("OrderItem record 自动生成的 toString(): " + item);
    }

    /**
     * 测试 Record 自动生成的访问器(accessor methods)(STANDARD)
     * 访问器命名规则: 组件名 + (), 如 name()、age(), 而不是 getName()、getAge()
     */
    @Test
    public void testRecordAccessors() {
        Person person = new Person("李四", 30);
        System.out.println("person.name() = " + person.name());
        System.out.println("person.age() = " + person.age());

        OrderItem item = new OrderItem("手机", 1, 3999.00);
        System.out.println("item.productName() = " + item.productName());
        System.out.println("item.quantity() = " + item.quantity());
        System.out.println("item.price() = " + item.price());
    }

    /**
     * 测试 Record 自动生成的 equals() 与 hashCode()(STANDARD)
     * 基于所有组件生成, 组件值相同的两个 record 对象相等
     */
    @Test
    public void testRecordEqualsAndHashCode() {
        Person p1 = new Person("王五", 28);
        Person p2 = new Person("王五", 28);
        Person p3 = new Person("王五", 29);

        System.out.println("p1.equals(p2) = " + p1.equals(p2));
        System.out.println("p1.equals(p3) = " + p1.equals(p3));
        System.out.println("p1.hashCode() = " + p1.hashCode());
        System.out.println("p2.hashCode() = " + p2.hashCode());
        System.out.println("组件值相同的 record hashCode 相等: " + (p1.hashCode() == p2.hashCode()));
    }

    /**
     * 测试 Record 紧凑构造器(compact constructor)的参数校验(STANDARD)
     * 紧凑构造器在字段赋值前执行, 可以添加参数校验逻辑
     */
    @Test
    public void testRecordCompactConstructorValidation() {
        // 合法参数
        Person person = new Person("赵六", 20);
        System.out.println("合法参数创建: " + person);

        // 非法参数: 姓名为空
        try {
            Person invalid = new Person("", 20);
            System.out.println("非法参数创建: " + invalid);
        } catch (IllegalArgumentException e) {
            System.out.println("捕获紧凑构造器异常(空姓名): " + e.getMessage());
        }

        // 非法参数: 年龄为负数
        try {
            Person invalid = new Person("测试", -5);
            System.out.println("非法参数创建: " + invalid);
        } catch (IllegalArgumentException e) {
            System.out.println("捕获紧凑构造器异常(负数年龄): " + e.getMessage());
        }
    }

    /**
     * 测试 Record 的静态方法(STANDARD)
     * record 中可以定义静态方法和静态字段
     */
    @Test
    public void testRecordStaticMethod() {
        // 测试静态工厂方法
        Person person = Person.of("孙七", 35);
        System.out.println("静态工厂方法创建: " + person);

        // 测试静态方法返回默认实例
        Person unknown = Person.unknown();
        System.out.println("默认实例: " + unknown);

        // 测试 OrderItem 的静态工厂方法
        OrderItem item = OrderItem.of("键盘", 3, 199.00);
        System.out.println("OrderItem 静态工厂方法创建: " + item);
    }

    /**
     * 测试 Record 的实例方法(STANDARD)
     * record 中可定义自定义实例方法, 基于组件数据进行计算
     */
    @Test
    public void testRecordInstanceMethod() {
        Person person = new Person("周八", 22);
        System.out.println(person.greeting());

        OrderItem item = new OrderItem("显示器", 2, 2499.00);
        System.out.println("商品: " + item.productName());
        System.out.println("数量: " + item.quantity());
        System.out.println("单价: " + item.price());
        System.out.println("小计: " + item.subtotal());
    }
}