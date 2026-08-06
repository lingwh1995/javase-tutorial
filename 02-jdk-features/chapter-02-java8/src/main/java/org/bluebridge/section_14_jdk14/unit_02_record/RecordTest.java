﻿package org.bluebridge.section_14_jdk14.unit_02_record;

import org.junit.Test;

/**
 * Java14 Record 测试(PREVIEW 特性)
 *
 * Record(JEP 359) 是 JDK14 引入的 PREVIEW 特性, 用于简化不可变数据类的定义,
 * 编译器会根据组件(component)自动生成规范构造器、访问器、equals()、hashCode()、toString() 等方法。
 *
 * Record 的构造器形式:
 * 1. 规范构造器(canonical constructor): 参数列表与组件列表一致, 由编译器自动生成
 * 2. 紧凑构造器(compact constructor): 参数列表为空, 在字段赋值前执行校验或加工
 * 3. 自定义构造器(重载): 必须通过 this(...) 委托给规范构造器
 *
 * 注意: Record 在 JDK 14 中是 PREVIEW 特性, 需要 JDK 14 + --enable-preview 才能编译运行,
 *       本文件使用真实的 record 语法编写, record 作为嵌套类型定义在测试类中(嵌套 record 隐式 static)
 *
 * @author lingwh
 * @date 2026/08/05 18:31
 */
public class RecordTest {

    /**
     * JDK 14 PREVIEW 特性的真实 record 定义(嵌套 record, 隐式 static)
     * 编译器自动生成规范构造器、访问器、equals()、hashCode()、toString()
     */
    record Point(int x, int y) {
        // 紧凑构造器(compact constructor): 参数列表为空, 在字段赋值前执行校验
        Point {
            if (x < 0 || y < 0) {
                throw new IllegalArgumentException("坐标不能为负数");
            }
        }

        // 自定义构造器(重载): 必须通过 this(...) 委托给规范构造器
        Point() {
            this(0, 0);
        }
    }

    /**
     * 测试 Record 定义与基本使用(PREVIEW)
     * 注意: record 语法在 JDK14 中是 PREVIEW 特性, 需要 JDK 14 + --enable-preview 才能编译
     */
    @Test
    public void testRecord_Preview() {
        // 定义 record 后直接通过构造器创建对象
        Point point = new Point(10, 20);
        System.out.println("record 创建的 Point: " + point);
    }

    /**
     * 测试 Record 自动生成的访问器(PREVIEW)
     * record 的访问器命名规则: 去掉组件名后的括号, 如 x()、y()
     */
    @Test
    public void testRecordAccessors_Preview() {
        Point point = new Point(3, 4);
        System.out.println("x() = " + point.x());
        System.out.println("y() = " + point.y());
    }

    /**
     * 测试 Record 自动生成的 equals() 与 hashCode()(PREVIEW)
     * record 基于所有组件生成 equals() 和 hashCode(), 组件值相同的两个 record 对象相等
     */
    @Test
    public void testRecordEqualsAndHashCode_Preview() {
        Point p1 = new Point(1, 2);
        Point p2 = new Point(1, 2);
        Point p3 = new Point(1, 3);
        System.out.println("p1.equals(p2) = " + p1.equals(p2));
        System.out.println("p1.equals(p3) = " + p1.equals(p3));
        System.out.println("p1.hashCode() = " + p1.hashCode());
        System.out.println("p2.hashCode() = " + p2.hashCode());
        System.out.println("p1 == p2 = " + (p1 == p2));
    }

    /**
     * 测试 Record 自动生成的 toString()(PREVIEW)
     * record 的 toString() 格式为: 类名[组件1=值1, 组件2=值2, ...]
     */
    @Test
    public void testRecordToString_Preview() {
        Point point = new Point(5, 6);
        System.out.println("record 的 toString(): " + point.toString());
    }

    /**
     * 测试 Record 自定义构造器(PREVIEW)
     * record 支持两种自定义构造器:
     * 1. 紧凑构造器(compact constructor): 参数列表为空, 在字段赋值前执行校验或加工
     * 2. 自定义构造器(重载): 必须通过 this(...) 委托给规范构造器
     */
    @Test
    public void testRecordCustomConstructor_Preview() {
        // 正常创建: 通过紧凑构造器完成参数校验
        Point point = new Point(3, 4);
        System.out.println("正常创建: " + point);

        // 使用自定义无参构造器(重载), 委托给 this(0, 0)
        Point origin = new Point();
        System.out.println("无参构造器创建: " + origin);

        try {
            // 传入负数, 触发紧凑构造器的参数校验, 抛出 IllegalArgumentException
            Point negative = new Point(-1, 2);
            System.out.println("负数创建: " + negative);
        } catch (IllegalArgumentException e) {
            System.out.println("捕获紧凑构造器抛出的异常: " + e.getMessage());
        }
    }
}
