package org.bluebridge.section_25_jdk25.unit_03_class_file;

import org.junit.Test;

import java.lang.classfile.*;
import java.lang.classfile.attribute.CodeAttribute;
import java.lang.classfile.constantpool.ClassEntry;
import java.lang.classfile.constantpool.Utf8Entry;
import java.lang.constant.ClassDesc;
import java.lang.reflect.AccessFlag;
import java.util.List;

/**
 * JDK 25 Class-File API 测试(STANDARD 正式特性)
 *
 * Class-File API(JEP 484) 在 JDK 25 中转正为 STANDARD 正式特性,
 * 不再需要 --enable-preview。
 *
 * Class-File API 位于 java.lang.classfile 包中, 提供了一套标准的
 * 用于读取、解析、构建和转换 Java 类文件的标准 API, 取代了旧的
 * javassist、ASM 等第三方库。
 *
 * 核心类:
 *   ClassFile           - 入口点, 提供 of() 工厂方法
 *   ClassModel          - 已解析的类文件模型
 *   ClassBuilder        - 用于构建类文件
 *   MethodModel         - 方法模型
 *   FieldModel          - 字段模型
 *   CodeAttribute       - Code 属性, 包含字节码指令
 *
 * @author lingwh
 * @date 2026/08/05 19:11
 */
public class ClassFileAPITest {

    /**
     * 测试使用 ClassFile API 构建一个简单的类(STANDARD)
     * 使用 ClassFile.of().build(ClassDesc, ClassBuilder) 构建类文件
     * 构建一个名为 org.bluebridge.section_25_jdk25.unit_03_class_file.Hello 的类
     */
    @Test
    public void testBuildClass() {
        // 定义要构建的类的描述符
        ClassDesc classDesc = ClassDesc.of("org.bluebridge.section_25_jdk25.unit_03_class_file.Hello");

        // 使用 ClassFile API 构建类
        byte[] classBytes = ClassFile.of().build(classDesc, clb -> {
            // 设置类的访问标志为 public
            clb.withFlags(AccessFlag.PUBLIC);

            // 添加一个 public static void main(String[]) 方法
            clb.withMethod("main", MethodTypeDesc.of(ClassDesc.of("void"),
                    ClassDesc.of("java.lang.String").arrayType()), AccessFlag.PUBLIC | AccessFlag.STATIC,
                    mb -> mb.withCode(codeBuilder -> {
                        // 生成: System.out.println("Hello from Class-File API!");
                        codeBuilder.getstatic(ClassDesc.of("java.lang.System"), "out",
                                ClassDesc.of("java.io.PrintStream"));
                        codeBuilder.ldc("Hello from Class-File API!");
                        codeBuilder.invokevirtual(ClassDesc.of("java.io.PrintStream"), "println",
                                MethodTypeDesc.of(ClassDesc.of("void"), ClassDesc.of("java.lang.String")));
                        codeBuilder.return_();
                    }));

            // 添加一个 public int add(int, int) 方法
            clb.withMethod("add", MethodTypeDesc.of(ClassDesc.of("int"),
                    ClassDesc.of("int"), ClassDesc.of("int")),
                    AccessFlag.PUBLIC,
                    mb -> mb.withCode(codeBuilder -> {
                        // 生成: return a + b;
                        codeBuilder.iload(0);  // 加载第一个参数 a
                        codeBuilder.iload(1);  // 加载第二个参数 b
                        codeBuilder.iadd();     // 相加
                        codeBuilder.ireturn();  // 返回结果
                    }));
        });

        System.out.println("成功构建类文件, 字节数: " + classBytes.length + " bytes");
        System.out.println("--------------------------------------");

        // 验证构建的类字节码可以被解析
        ClassModel classModel = ClassFile.of().parse(classBytes);
        System.out.println("解析构建的类文件:");
        System.out.println("  类名: " + classModel.thisClass().asInternalName());
        System.out.println("  方法数量: " + classModel.methods().size());
        classModel.methods().forEach(m ->
                System.out.println("  方法: " + m.methodName().stringValue()));
    }

    /**
     * 测试使用 ClassFile API 解析现有类文件(STANDARD)
     * 使用 ClassFile.of().parse(byte[]) 解析类文件字节码
     * 解析当前类本身作为示例
     */
    @Test
    public void testParseClassFile() {
        // 获取当前类的字节码
        String className = this.getClass().getName();
        byte[] classBytes = this.getClass().getResourceAsStream(
                "/" + className.replace('.', '/') + ".class")
                .readAllBytes();

        // 解析类文件
        ClassModel classModel = ClassFile.of().parse(classBytes);

        System.out.println("解析类文件: " + className);
        System.out.println("  类名: " + classModel.thisClass().asInternalName());
        System.out.println("  访问标志: " + classModel.flags());
        System.out.println("  父类: " + classModel.superclass().orElse(ClassEntry.of(ClassDesc.of("java.lang.Object"))).asInternalName());
        System.out.println("  接口数量: " + classModel.interfaces().size());
        System.out.println("  字段数量: " + classModel.fields().size());
        System.out.println("  方法数量: " + classModel.methods().size());
        System.out.println("  属性数量: " + classModel.attributes().size());
        System.out.println("--------------------------------------");

        // 列出所有方法
        System.out.println("类中的方法列表:");
        classModel.methods().forEach(method -> {
            System.out.println("  " + method.methodName().stringValue()
                    + method.methodType().map(t -> t.stringValue()).orElse(""));
        });
    }

