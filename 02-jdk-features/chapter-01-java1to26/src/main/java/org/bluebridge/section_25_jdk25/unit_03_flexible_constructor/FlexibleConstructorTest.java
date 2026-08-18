package org.bluebridge.section_25_jdk25.unit_03_flexible_constructor;

import org.junit.Test;

/**
 * JDK 25 灵活构造器体测试(STANDARD 正式特性)
 *
 * 灵活构造器体(Flexible Constructor Bodies, JEP 492) 在 JDK 25 中
 * 转正为 STANDARD 正式特性, 不再需要 --enable-preview。
 *
 * 在 JDK 25 之前, Java 要求在构造器中 super() 或 this() 调用必须是
 * 构造器中的第一个语句, 且 super()/this() 之前不能有任何其他语句。
 * 这导致了一些不方便的模式, 比如需要在调用 super() 之前验证参数、
 * 预处理参数或记录日志时, 必须使用静态方法或临时变量等变通方式。
 *
 * JDK 25 的灵活构造器体允许在 super() 或 this() 调用之前编写语句,
 * 但前提是这些语句不能引用正在构造的实例(即不能访问 this 的字段和方法)。
 * 这允许开发者在调用父类构造器之前进行参数验证、预处理或日志记录。
 *
 * 核心规则:
 *   1. super()/this() 之前的语句不能引用 this (字段、方法、内部类)
 *   2. 不能引用当前构造器中未初始化的实例字段
 *   3. 可以引用构造器参数、局部变量、静态方法/字段
 *   4. super()/this() 之后的语句可以正常引用 this
 *
 * 演化历程: 构造器前置语句 JDK 22(1st PREVIEW) → JDK 25(JEP 492, STANDARD)
 *
 * @author lingwh
 * @date 2026/08/06 09:11
 */
public class FlexibleConstructorTest {

    /**
     * 基础类: Person, 用于演示灵活构造器体
     */
    static class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
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
     * 子类: Employee, 在 super() 之前进行参数验证
     * JDK 25 允许在 super() 调用之前编写验证逻辑
     */
    static class Employee extends Person {
        private String employeeId;

        public Employee(String name, int age, String employeeId) {
            // JDK 25 灵活构造器体: 在 super() 之前进行参数验证
            if (name == null || name.isBlank()) {
                throw new IllegalArgumentException("姓名不能为空");
            }
            if (age < 0 || age > 150) {
                throw new IllegalArgumentException("年龄必须在 0-150 之间: " + age);
            }
            if (employeeId == null || employeeId.isBlank()) {
                throw new IllegalArgumentException("工号不能为空");
            }
            // super() 调用在验证之后
            super(name, age);
            this.employeeId = employeeId;
        }

        public String getEmployeeId() { return employeeId; }

        @Override
        public String toString() {
            return "Employee{name='" + getName() + "', age=" + getAge()
                    + ", employeeId='" + employeeId + "'}";
        }
    }

    /**
     * 子类: Student, 在 super() 之前进行参数预处理
     * JDK 25 允许在 super() 调用之前对参数进行预处理/转换
     */
    static class Student extends Person {
        private String studentId;

        public Student(String name, int age, String studentId) {
            // JDK 25 灵活构造器体: 在 super() 之前进行参数预处理
            String processedName = (name != null) ? name.trim() : "未知";
            int processedAge = Math.max(0, Math.min(age, 100));
            String processedStudentId = (studentId != null) ? studentId.toUpperCase() : "N/A";
            // super() 调用在预处理之后
            super(processedName, processedAge);
            this.studentId = processedStudentId;
        }

        public String getStudentId() { return studentId; }

        @Override
        public String toString() {
            return "Student{name='" + getName() + "', age=" + getAge()
                    + ", studentId='" + studentId + "'}";
        }
    }

    /**
     * 基础类: Shape, 包含一个需要验证的构造器参数
     */
    static class Shape {
        private String type;
        private double size;

        public Shape(String type, double size) {
            this.type = type;
            this.size = size;
        }

        public String getType() { return type; }
        public double getSize() { return size; }
    }

    /**
     * 子类: Circle, 在 super() 之前进行日志记录和验证
     * JDK 25 允许在 super() 调用之前记录日志
     */
    static class Circle extends Shape {
        public Circle(double radius) {
            // JDK 25 灵活构造器体: 在 super() 之前记录日志
            System.out.println("正在创建 Circle, radius = " + radius);
            // 参数验证
            if (radius <= 0) {
                throw new IllegalArgumentException("半径必须为正数: " + radius);
            }
            // super() 调用
            super("Circle", radius);
            // super() 之后可以进行实例相关操作(此处省略)
        }
    }

