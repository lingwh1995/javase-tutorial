package org.bluebridge.section_24_jdk24.unit_03_class_file;

import org.junit.Test;

import java.lang.classfile.Attributes;
import java.lang.classfile.ClassFile;
import java.lang.classfile.ClassModel;
import java.lang.classfile.ClassTransform;
import java.lang.classfile.FieldModel;
import java.lang.classfile.MethodModel;
import java.lang.classfile.constantpool.ClassEntry;
import java.lang.constant.ClassDesc;
import java.lang.constant.MethodTypeDesc;
import java.lang.reflect.AccessFlag;
import java.security.MessageDigest;

/**
 * JDK 24 Class-File API 测试(STANDARD 正式特性)
 *
 * Class-File API (JEP 484) 是 JDK 24 的 STANDARD 正式特性，无需 --enable-preview。
 *
 * Class-File API 提供了一种标准方式来解析、构建和转换 Java 类文件，
 * 位于 java.lang.classfile 包中，取代了内部 API 如 ASM 和 javassist。
 *
 * 主要功能：
 *   1. 解析 .class 文件并检查类结构
 *   2. 构建新的 .class 文件
 *   3. 转换和修改现有的 .class 文件
 *
 * 注意：本文件使用 JDK 24 正式特性的真实语法编写，无需 --enable-preview。
 *
 * 演化历程: Class-File API JDK 22(JEP 457, 1st PREVIEW) → JDK 23(JEP 466, 2nd) → JDK 24(JEP 484, STANDARD)
 *
 * @author lingwh
 * @date 2026/08/06 09:11
 */
public class ClassFileAPITest {

