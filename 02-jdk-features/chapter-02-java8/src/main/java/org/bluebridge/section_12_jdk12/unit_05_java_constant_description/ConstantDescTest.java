﻿package org.bluebridge.section_12_jdk12.unit_05_java_constant_description;

import org.junit.Test;

import java.lang.constant.ClassDesc;
import java.lang.constant.ConstantDesc;
import java.lang.constant.DirectMethodHandleDesc;
import java.lang.constant.MethodHandleDesc;
import java.lang.constant.MethodTypeDesc;
import java.lang.constant.DynamicConstantDesc;

/**
 * JDK 12 java.lang.constant.ConstantDesc API（JEP 334，PREVIEW 特性）
 * <p>
 * ConstantDesc 是常量描述符的顶级接口，用于描述可加载的常量值。
 * 该 API 提供了类型安全的常量描述方式，支持：
 * 1. ClassDesc - 类常量描述符
 * 2. MethodTypeDesc - 方法类型描述符
 * 3. MethodHandleDesc - 方法句柄描述符
 * 4. DynamicConstantDesc - 动态常量描述符
 * <p>
 * 注意：该 API 在 JDK 12 中为 PREVIEW 特性，需要启用预览选项。
 *
 * @author lingwh
 * @date 2026/08/06 14:07
 */
public class ConstantDescTest {

    /**
     * 测试 ClassDesc 的基本用法：创建类描述符
     */
    @Test
    public void testClassDescBasic() {
        // 使用 ofDescriptor 创建类描述符
        ClassDesc stringDesc = ClassDesc.ofDescriptor("Ljava/lang/String;");
        System.out.println("String 类描述符: " + stringDesc);

        // 使用 of 方法通过全限定类名创建
        ClassDesc listDesc = ClassDesc.of("java.util.List");
        System.out.println("List 类描述符: " + listDesc);

        // 使用 of 方法创建数组类描述符
        ClassDesc intArrayDesc = ClassDesc.of("[I");
        System.out.println("int 数组描述符: " + intArrayDesc);
    }

    /**
     * 测试 ClassDesc 的 displayName 和 packageName 方法
     */
    @Test
    public void testClassDescName() {
        ClassDesc desc = ClassDesc.of("java.util.ArrayList");
        System.out.println("displayName: " + desc.displayName());
        System.out.println("packageName: " + desc.packageName());
    }

    /**
     * 测试 ClassDesc 的 arrayType 方法创建数组类型
     */
    @Test
    public void testClassDescArrayType() {
        ClassDesc elementDesc = ClassDesc.of("java.lang.String");
        // 创建 String 的一维数组类型
        ClassDesc arrayDesc = elementDesc.arrayType();
        System.out.println("String 数组类型: " + arrayDesc);

        // 创建多维数组类型
        ClassDesc multiArrayDesc = arrayDesc.arrayType();
        System.out.println("String 二维数组类型: " + multiArrayDesc);
    }

    /**
     * 测试 ClassDesc 的 isArray 和 isPrimitive 方法
     */
    @Test
    public void testClassDescTypeCheck() {
        ClassDesc stringDesc = ClassDesc.of("java.lang.String");
        ClassDesc intDesc = ClassDesc.ofDescriptor("I");
        ClassDesc arrayDesc = stringDesc.arrayType();

        System.out.println("String isArray: " + stringDesc.isArray());
        System.out.println("int isPrimitive: " + intDesc.isPrimitive());
        System.out.println("String[] isArray: " + arrayDesc.isArray());
    }

    /**
     * 测试 MethodTypeDesc 描述方法类型
     */
    @Test
    public void testMethodTypeDesc() {
        // 描述一个方法类型: (int, int) -> int
        MethodTypeDesc addMethodType = MethodTypeDesc.of(
                ClassDesc.ofDescriptor("I"),
                ClassDesc.ofDescriptor("I"),
                ClassDesc.ofDescriptor("I")
        );
        System.out.println("add 方法类型: " + addMethodType);

        // 描述一个无参返回 String 的方法类型: () -> String
        MethodTypeDesc toStringMethodType = MethodTypeDesc.of(
                ClassDesc.of("java.lang.String")
        );
        System.out.println("toString 方法类型: " + toStringMethodType);

        // 描述一个参数为 String 的方法类型: (String) -> int
        MethodTypeDesc lengthMethodType = MethodTypeDesc.of(
                ClassDesc.ofDescriptor("I"),
                ClassDesc.of("java.lang.String")
        );
        System.out.println("length 方法类型: " + lengthMethodType);
    }

    /**
     * 测试 MethodTypeDesc 的 parameterCount 和 returnType 方法
     */
    @Test
    public void testMethodTypeDescInspect() {
        MethodTypeDesc methodType = MethodTypeDesc.of(
                ClassDesc.of("java.lang.String"),
                ClassDesc.of("java.lang.Object")
        );
        System.out.println("参数个数: " + methodType.parameterCount());
        System.out.println("返回类型: " + methodType.returnType());
        System.out.println("参数类型: " + methodType.parameterType(0));
    }

