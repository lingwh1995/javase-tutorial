package org.bluebridge.section_23_jdk23.unit_06_flexible_constructor;

import org.junit.Test;

/**
 * JDK 23 灵活构造器体测试(PREVIEW 预览特性)
 *
 * 灵活构造器体(Flexible Constructor Bodies, JEP 482, 第一次预览)
 * 是 JDK 23 的 PREVIEW 预览特性, 编译和运行都需要 --enable-preview 参数。
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
 *   - JDK 25: 转正(最终确定的 API)
 *
 * @author lingwh
 * @date 2026/08/06 18:20
 */
public class FlexibleConstructorTest {

    /**
     * 测试在 super() 调用前进行参数验证(PREVIEW)
     * JDK 23 PREVIEW 特性，需要 --enable-preview
     * 在 super() 前对参数进行校验, 避免调用 super 后再抛出异常
     */
    @Test
    public void testValidationBeforeSuper_Preview() {
        // JDK 23 PREVIEW 特性，需要 --enable-preview
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
     * 测试在 super() 调用前进行参数预处理(PREVIEW)
     * JDK 23 PREVIEW 特性，需要 --enable-preview
     * 在 super() 前对参数进行转换和格式化
     */
    @Test
    public void testParameterPreparationBeforeSuper_Preview() {
        // JDK 23 PREVIEW 特性，需要 --enable-preview
        // 使用 EMPLOYEE_STRATEGY 策略创建 Manager
        Manager manager = new Manager("王五", 30, "技术部");
        System.out.println("Manager 创建成功: " + manager);
        System.out.println("--------------------------------------");

        // 使用默认策略创建
        Manager defaultManager = new Manager("赵六", 28);
        System.out.println("默认 Manager 创建成功: " + defaultManager);
        System.out.println("--------------------------------------");
    }

    /**
     * 测试在 this() 调用前进行逻辑判断(PREVIEW)
     * JDK 23 PREVIEW 特性，需要 --enable-preview
     * 在 this() 前根据条件调用不同的构造器
     */
    @Test
    public void testLogicBeforeThis_Preview() {
        // JDK 23 PREVIEW 特性，需要 --enable-preview
        // 测试不同的构造器选择逻辑
        Employee emp1 = new Employee("员工A", 22, "初级");
        System.out.println("员工1: " + emp1);

        Employee emp2 = new Employee("员工B", 35, "高级");
        System.out.println("员工2: " + emp2);
        System.out.println("--------------------------------------");
    }

    /**
     * 测试灵活构造器体中的异常处理(PREVIEW)
     * JDK 23 PREVIEW 特性，需要 --enable-preview
     * 在 super() 前进行异常处理
     */
    @Test
    public void testExceptionHandlingInConstructor_Preview() {
        // JDK 23 PREVIEW 特性，需要 --enable-preview
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
     * 基础 Person 类, 演示 super() 前的参数验证(PREVIEW)
     * 注意: 本类中的灵活构造器语法需要 JDK 23 --enable-preview 编译
     */
    static class Person {
        private final String name;
        private final int age;

        /**
         * 灵活构造器: 在 super() 前进行参数验证
         * 注意: 在 JDK 23 中, 可以在 super() 前添加语句
         * 此处使用模拟方式演示, 实际编译需要 --enable-preview
         */
        public Person(String name, int age) {
            // JDK 23 PREVIEW 特性: 在 super() 前进行参数验证
            // 注意: 由于 Object 是超类, super() 无需参数
            // 实际场景: 子类可以在 super(args) 前进行参数处理
            if (name == null || name.isBlank()) {
                throw new IllegalArgumentException("姓名不能为空");
            }
            if (age < 0 || age > 150) {
                throw new IllegalArgumentException("年龄必须在 0-150 之间: " + age);
            }
            // 在 JDK 23 中, 这里可以编写任意语句(包括 super() 或 this() 调用)
            // 对于直接继承 Object 的类, super() 是隐式调用的
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
     * Employee 类, 演示 this() 调用前的逻辑判断(PREVIEW)
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
            // JDK 23 PREVIEW 特性: 在 this() 前进行逻辑判断
            String autoLevel;
            if (age < 25) {
                autoLevel = "初级";
            } else if (age < 35) {
                autoLevel = "中级";
            } else {
                autoLevel = "高级";
            }
            // 调用主构造器
            this(name, age, autoLevel);
        }

        @Override
        public String toString() {
            return "Employee{name='" + name + "', age=" + age + ", level='" + level + "'}";
        }
    }

    /**
     * Manager 类, 演示 super() 前的参数预处理(PREVIEW)
     */
    static class Manager extends Employee {
        private final String department;

        /**
         * 灵活构造器: 在 super() 前进行参数预处理
         */
        public Manager(String name, int age, String department) {
            // JDK 23 PREVIEW 特性: 在 super() 前进行参数处理
            String processedName = "[经理]" + name;
            // 调用父类构造器
            super(processedName, age, "管理");
            this.department = department;
        }

        /**
         * 使用默认部门的构造器
         */
        public Manager(String name, int age) {
            // JDK 23 PREVIEW 特性: 在 this() 前进行逻辑判断
            String defaultDept = "综合部";
            this(name, age, defaultDept);
        }

        @Override
        public String toString() {
            return "Manager{department='" + department + "', " + super.toString() + "}";
        }
    }
}