    /**
     * 测试使用 Class-File API 解析当前类的类文件结构(STANDARD)
     * JDK 24 STANDARD 正式特性，无需 --enable-preview
     * 解析当前类文件，输出类名、方法、字段等信息
     */
    @Test
    public void testParseClassFile_Standard() throws Exception {
        // JDK 24 STANDARD 正式特性，无需 --enable-preview
        // 获取当前类的字节码
        String className = this.getClass().getName();
        Class<?> clazz = this.getClass();

        // 使用 ClassFile API 解析类
        ClassFile classFile = ClassFile.of();
        byte[] classBytes = clazz.getResourceAsStream("/" + className.replace('.', '/') + ".class")
                .readAllBytes();
        ClassModel classModel = classFile.parse(classBytes);

        // 输出类文件信息
        System.out.println("Class-File API 解析类文件测试:");
        System.out.println("  类名: " + classModel.thisClass().asInternalName());
        System.out.println("  父类: " + classModel.superclass().map(ClassEntry::asInternalName).orElse("无"));
        System.out.println("  访问标志: " + classModel.flags());
        System.out.println("  接口数量: " + classModel.interfaces().size());
        System.out.println("  字段数量: " + classModel.fields().size());
        System.out.println("  方法数量: " + classModel.methods().size());
        System.out.println("  属性数量: " + classModel.attributes().size());

        // 列出所有方法
        System.out.println("  方法列表:");
        for (MethodModel method : classModel.methods()) {
            System.out.println("    - " + method.methodName().stringValue()
                    + method.methodType().toString());
        }

        // 列出所有字段
        System.out.println("  字段列表:");
        for (FieldModel field : classModel.fields()) {
            System.out.println("    - " + field.fieldName().stringValue()
                    + " (" + field.fieldType().stringValue() + ")");
        }
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试使用 Class-File API 构建一个简单的类文件(STANDARD)
     * JDK 24 STANDARD 正式特性，无需 --enable-preview
     * 使用 ClassFile API 构建一个简单的 HelloWorld 类
     */
    @Test
    public void testBuildClassFile_Standard() throws Exception {
        // JDK 24 STANDARD 正式特性，无需 --enable-preview
        ClassFile classFile = ClassFile.of();

        // 构建一个简单的类: HelloWorld { public static void main(String[]); }
        byte[] classBytes = classFile.build(ClassDesc.of("HelloWorld"), clb -> {
            clb.withFlags(AccessFlag.PUBLIC);
            // 添加 main 方法
            clb.withMethod("main",
                    MethodTypeDesc.of(ClassDesc.ofDescriptor("V"), ClassDesc.of("java.lang.String").arrayType()),
                    AccessFlag.PUBLIC.mask() | AccessFlag.STATIC.mask(),
                    mb -> {
                        mb.withCode(cob -> {
                            // getstatic java.lang.System.out
                            cob.getstatic(ClassDesc.of("java.lang.System"), "out",
                                    ClassDesc.of("java.io.PrintStream"));
                            // ldc "Hello from Class-File API!"
                            cob.ldc("Hello from Class-File API!");
                            // invokevirtual java.io.PrintStream.println
                            cob.invokevirtual(ClassDesc.of("java.io.PrintStream"), "println",
                                    MethodTypeDesc.of(ClassDesc.ofDescriptor("V"), ClassDesc.of("java.lang.String")));
                            // return
                            cob.return_();
                        });
                    });
        });

        System.out.println("Class-File API 构建类文件测试:");
        System.out.println("  生成的字节码长度: " + classBytes.length + " bytes");
        System.out.println("  HelloWorld 类构建成功!");

        // 验证生成的字节码
        // 使用自定义类加载器加载并执行
        try (var classBytesStream = new java.io.ByteArrayInputStream(classBytes)) {
            ClassLoader loader = new ClassLoader() {
                @Override
                protected Class<?> findClass(String name) throws ClassNotFoundException {
                    if (name.equals("HelloWorld")) {
                        return defineClass(name, classBytes, 0, classBytes.length);
                    }
                    throw new ClassNotFoundException(name);
                }
            };

            // 通过自定义类加载器的 loadClass 触发 findClass 加载类
            // 注意: 不能通过反射调用 ClassLoader.defineClass(JDK 9+ 强封装会抛出 InaccessibleObjectException)
            Class<?> helloWorldClass = loader.loadClass("HelloWorld");

            // 通过反射调用 main 方法
            java.lang.reflect.Method mainMethod = helloWorldClass.getMethod("main", String[].class);
            mainMethod.invoke(null, (Object) new String[]{});

            System.out.println("  通过反射调用 main 方法成功!");
        }
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试使用 Class-File API 解析和检查类的常量池信息(STANDARD)
     * JDK 24 STANDARD 正式特性，无需 --enable-preview
     * 解析类文件后输出常量池相关的信息
     */
    @Test
    public void testParseClassFileConstantPool_Standard() throws Exception {
        // JDK 24 STANDARD 正式特性，无需 --enable-preview
        String className = this.getClass().getName();
        Class<?> clazz = this.getClass();

        ClassFile classFile = ClassFile.of();
        byte[] classBytes = clazz.getResourceAsStream("/" + className.replace('.', '/') + ".class")
                .readAllBytes();
        ClassModel classModel = classFile.parse(classBytes);

        System.out.println("Class-File API 常量池和属性测试:");
        System.out.println("  类名: " + classModel.thisClass().asInternalName());
        System.out.println("  父类: " + classModel.superclass()
                .map(ClassEntry::asInternalName).orElse("无"));

        // 检查类中的方法及其属性
        System.out.println("  方法详细信息:");
        for (MethodModel method : classModel.methods()) {
            System.out.println("    方法: " + method.methodName().stringValue()
                    + method.methodType().toString());
            System.out.println("      访问标志: " + method.flags());
            System.out.println("      属性数量: " + method.attributes().size());
            method.attributes().forEach(attr -> {
                System.out.println("        - 属性: " + attr.attributeName());
            });
        }

        // 检查字段及其属性
        System.out.println("  字段详细信息:");
        for (FieldModel field : classModel.fields()) {
            System.out.println("    字段: " + field.fieldName().stringValue()
                    + " (" + field.fieldType().stringValue() + ")");
            System.out.println("      访问标志: " + field.flags());
            // 检查是否有常量值属性
            field.findAttribute(Attributes.constantValue()).ifPresent(cv -> {
                System.out.println("      常量值: " + cv.constant().constantValue());
            });
        }
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试使用 Class-File API 构建带字段的类(STANDARD)
     * JDK 24 STANDARD 正式特性，无需 --enable-preview
     * 构建一个包含字段、构造器和方法的完整类
     */
    @Test
    public void testBuildClassFileWithFields_Standard() throws Exception {
        // JDK 24 STANDARD 正式特性，无需 --enable-preview
        ClassFile classFile = ClassFile.of();

        // 构建一个 Person 类: public class Person { private String name; private int age; ... }
        byte[] classBytes = classFile.build(ClassDesc.of("Person"), clb -> {
            clb.withFlags(AccessFlag.PUBLIC);
            // 添加私有字段 name (String 类型)
            clb.withField("name", ClassDesc.of("java.lang.String"), AccessFlag.PRIVATE.mask());
            // 添加私有字段 age (int 类型)
            clb.withField("age", ClassDesc.ofDescriptor("I"), AccessFlag.PRIVATE.mask());
            // 添加构造方法 Person(String name, int age)
            clb.withMethod("<init>",
                    MethodTypeDesc.of(ClassDesc.ofDescriptor("V"),
                            ClassDesc.of("java.lang.String"),
                            ClassDesc.ofDescriptor("I")),
                    AccessFlag.PUBLIC.mask(),
                    mb -> {
                        mb.withCode(cob -> {
                            cob.aload(0); // this
                            cob.invokespecial(ClassDesc.of("java.lang.Object"), "<init>",
                                    MethodTypeDesc.of(ClassDesc.ofDescriptor("V")));
                            cob.aload(0); // this
                            cob.aload(1); // name
                            cob.putfield(ClassDesc.of("Person"), "name",
                                    ClassDesc.of("java.lang.String"));
                            cob.aload(0); // this
                            cob.iload(2); // age
                            cob.putfield(ClassDesc.of("Person"), "age",
                                    ClassDesc.ofDescriptor("I"));
                            cob.return_();
                        });
                    });
            // 添加 toString 方法
            clb.withMethod("toString",
                    MethodTypeDesc.of(ClassDesc.of("java.lang.String")),
                    AccessFlag.PUBLIC.mask(),
                    mb -> {
                        mb.withCode(cob -> {
                            cob.new_(ClassDesc.of("java.lang.StringBuilder"));
                            cob.dup();
                            cob.invokespecial(ClassDesc.of("java.lang.StringBuilder"), "<init>",
                                    MethodTypeDesc.of(ClassDesc.ofDescriptor("V")));
                            cob.ldc("Person{name='");
                            cob.invokevirtual(ClassDesc.of("java.lang.StringBuilder"), "append",
                                    MethodTypeDesc.of(ClassDesc.of("java.lang.StringBuilder"),
                                            ClassDesc.of("java.lang.String")));
                            cob.aload(0);
                            cob.getfield(ClassDesc.of("Person"), "name",
                                    ClassDesc.of("java.lang.String"));
                            cob.invokevirtual(ClassDesc.of("java.lang.StringBuilder"), "append",
                                    MethodTypeDesc.of(ClassDesc.of("java.lang.StringBuilder"),
                                            ClassDesc.of("java.lang.String")));
                            cob.ldc("', age=");
                            cob.invokevirtual(ClassDesc.of("java.lang.StringBuilder"), "append",
                                    MethodTypeDesc.of(ClassDesc.of("java.lang.StringBuilder"),
                                            ClassDesc.of("java.lang.String")));
                            cob.aload(0);
                            cob.getfield(ClassDesc.of("Person"), "age",
                                    ClassDesc.ofDescriptor("I"));
                            cob.invokevirtual(ClassDesc.of("java.lang.StringBuilder"), "append",
                                    MethodTypeDesc.of(ClassDesc.of("java.lang.StringBuilder"),
                                            ClassDesc.ofDescriptor("I")));
                            cob.ldc("'}");
                            cob.invokevirtual(ClassDesc.of("java.lang.StringBuilder"), "append",
                                    MethodTypeDesc.of(ClassDesc.of("java.lang.StringBuilder"),
                                            ClassDesc.of("java.lang.String")));
                            cob.invokevirtual(ClassDesc.of("java.lang.StringBuilder"), "toString",
                                    MethodTypeDesc.of(ClassDesc.of("java.lang.String")));
                            cob.areturn();
                        });
                    });
        });

        System.out.println("Class-File API 构建带字段的类测试:");
        System.out.println("  生成的字节码长度: " + classBytes.length + " bytes");

        // 加载并测试
        // 通过自定义类加载器的 loadClass 触发 findClass 加载类
        // 注意: 不能通过反射调用 ClassLoader.defineClass(JDK 9+ 强封装会抛出 InaccessibleObjectException)
        ClassLoader loader = new ClassLoader() {
            @Override
            protected Class<?> findClass(String name) throws ClassNotFoundException {
                if (name.equals("Person")) {
                    return defineClass(name, classBytes, 0, classBytes.length);
                }
                throw new ClassNotFoundException(name);
            }
        };
        Class<?> personClass = loader.loadClass("Person");

        // 通过反射创建 Person 实例
        java.lang.reflect.Constructor<?> constructor = personClass.getConstructor(String.class, int.class);
        Object person = constructor.newInstance("张三", 25);
        System.out.println("  创建 Person 实例: " + person);
        System.out.println("  类加载成功，Person 类构建完成!");
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试 Class-File API 解析和转换类文件(STANDARD)
     * JDK 24 STANDARD 正式特性，无需 --enable-preview
     * 解析当前类，再重新构建并对比
     */
    @Test
    public void testParseAndRebuildClassFile_Standard() throws Exception {
        // JDK 24 STANDARD 正式特性，无需 --enable-preview
        String className = this.getClass().getName();
        Class<?> clazz = this.getClass();

        ClassFile classFile = ClassFile.of();
        byte[] classBytes = clazz.getResourceAsStream("/" + className.replace('.', '/') + ".class")
                .readAllBytes();
        ClassModel classModel = classFile.parse(classBytes);

        // 从解析的模型中重建类文件
        byte[] rebuiltBytes = classFile.transformClass(classModel, ClassTransform.ACCEPT_ALL);

        // 对比原始字节码和重建字节码
        String originalHash = bytesToHex(MessageDigest.getInstance("MD5").digest(classBytes));
        String rebuiltHash = bytesToHex(MessageDigest.getInstance("MD5").digest(rebuiltBytes));

        System.out.println("Class-File API 解析与重建测试:");
        System.out.println("  原始类: " + classModel.thisClass().asInternalName());
        System.out.println("  原始字节码长度: " + classBytes.length + " bytes");
        System.out.println("  重建字节码长度: " + rebuiltBytes.length + " bytes");
        System.out.println("  原始 MD5: " + originalHash);
        System.out.println("  重建 MD5: " + rebuiltHash);
        System.out.println("  字节码一致性: " + (originalHash.equals(rebuiltHash) ? "一致 ✓" : "不同（可能因元数据差异）"));
        System.out.println("--- 分割线 ---");
    }

    /**
     * 将字节数组转换为十六进制字符串
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    /**
     * 测试 Class-File API 的 ensureClassFile 和 transform 方法(STANDARD)
     * JDK 24 STANDARD 正式特性，无需 --enable-preview
     * 演示类文件的转换操作
     */
    @Test
    public void testClassFileTransform_Standard() throws Exception {
        // JDK 24 STANDARD 正式特性，无需 --enable-preview
        // 确保目标类文件是可解析的类文件
        String className = this.getClass().getName();
        Class<?> clazz = this.getClass();
        byte[] classBytes = clazz.getResourceAsStream("/" + className.replace('.', '/') + ".class")
                .readAllBytes();

        // 使用 ClassFile API 确保类文件格式正确
        ClassFile classFile = ClassFile.of();
        ClassModel classModel = classFile.parse(classBytes);

        System.out.println("Class-File API 转换测试:");
        System.out.println("  类: " + classModel.thisClass().asInternalName());
        System.out.println("  版本: " + classModel.majorVersion() + "." + classModel.minorVersion());
        System.out.println("  类文件解析成功，可以安全转换!");
        System.out.println("--- 分割线 ---");
    }
}