    /**
     * 测试 DirectMethodHandleDesc 描述直接方法句柄
     */
    @Test
    public void testDirectMethodHandleDesc() {
        // 描述静态方法句柄: Integer.parseInt(String)
        DirectMethodHandleDesc parseIntDesc = DirectMethodHandleDesc.of(
                DirectMethodHandleDesc.Kind.STATIC,
                ClassDesc.of("java.lang.Integer"),
                "parseInt",
                MethodTypeDesc.of(ClassDesc.ofDescriptor("I"), ClassDesc.of("java.lang.String"))
        );
        System.out.println("parseInt 方法句柄: " + parseIntDesc);
        System.out.println("方法句柄所有者: " + parseIntDesc.owner());
        System.out.println("方法句柄名称: " + parseIntDesc.methodName());
        System.out.println("方法句柄类型: " + parseIntDesc.invocationType());

        // 描述虚方法句柄: String.length()
        DirectMethodHandleDesc lengthDesc = DirectMethodHandleDesc.of(
                DirectMethodHandleDesc.Kind.VIRTUAL,
                ClassDesc.of("java.lang.String"),
                "length",
                MethodTypeDesc.of(ClassDesc.ofDescriptor("I"))
        );
        System.out.println("length 虚方法句柄: " + lengthDesc);
    }

    /**
     * 测试 MethodHandleDesc 描述构造函数句柄
     */
    @Test
    public void testConstructorMethodHandleDesc() {
        // 描述构造函数句柄: new StringBuilder()
        MethodHandleDesc constructorDesc = MethodHandleDesc.ofConstructor(
                ClassDesc.of("java.lang.StringBuilder")
        );
        System.out.println("构造函数句柄: " + constructorDesc);

        // 描述带参构造函数句柄: new StringBuilder(String)
        MethodHandleDesc constructorWithParamDesc = MethodHandleDesc.ofConstructor(
                ClassDesc.of("java.lang.StringBuilder"),
                ClassDesc.of("java.lang.String")
        );
        System.out.println("带参构造函数句柄: " + constructorWithParamDesc);
    }

    /**
     * 测试 DynamicConstantDesc 描述动态常量
     */
    @Test
    public void testDynamicConstantDesc() {
        // 创建一个动态常量描述符
        DynamicConstantDesc<String> dynamicConstant = DynamicConstantDesc.of(
                DirectMethodHandleDesc.of(
                        DirectMethodHandleDesc.Kind.STATIC,
                        ClassDesc.of("java.lang.System"),
                        "getProperty",
                        MethodTypeDesc.of(
                                ClassDesc.of("java.lang.String"),
                                ClassDesc.of("java.lang.String")
                        )
                ),
                "java.version"
        );
        System.out.println("动态常量描述符: " + dynamicConstant);
        System.out.println("常量名称: " + dynamicConstant.constantName());
        System.out.println("常量类型: " + dynamicConstant.constantType());
    }

    /**
     * 测试 ConstantDesc 接口的 canResolve 方法
     */
    @Test
    public void testConstantDescResolve() {
        // ConstantDesc 接口定义常量描述符的解析能力
        ClassDesc desc = ClassDesc.of("java.lang.Object");
        System.out.println("ClassDesc 实现了 ConstantDesc: " + (desc instanceof ConstantDesc));
        System.out.println("ClassDesc 描述符: " + desc.descriptorString());
    }

    /**
     * 测试 MethodTypeDesc 的 changeReturnType 和 changeParameterType 方法
     */
    @Test
    public void testMethodTypeDescModify() {
        MethodTypeDesc original = MethodTypeDesc.of(
                ClassDesc.ofDescriptor("I"),
                ClassDesc.of("java.lang.String")
        );
        System.out.println("原始方法类型: " + original);

        // 修改返回类型
        MethodTypeDesc changedReturn = original.changeReturnType(ClassDesc.of("java.lang.Integer"));
        System.out.println("修改返回类型后: " + changedReturn);

        // 修改参数类型
        MethodTypeDesc changedParam = original.changeParameterType(0, ClassDesc.of("java.lang.CharSequence"));
        System.out.println("修改参数类型后: " + changedParam);
    }

    /**
     * 测试 ClassDesc 的 nested 方法创建嵌套类描述符
     */
    @Test
    public void testClassDescNested() {
        // 创建 Map.Entry 的嵌套类描述符
        ClassDesc mapDesc = ClassDesc.of("java.util.Map");
        ClassDesc entryDesc = mapDesc.nested("Entry");
        System.out.println("Map.Entry 描述符: " + entryDesc);
        System.out.println("displayName: " + entryDesc.displayName());
    }

    /**
     * 测试 MethodHandleDesc 使用 ofMethod 工厂方法
     */
    @Test
    public void testMethodHandleDescOfMethod() {
        // 使用 ofMethod 工厂方法创建虚方法句柄
        MethodHandleDesc virtualDesc = MethodHandleDesc.ofMethod(
                DirectMethodHandleDesc.Kind.VIRTUAL,
                ClassDesc.of("java.lang.String"),
                "toUpperCase",
                MethodTypeDesc.of(ClassDesc.of("java.lang.String"))
        );
        System.out.println("虚方法句柄 (ofMethod): " + virtualDesc);
    }

    /**
     * 测试 ConstantDesc 的 toString 和 equals 方法
     */
    @Test
    public void testConstantDescEquals() {
        ClassDesc desc1 = ClassDesc.of("java.lang.String");
        ClassDesc desc2 = ClassDesc.of("java.lang.String");
        ClassDesc desc3 = ClassDesc.of("java.lang.Object");

        System.out.println("desc1: " + desc1);
        System.out.println("desc2: " + desc2);
        System.out.println("desc1.equals(desc2): " + desc1.equals(desc2));
        System.out.println("desc1.equals(desc3): " + desc1.equals(desc3));
        System.out.println("desc1.hashCode(): " + desc1.hashCode());
        System.out.println("desc2.hashCode(): " + desc2.hashCode());
    }
}