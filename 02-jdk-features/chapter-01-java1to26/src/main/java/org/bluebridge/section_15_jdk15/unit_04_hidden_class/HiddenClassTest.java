package org.bluebridge.section_15_jdk15.unit_04_hidden_class;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.reflect.Method;

/**
 * JDK 15 隐藏类（STANDARD 特性，JEP 371）
 * 隐藏类是一种不能被其他类直接通过字节码名使用或发现的类
 * 隐藏类主要用于框架运行时生成类，如 Lambda 表达式、动态代理等
 * 隐藏类在加载完成后无法通过反射的 Class.forName() 查找，且可以指定卸载策略
 *
 * 演化历程: 隐藏类 JDK 15 STANDARD（JEP 371）
 *
 * @author lingwh
 * @date 2026/08/06 02:19
 */
public class HiddenClassTest {

    /**
     * 测试使用 MethodHandles.Lookup.defineHiddenClass() 定义隐藏类
     * 定义一个隐藏类，验证其基本属性
     */
    @Test
    public void testDefineHiddenClass() throws Exception {
        // 隐藏类通常用于框架内部，此处演示通过 Lookup 定义隐藏类的能力
        Lookup lookup = MethodHandles.lookup();

        // 加载 Helper 类的字节码作为隐藏类的定义
        byte[] classBytes = loadClassBytes("HiddenClassHelper");
        if (classBytes == null) {
            System.out.println("无法加载类字节码，请先编译 HiddenClassHelper");
            System.out.println("--- 分割线 ---");
            return;
        }

        // 使用 defineHiddenClass 定义隐藏类
        // 参数：字节码、是否初始化、允许的查找模式
        Class<?> hiddenClass = lookup.defineHiddenClass(classBytes, true, Lookup.PRIVATE).lookupClass();

        // 验证隐藏类的基本信息
        System.out.println("隐藏类名称: " + hiddenClass.getName());
        System.out.println("隐藏类是否为隐藏类: " + hiddenClass.isHidden());
        System.out.println("隐藏类 CanonicalName: " + hiddenClass.getCanonicalName());
        System.out.println("隐藏类所在包: " + hiddenClass.getPackage());
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试隐藏类的不可发现特性
     * 隐藏类不能通过 Class.forName() 发现
     */
    @Test
    public void testHiddenClassNotFound() throws Exception {
        Lookup lookup = MethodHandles.lookup();
        byte[] classBytes = loadClassBytes("HiddenClassHelper");
        if (classBytes == null) {
            System.out.println("无法加载类字节码，请先编译 HiddenClassHelper");
            System.out.println("--- 分割线 ---");
            return;
        }

        Class<?> hiddenClass = lookup.defineHiddenClass(classBytes, true, Lookup.PRIVATE).lookupClass();

        String hiddenClassName = hiddenClass.getName();
        System.out.println("隐藏类名称: " + hiddenClassName);
        System.out.println("是否为隐藏类: " + hiddenClass.isHidden());

        // 尝试通过 Class.forName() 查找隐藏类（应抛出 ClassNotFoundException）
        try {
            Class.forName(hiddenClassName);
            System.out.println("Class.forName 查找结果: 找到 (异常)");
        } catch (ClassNotFoundException e) {
            System.out.println("Class.forName 查找结果: 未找到 (预期) - " + e.getMessage());
        }
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试隐藏类实例化并调用方法
     * 隐藏类可以正常实例化并调用其方法
     */
    @Test
    public void testHiddenClassInstantiateAndInvoke() throws Exception {
        Lookup lookup = MethodHandles.lookup();
        byte[] classBytes = loadClassBytes("HiddenClassHelper");
        if (classBytes == null) {
            System.out.println("无法加载类字节码，请先编译 HiddenClassHelper");
            System.out.println("--- 分割线 ---");
            return;
        }

        Class<?> hiddenClass = lookup.defineHiddenClass(classBytes, true, Lookup.PRIVATE).lookupClass();

        // 实例化隐藏类
        Object instance = hiddenClass.getDeclaredConstructor().newInstance();

        // 调用隐藏类的方法
        Method sayHelloMethod = hiddenClass.getMethod("sayHello");
        String result = (String) sayHelloMethod.invoke(instance);

        System.out.println("隐藏类实例调用结果: " + result);
        System.out.println("隐藏类类加载器: " + hiddenClass.getClassLoader());
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试隐藏类的匿名特性
     * 隐藏类没有直接的字节码名称，CanonicalName 为 null
     */
    @Test
    public void testHiddenClassAnonymous() throws Exception {
        Lookup lookup = MethodHandles.lookup();
        byte[] classBytes = loadClassBytes("HiddenClassHelper");
        if (classBytes == null) {
            System.out.println("无法加载类字节码，请先编译 HiddenClassHelper");
            System.out.println("--- 分割线 ---");
            return;
        }

        Class<?> hiddenClass = lookup.defineHiddenClass(classBytes, true, Lookup.PRIVATE).lookupClass();

        // 隐藏类的 CanonicalName 为 null
        System.out.println("隐藏类名称: " + hiddenClass.getName());
        System.out.println("隐藏类简单名称: " + hiddenClass.getSimpleName());
        System.out.println("隐藏类 CanonicalName: " + hiddenClass.getCanonicalName());
        System.out.println("隐藏类 TypeName: " + hiddenClass.getTypeName());
        System.out.println("隐藏类是否为匿名类: " + hiddenClass.isAnonymousClass());
        System.out.println("隐藏类是否为合成类: " + hiddenClass.isSyntheticClass());
        System.out.println("--- 分割线 ---");
    }

    /**
     * 从 classpath 中加载指定类的字节码
     * 使用类加载器读取 .class 文件
     *
     * @param className 类名（简单名称，不含包路径）
     * @return 类字节码数组，如果加载失败返回 null
     */
    private byte[] loadClassBytes(String className) {
        try {
            // 构建 .class 文件路径：当前类所在的包路径下
            String resourceName = HiddenClassTest.class.getSimpleName() + "$" + className + ".class";
            InputStream is = HiddenClassTest.class.getResourceAsStream(resourceName);
            if (is == null) {
                return null;
            }
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
            return baos.toByteArray();
        } catch (Exception e) {
            System.out.println("加载字节码异常: " + e.getMessage());
            return null;
        }
    }

    /**
     * 辅助类，用于演示隐藏类的基本概念
     * 实际隐藏类一般由框架动态生成字节码
     * 此类作为模板，其字节码会被加载为隐藏类
     */
    static class HiddenClassHelper {
        public String sayHello() {
            return "Hello from HiddenClass!";
        }

        @Override
        public String toString() {
            return "HiddenClassHelper instance";
        }
    }

    /**
     * 测试隐藏类与普通类的区别对比
     */
    @Test
    public void testHiddenClassVsNormalClass() throws Exception {
        // 普通类信息
        Class<HiddenClassHelper> normalClass = HiddenClassHelper.class;
        System.out.println("普通类信息:");
        System.out.println("  名称: " + normalClass.getName());
        System.out.println("  是否为隐藏类: " + normalClass.isHidden());
        System.out.println("  CanonicalName: " + normalClass.getCanonicalName());
        System.out.println("");

        // 创建隐藏类并对比
        Lookup lookup = MethodHandles.lookup();
        byte[] classBytes = loadClassBytes("HiddenClassHelper");
        if (classBytes == null) {
            System.out.println("无法加载类字节码，请先编译 HiddenClassHelper");
            System.out.println("--- 分割线 ---");
            return;
        }

        Class<?> hiddenClass = lookup.defineHiddenClass(classBytes, true, Lookup.PRIVATE).lookupClass();

        System.out.println("隐藏类信息:");
        System.out.println("  名称: " + hiddenClass.getName());
        System.out.println("  是否为隐藏类: " + hiddenClass.isHidden());
        System.out.println("  CanonicalName: " + hiddenClass.getCanonicalName());

        // 对比两者的类加载器
        System.out.println("  普通类类加载器: " + normalClass.getClassLoader());
        System.out.println("  隐藏类类加载器: " + hiddenClass.getClassLoader());
        System.out.println("--- 分割线 ---");
    }
}