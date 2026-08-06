package org.bluebridge.section_01_jdk1.unit_02_reflection;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * JDK 1.1 反射(Reflection)机制测试
 *
 * JDK 1.1 引入 java.lang.reflect 包, 提供反射机制, 允许在运行时动态地:
 * 1. 获取 Class 对象: 类名.class、对象.getClass()、Class.forName() 三种方式
 * 2. 获取构造方法: 通过 Class 对象的 getConstructor/getDeclaredConstructor 获取并创建实例
 * 3. 获取字段: 通过 getField/getDeclaredField 获取字段并读写字段值
 * 4. 获取方法: 通过 getMethod/getDeclaredMethod 获取方法并调用
 * 反射是 Spring、MyBatis 等主流框架的底层基石
 *
 * @author lingwh
 * @date 2026/08/05 18:25
 */
public class ReflectionTest {

    /**
     * 用户类: 演示反射机制, 包含私有字段、多个构造方法、私有方法
     */
    static class User {

        // 私有字段 name
        private String name;

        // 私有字段 age
        private int age;

        public User() {
        }

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        /**
         * 私有方法: 演示反射调用私有方法
         */
        private String privateMethod(String prefix) {
            return prefix + ": " + name + ", " + age;
        }

        @Override
        public String toString() {
            return "User{name='" + name + "', age=" + age + "}";
        }
    }

    /**
     * 测试获取 Class 对象的三种方式
     */
    @Test
    public void testGetClassObject() throws ClassNotFoundException {
        // 方式一: 通过 类名.class 获取
        Class<User> clazz1 = User.class;
        System.out.println("方式一 类名.class: " + clazz1.getName());
        // 方式二: 通过 对象.getClass() 获取
        User user = new User();
        Class<? extends User> clazz2 = user.getClass();
        System.out.println("方式二 对象.getClass(): " + clazz2.getName());
        // 方式三: 通过 Class.forName() 加载类并获取(最灵活, 只需要类的全限定名)
        Class<?> clazz3 = Class.forName("org.bluebridge.section_01_jdk1.unit_02_reflection.ReflectionTest$User");
        System.out.println("方式三 Class.forName(): " + clazz3.getName());
        // 同一个类在 JVM 中只有一份 Class 对象, 三种方式获取的是同一个对象
        System.out.println("三种方式获取的 Class 对象是否相同: " + (clazz1 == clazz2 && clazz2 == clazz3));
    }

    /**
     * 测试通过反射获取构造方法并创建对象
     */
    @Test
    public void testGetConstructorAndCreateInstance() throws Exception {
        Class<?> clazz = Class.forName("org.bluebridge.section_01_jdk1.unit_02_reflection.ReflectionTest$User");
        // 获取无参构造方法并创建对象
        Constructor<?> noArgConstructor = clazz.getConstructor();
        Object user1 = noArgConstructor.newInstance();
        System.out.println("通过无参构造方法创建对象: " + user1);
        // 获取带参构造方法并创建对象
        Constructor<?> constructor = clazz.getConstructor(String.class, int.class);
        Object user2 = constructor.newInstance("张三", 18);
        System.out.println("通过带参构造方法创建对象: " + user2);
    }

    /**
     * 测试通过反射获取字段并读写字段值
     */
    @Test
    public void testGetFieldAndModify() throws Exception {
        Class<?> clazz = Class.forName("org.bluebridge.section_01_jdk1.unit_02_reflection.ReflectionTest$User");
        Object user = clazz.getConstructor().newInstance();
        // 获取私有字段 name 并设置值(私有字段需要先 setAccessible(true))
        Field nameField = clazz.getDeclaredField("name");
        nameField.setAccessible(true);
        nameField.set(user, "李四");
        // 获取私有字段 age 并设置值
        Field ageField = clazz.getDeclaredField("age");
        ageField.setAccessible(true);
        ageField.setInt(user, 30);
        System.out.println("通过反射读写字段后的对象: " + user);
    }

    /**
     * 测试通过反射获取方法并调用
     */
    @Test
    public void testGetMethodAndInvoke() throws Exception {
        Class<?> clazz = Class.forName("org.bluebridge.section_01_jdk1.unit_02_reflection.ReflectionTest$User");
        Object user = clazz.getConstructor().newInstance();
        // 获取 setName(String) 方法并调用
        Method setNameMethod = clazz.getMethod("setName", String.class);
        setNameMethod.invoke(user, "王五");
        // 获取 getName() 方法并调用
        Method getNameMethod = clazz.getMethod("getName");
        Object name = getNameMethod.invoke(user);
        System.out.println("通过反射调用 setName/getName 的结果: " + name);
    }

    /**
     * 测试通过反射调用私有方法
     */
    @Test
    public void testGetPrivateMethodAndInvoke() throws Exception {
        Class<?> clazz = Class.forName("org.bluebridge.section_01_jdk1.unit_02_reflection.ReflectionTest$User");
        Object user = clazz.getConstructor(String.class, int.class).newInstance("张三", 18);
        // 私有方法只能通过 getDeclaredMethod 获取, 且需要 setAccessible(true)
        Method privateMethod = clazz.getDeclaredMethod("privateMethod", String.class);
        privateMethod.setAccessible(true);
        Object result = privateMethod.invoke(user, "前缀");
        System.out.println("通过反射调用私有方法的结果: " + result);
    }
}