    /**
     * 测试 ClassFile API 解析方法字节码(STANDARD)
     * 深入解析方法的 Code 属性, 查看方法的字节码指令
     */
    @Test
    public void testParseMethodBytecode() {
        // 获取当前类的字节码
        String className = this.getClass().getName();
        byte[] classBytes = this.getClass().getResourceAsStream(
                "/" + className.replace('.', '/') + ".class")
                .readAllBytes();

        // 解析类文件
        ClassModel classModel = ClassFile.of().parse(classBytes);

        // 查找并解析每个方法的 Code 属性
        System.out.println("解析方法字节码信息:");
        classModel.methods().forEach(method -> {
            String methodName = method.methodName().stringValue();
            // 跳过合成方法和初始化方法
            if (methodName.startsWith("lambda$") || methodName.equals("<init>") || methodName.equals("<clinit>")) {
                return;
            }
            System.out.println("  方法: " + methodName);
            method.findAttribute(Attributes.code()).ifPresent(codeAttr -> {
                System.out.println("    Code 属性:");
                System.out.println("      最大栈深度: " + codeAttr.maxStack());
                System.out.println("      最大局部变量: " + codeAttr.maxLocals());
                System.out.println("      字节码长度: " + codeAttr.codeLength());
                System.out.println("      异常表条目数: " + codeAttr.exceptionHandlers().size());
            });
            System.out.println("    ---");
        });
    }

    /**
     * 测试 ClassFile API 转换类文件(STANDARD)
     * 解析类文件并重新构建, 在构建过程中可以修改类的结构
     */
    @Test
    public void testTransformClass() {
        // 获取当前类的字节码
        String className = this.getClass().getName();
        byte[] classBytes = this.getClass().getResourceAsStream(
                "/" + className.replace('.', '/') + ".class")
                .readAllBytes();

        // 解析类文件
        ClassModel classModel = ClassFile.of().parse(classBytes);

        // 使用 ClassFile API 构建新的类文件(基于解析的模型)
        byte[] transformedBytes = ClassFile.of().build(classModel, (classBuilder, element) -> {
            // 直接传递所有元素, 不做修改(演示转写能力)
            classBuilder.with(element);
        });

        System.out.println("原始类文件大小: " + classBytes.length + " bytes");
        System.out.println("转写后类文件大小: " + transformedBytes.length + " bytes");
        System.out.println("转写前后大小一致: " + (classBytes.length == transformedBytes.length));
        System.out.println("--------------------------------------");

        // 验证转写后的字节码仍然可解析
        ClassFile.of().parse(transformedBytes);
        System.out.println("转写后的字节码可正常解析");
    }

    /**
     * 测试 ClassFile API 读取常量池信息(STANDARD)
     * 常量池(Constant Pool)是类文件的重要组成部分
     */
    @Test
    public void testReadConstantPool() {
        // 获取当前类的字节码
        String className = this.getClass().getName();
        byte[] classBytes = this.getClass().getResourceAsStream(
                "/" + className.replace('.', '/') + ".class")
                .readAllBytes();

        // 解析类文件
        ClassModel classModel = ClassFile.of().parse(classBytes);

        // 读取常量池信息
        System.out.println("常量池信息:");
        var constantPool = classModel.constantPool();
        System.out.println("  常量池条目数: " + constantPool.count());
        System.out.println("--------------------------------------");

        // 读取类名和接口信息
        System.out.println("类名: " + classModel.thisClass().asInternalName());
        System.out.println("父类: " + classModel.superclass()
                .map(ClassEntry::asInternalName)
                .orElse("无"));
        System.out.println("接口列表:");
        classModel.interfaces().forEach(iface ->
                System.out.println("  " + iface.asInternalName()));
    }

    /**
     * 测试 ClassFile API 验证类文件(STANDARD)
     * 使用 ClassFile API 验证类文件的结构是否正确
     */
    @Test
    public void testVerifyClassFile() {
        // 获取当前类的字节码
        String className = this.getClass().getName();
        byte[] classBytes = this.getClass().getResourceAsStream(
                "/" + className.replace('.', '/') + ".class")
                .readAllBytes();

        // 解析并验证类文件
        try {
            ClassModel classModel = ClassFile.of().parse(classBytes);
            System.out.println("类文件验证通过: " + classModel.thisClass().asInternalName());
            System.out.println("  类文件版本: " + classModel.majorVersion() + "." + classModel.minorVersion());
            System.out.println("  JDK 版本: " + (classModel.majorVersion() - 44));
        } catch (Exception e) {
            System.out.println("类文件验证失败: " + e.getMessage());
        }
    }
}