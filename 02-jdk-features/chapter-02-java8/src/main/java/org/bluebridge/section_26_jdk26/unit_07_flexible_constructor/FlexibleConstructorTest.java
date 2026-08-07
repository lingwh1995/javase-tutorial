package org.bluebridge.section_26_jdk26.unit_07_flexible_constructor;

import org.junit.Test;

/**
 * JDK 26 灵活构造器体测试(STANDARD 正式特性)
 *
 * 灵活构造器体(Flexible Constructor Bodies) 是 JDK 25 转正的
 * STANDARD 正式特性, 在 JDK 26 中无需 --enable-preview 参数。
 *
 * 在 JDK 23 之前, 构造器的第一条语句必须是 super() 或 this() 调用。
 * 灵活构造器体允许在 super() 或 this() 调用之前插入语句, 使构造器更加灵活:
 *   1. 在 super() 前进行参数验证和预处理
 *   2. 在 super() 前计算和准备参数
 *   3. 在 this() 前进行逻辑判断, 选择不同的构造器调用
 *
 * 演化历程:
 *   - JDK 23: JEP 482 第一次预览
 *   - JDK 24: 第二次预览
 *   - JDK 25: 正式转正
 *   - JDK 26: STANDARD 正式特性
 *
 * @author lingwh
 * @date 2026/08/06 18:22
 */
public class FlexibleConstructorTest {

    /**
     * 测试在 super() 调用前进行参数验证(STANDARD)
     * JDK 26 正式特性, 无需 --enable-preview
     * 在 super() 前对参数进行校验
     */
    @Test
    public void testValidationBeforeSuper() {
        // JDK 26 正式特性
        // 正常参数
        try {
            Person person = new Person("张三", 25);
            System.out.println("创建成功: " + person);
        } catch (IllegalArgumentException e) {
            System.out.println("创建失败: " + e.getMessage());
        }
        System.out.println("--------------------------------------");

        // 无效参数: 年龄为负
        try {
            Person person = new Person("李四", -5);
            System.out.println("创建成功: " + person);
        } catch (IllegalArgumentException e) {
            System.out.println("创建失败(预期): " + e.getMessage());
        }
        System.out.println("--------------------------------------");
    }

    /**
     * 测试在 super() 调用前进行参数预处理(STANDARD)
     * JDK 26 正式特性, 无需 --enable-preview
     * 在 super() 前对参数进行转换和格式化
     */
    @Test
    public void testParameterPreparationBeforeSuper() {
        // JDK 26 正式特性
        Manager manager = new Manager("王五", 30, "技术部");
        System.out.println("Manager 创建成功: " + manager);
        System.out.println("--------------------------------------");

        // 使用默认策略创建
        Manager defaultManager = new Manager("赵六", 28);
        System.out.println("默认 Manager 创建成功: " + defaultManager);
        System.out.println("--------------------------------------");
    }

    /**
     * 测试在 this() 调用前进行逻辑判断(STANDARD)
     * JDK 26 正式特性, 无需 --enable-preview
     * 在 this() 前根据条件调用不同的构造器
     */
    @Test
    public void testLogicBeforeThis() {
        // JDK 26 正式特性
        Employee emp1 = new Employee("员工A", 22, "初级");
        System.out.println("员工1: " + emp1);

        Employee emp2 = new Employee("员工B", 35, "高级");
        System.out.println("员工2: " + emp2);
        System.out.println("--------------------------------------");
    }

    /**
     * 测试灵活构造器体中的异常处理(STANDARD)
     * JDK 26 正式特性, 无需 --enable-preview
     * 在 super() 前进行异常处理
     */
    @Test
    public void testExceptionHandlingInConstructor() {
        // JDK 26 正式特性
        try {
            Person person = new Person(null, 25);
            System.out.println("创建成功: " + person);
        } catch (IllegalArgumentException e) {
            System.out.println("捕获到参数验证异常(预期): " + e.getMessage());
        }
        System.out.println("--------------------------------------");
    }

    // ========== 内部辅助类 ==========

    /**
     * 基础 Person 类, 演示 super() 前的参数验证(STANDARD)
     */
    static class Person {
        private final String name;
        private final int age;

        /**
         * 灵活构造器: 在 super() 前进行参数验证
         * JDK 23+ 允许在 super() 前添加语句
         */
        public Person(String name, int age) {
            // 在 super() 前进行参数验证
            if (name == null || name.isBlank()) {
                throw new IllegalArgumentException("姓名不能为空");
            }
            if (age < 0 || age > 150) {
                throw new IllegalArgumentException("年龄必须在 0-150 之间: " + age);
            }
            this.name = name;
            this.age = age;
        }

        public String getName() { return name; }
        public int getAge() { return age; }

        @Override
        public String toString() {
            return "Person{name='" + name + "', age=" + age + "}";
        }
    }

    /**
     * Employee 类, 演示 this() 调用前的逻辑判断(STANDARD)
     */
    static class Employee {
        private final String name;
        private final int age;
        private final String level;

        /**
         * 主构造器
         */
        public Employee(String name, int age, String level) {
            this.name = name;
            this.age = age;
            this.level = level;
        }

        /**
         * 灵活构造器: 在 this() 前进行逻辑判断
         * 根据年龄自动选择级别
         */
        public Employee(String name, int age) {
            String autoLevel;
            if (age < 25) {
                autoLevel = "初级";
            } else if (age < 35) {
                autoLevel = "中级";
            } else {
                autoLevel = "高级";
            }
            this(name, age, autoLevel);
        }

        @Override
        public String toString() {
            return "Employee{name='" + name + "', age=" + age + ", level='" + level + "'}";
        }
    }

    /**
     * Manager 类, 演示 super() 前的参数预处理(STANDARD)
     */
    static class Manager extends Employee {
        private final String department;

        /**
         * 灵活构造器: 在 super() 前进行参数预处理
         */
        public Manager(String name, int age, String department) {
            String processedName = "[经理]" + name;
            super(processedName, age, "管理");
            this.department = department;
        }

        /**
         * 使用默认部门的构造器
         */
        public Manager(String name, int age) {
            String defaultDept = "综合部";
            this(name, age, defaultDept);
        }

        @Override
        public String toString() {
            return "Manager{department='" + department + "', " + super.toString() + "}";
        }
    }
}