    /**
     * 测试灵活构造器体: 在 super() 之前进行参数验证(STANDARD)
     * 验证参数后调用 super(), 如果参数非法则抛出异常
     */
    @Test
    public void testParameterValidationBeforeSuper() {
        // 正常参数
        Employee emp = new Employee("张三", 30, "EMP001");
        System.out.println("创建成功: " + emp);
        System.out.println("--------------------------------------");

        // 非法参数(年龄超出范围) - 会抛出异常
        try {
            Employee invalid = new Employee("李四", 200, "EMP002");
            System.out.println("不应该执行到这里: " + invalid);
        } catch (IllegalArgumentException e) {
            System.out.println("参数验证生效: " + e.getMessage());
        }
        System.out.println("--------------------------------------");

        // 空姓名 - 会抛出异常
        try {
            Employee invalid = new Employee(null, 25, "EMP003");
            System.out.println("不应该执行到这里: " + invalid);
        } catch (IllegalArgumentException e) {
            System.out.println("参数验证生效: " + e.getMessage());
        }
    }

    /**
     * 测试灵活构造器体: 在 super() 之前进行参数预处理(STANDARD)
     * 对传入参数进行清洗、转换后, 再传递给父类构造器
     */
    @Test
    public void testParameterProcessingBeforeSuper() {
        // 需要预处理的参数(多余空格)
        Student s1 = new Student("  王五  ", 20, "stu001");
        System.out.println("姓名修剪后: " + s1);
        System.out.println("  学生证号已转大写: " + s1.getStudentId());
        System.out.println("--------------------------------------");

        // 超出范围的年龄被截断
        Student s2 = new Student("赵六", 150, "stu002");
        System.out.println("年龄截断后: " + s2);
        System.out.println("  实际年龄: " + s2.getAge());
        System.out.println("--------------------------------------");

        // null 参数被赋予默认值
        Student s3 = new Student(null, -5, null);
        System.out.println("null 参数处理: " + s3);
        System.out.println("  姓名默认值: " + s3.getName());
        System.out.println("  年龄默认值: " + s3.getAge());
        System.out.println("  学号默认值: " + s3.getStudentId());
    }

    /**
     * 测试灵活构造器体: 在 super() 之前进行日志记录(STANDARD)
     * 在调用父类构造器之前记录构造日志
     */
    @Test
    public void testLoggingBeforeSuper() {
        // 正常创建, 会看到构造过程中的日志输出
        Circle c = new Circle(5.0);
        System.out.println("Circle 创建成功: type=" + c.getType() + ", radius=" + c.getSize());
        System.out.println("--------------------------------------");

        // 非法参数, 在 super() 之前抛出异常, 不会执行到 super()
        try {
            Circle invalid = new Circle(-1.0);
            System.out.println("不应该执行到这里: " + invalid);
        } catch (IllegalArgumentException e) {
            System.out.println("参数验证生效: " + e.getMessage());
        }
    }

    /**
     * 测试灵活构造器体: super() 之后执行实例初始化(STANDARD)
     * super() 调用后可以正常访问 this 的字段和方法
     */
    @Test
    public void testInstanceInitAfterSuper() {
        Employee emp = new Employee("张三", 30, "EMP001");
        System.out.println("Employee 实例信息:");
        System.out.println("  姓名: " + emp.getName());
        System.out.println("  年龄: " + emp.getAge());
        System.out.println("  工号: " + emp.getEmployeeId());
        System.out.println("  toString: " + emp.toString());
    }

    /**
     * 测试灵活构造器体: 在 super() 之前调用静态方法(STANDARD)
     * 静态方法不依赖于实例, 可以在 super() 之前安全调用
     */
    @Test
    public void testStaticMethodBeforeSuper() {
        // 使用静态方法进行参数验证(模拟)
        String validatedName = validateName("李四");
        int validatedAge = validateAge(25);
        System.out.println("静态方法验证结果: name=" + validatedName + ", age=" + validatedAge);
        System.out.println("--------------------------------------");

        // 实际创建 Employee 时, 构造器内部在 super() 之前做了验证
        Employee emp = new Employee(validatedName, validatedAge, "EMP004");
        System.out.println("创建成功: " + emp);
    }

    /**
     * 静态验证方法: 验证姓名
     */
    private static String validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("姓名不能为空");
        }
        return name.trim();
    }

    /**
     * 静态验证方法: 验证年龄
     */
    private static int validateAge(int age) {
        if (age < 0 || age > 150) {
            throw new IllegalArgumentException("年龄无效: " + age);
        }
        return age;
    }

    /**
     * 测试灵活构造器体: 使用 this() 调用其他构造器(STANDARD)
     * this() 调用同样可以放在预处理语句之后
     */
    @Test
    public void testThisCallAfterProcessing() {
        // 创建 Student 时, 构造器内部先预处理参数, 再调用 super()
        Student s = new Student("  钱七  ", 22, "stu003");
        System.out.println("this() 调用示例: " + s);
        System.out.println("  姓名已修剪: '" + s.getName() + "'");
        System.out.println("  学号已转大写: " + s.getStudentId());
    }
}