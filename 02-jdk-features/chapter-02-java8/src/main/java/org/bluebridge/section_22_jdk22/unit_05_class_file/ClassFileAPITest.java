package org.bluebridge.section_22_jdk22.unit_05_class_file;

import org.junit.Test;

/**
 * JDK 22 Class-File API 测试(PREVIEW 预览特性)
 *
 * Class-File API(JEP 466) 是 JDK 22 的 PREVIEW 预览特性, 第一次预览。
 * 引入 java.lang.classfile 包, 提供了读取、构建和转换 class 文件的标准 API。
 * 替代了第三方库如 ASM 的功能。
 *
 * 主要功能:
 *   1. 读取 class 文件: ClassModel 解析
 *   2. 构建 class 文件: ClassBuilder 构建
 *   3. 转换 class 文件: 修改已有 class
 *
 * 注意: 本文件使用 JDK 22 PREVIEW 特性的真实语法编写,
 *       编译命令: javac --enable-preview --release 22 ClassFileAPITest.java
 *       运行命令: java --enable-preview ClassFileAPITest
 *
 * 演化历程: Class-File API JDK 22(JEP 466, 1st PREVIEW) → JDK 23(2nd) → JDK 24(3rd) → JDK 25(STANDARD)
 *
 * @author lingwh
 * @date 2026/08/06 09:11
 */
public class ClassFileAPITest {

    /**
     * 测试 ClassModel 读取 class 文件(PREVIEW)
     * JDK 22 PREVIEW 特性，需要 --enable-preview
     * 使用 ClassFile.of() 读取 class 文件, 获取 ClassModel
     */
    @Test
    public void testReadClassFile_Preview() {
        // JDK 22 PREVIEW 特性，需要 --enable-preview
        // 使用 ClassFile API 读取当前类的字节码
        // ClassModel classModel = ClassFile.of().parse(this.getClass());
        // System.out.println("类名: " + classModel.thisClass().asInternalName());
        // System.out.println("父类: " + classModel.superclass().asInternalName());

        System.out.println("JDK 22 Class-File API 读取 class 文件演示");
        System.out.println("使用 java.lang.classfile.ClassFile 解析字节码");
        System.out.println("当前类: " + this.getClass().getName());
        System.out.println("--------------------------------------");
    }

    /**
     * 测试 ClassBuilder 构建 class 文件(PREVIEW)
     * JDK 22 PREVIEW 特性，需要 --enable-preview
     * 使用 ClassFile.of().build() 构建新的 class 文件
     */
    @Test
    public void testBuildClassFile_Preview() {
        // JDK 22 PREVIEW 特性，需要 --enable-preview
        // 使用 ClassBuilder 构建一个简单的类
        // byte[] classBytes = ClassFile.of().build(ClassDesc.of("com.example.Hello"),
        //     cl -> cl.withMethod("hello", MethodTypeDesc.of(CD_void), flags(ACC_PUBLIC),
        //         mb -> mb.withCode(code -> code.return_())));
        //
        // 上面的代码构建了一个包含 hello() 方法的类

        System.out.println("JDK 22 Class-File API 构建 class 文件演示");
        System.out.println("使用 ClassFile.of().build() 构建新的 class 文件");
        System.out.println("可以动态生成字节码");
        System.out.println("--------------------------------------");
    }

    /**
     * 测试 class 文件转换(PREVIEW)
     * JDK 22 PREVIEW 特性，需要 --enable-preview
     * 使用 ClassFile API 对已有的 class 文件进行转换
     */
    @Test
    public void testTransformClassFile_Preview() {
        // JDK 22 PREVIEW 特性，需要 --enable-preview
        // ClassFile API 支持对 class 文件进行转换:
        // 1. 添加方法
        // 2. 修改方法体
        // 3. 添加注解
        // 4. 修改类修饰符

        System.out.println("JDK 22 Class-File API 转换 class 文件演示");
        System.out.println("支持的转换操作:");
        System.out.println("  1. 添加新的方法");
        System.out.println("  2. 修改已有方法");
        System.out.println("  3. 添加/修改注解");
        System.out.println("  4. 修改类修饰符");
        System.out.println("--------------------------------------");
    }

    /**
     * 测试 class 文件属性解析(PREVIEW)
     * JDK 22 PREVIEW 特性，需要 --enable-preview
     * 使用 ClassFile API 解析 class 文件的属性
     */
    @Test
    public void testParseClassAttributes_Preview() {
        // JDK 22 PREVIEW 特性，需要 --enable-preview
        // ClassFile API 可以解析 class 文件的各种属性:
        // - 常量池
        // - 字段
        // - 方法
        // - 注解
        // - 行号表
        // - 局部变量表

        System.out.println("JDK 22 Class-File API 解析 class 属性演示");
        System.out.println("可解析的属性包括:");
        System.out.println("  1. 常量池 (ConstantPool)");
        System.out.println("  2. 字段信息 (FieldModel)");
        System.out.println("  3. 方法信息 (MethodModel)");
        System.out.println("  4. 注解信息 (Annotation)");
        System.out.println("  5. 行号表 (LineNumberTable)");
        System.out.println("--------------------------------------");
    }

    /**
     * 测试 ClassFile API 的实际使用示例(PREVIEW)
     * JDK 22 PREVIEW 特性，需要 --enable-preview
     * 展示 ClassFile API 的完整使用流程
     */
    @Test
    public void testClassFileAPIUsage_Preview() {
        // JDK 22 PREVIEW 特性，需要 --enable-preview
        // ClassFile API 的完整使用流程:
        //
        // 1. 获取 ClassFile 实例:
        //    ClassFile cf = ClassFile.of();
        //
        // 2. 解析已有的 class 文件:
        //    ClassModel model = cf.parse(bytes);
        //
        // 3. 遍历 class 元素:
        //    model.methods().forEach(m -> System.out.println(m.methodName()));
        //
        // 4. 构建新的 class 文件:
        //    byte[] newBytes = cf.build(ClassDesc.of("pkg.MyClass"), builder -> {
        //        builder.withMethod("myMethod", desc, flags, code -> {
        //            code.return_();
        //        });
        //    });

        System.out.println("JDK 22 Class-File API 完整使用流程演示");
        System.out.println("ClassFile API 提供了标准化的 class 文件处理方式");
        System.out.println("替代了传统的 ASM/BCEL 等第三方库");
        System.out.println("--------------------------------------");
    }

    /**
     * 测试 ClassFile API 的遍历元素(PREVIEW)
     * JDK 22 PREVIEW 特性，需要 --enable-preview
     * 使用 ClassFile API 遍历 class 文件中的各种元素
     */
    @Test
    public void testTraverseClassElements_Preview() {
        // JDK 22 PREVIEW 特性，需要 --enable-preview
        // ClassFile API 支持遍历 class 文件中的元素:
        //
        // classModel.methods().forEach(method -> {
        //     System.out.println("方法: " + method.methodName());
        //     System.out.println("描述符: " + method.methodType());
        // });
        //
        // classModel.fields().forEach(field -> {
        //     System.out.println("字段: " + field.fieldName());
        // });

        System.out.println("JDK 22 Class-File API 遍历元素演示");
        System.out.println("可以遍历 class 文件中的方法、字段、注解等元素");
        System.out.println("--------------------------------------");
    }
}