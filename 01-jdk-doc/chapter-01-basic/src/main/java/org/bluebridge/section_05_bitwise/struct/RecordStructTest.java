package org.bluebridge.section_05_bitwise.struct;

/**
 * 测试 record 模拟 C 结构体
 *
 * @author lingwh
 * @date 2026/08/19 14:08
 */
public class RecordStructTest {

    public static void main(String[] args) {
        System.out.println("--- 使用 record 模拟 C 结构体 ---");

        RecordStruct s1 = new RecordStruct(1, "张三", 20, 89.5f);
        RecordStruct s2 = new RecordStruct(2, "李四", 22, 95.0f);

        // record 自动生成 toString / getter
        System.out.println(s1);
        System.out.println("  id=" + s1.id() + ", name=" + s1.name());
        System.out.println(s2);

        // record 的不可变性: 无法修改字段, 更接近 C 结构体值传递语义
        System.out.println();
        System.out.println("--- 与普通类的区别 ---");
        System.out.println("record 字段不可变, 更接近 C 结构体值语义");
        System.out.println("自动生成的 equals/hashCode, 适合做值对象");
    }
}
