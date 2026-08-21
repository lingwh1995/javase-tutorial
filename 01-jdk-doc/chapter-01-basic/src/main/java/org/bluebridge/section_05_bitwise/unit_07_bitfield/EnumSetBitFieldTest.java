package org.bluebridge.section_05_bitwise.unit_07_bitfield;

import java.util.EnumSet;

/**
 * 使用 EnumSet 模拟 C 语言位域(Bit Field) 测试
 *
 * @author lingwh
 * @date 2026/08/19 11:35
 */
public class EnumSetBitFieldTest {

    public static void main(String[] args) {
        // 创建权限集合: 可读 + 可写
        EnumSet<EnumSetBitField> permissions = EnumSet.of(EnumSetBitField.READ, EnumSetBitField.WRITE);

        System.out.println("--- 使用 EnumSet 模拟 C 位域(标志位) ---");
        System.out.println("权限集合: " + permissions);
        System.out.println("可读: " + permissions.contains(EnumSetBitField.READ));     // true
        System.out.println("可写: " + permissions.contains(EnumSetBitField.WRITE));    // true
        System.out.println("可执行: " + permissions.contains(EnumSetBitField.EXECUTE)); // false
        System.out.println("可删除: " + permissions.contains(EnumSetBitField.DELETE));  // false

        // 添加权限
        permissions.add(EnumSetBitField.EXECUTE);
        System.out.println();
        System.out.println("添加 EXECUTE 后: " + permissions);

        // 批量操作: 添加所有权限
        permissions.addAll(EnumSet.allOf(EnumSetBitField.class));
        System.out.println("添加全部权限后: " + permissions);

        // 取差集: 移除只读权限
        permissions.removeAll(EnumSet.of(EnumSetBitField.READ));
        System.out.println("移除只读后: " + permissions);

        System.out.println();
        System.out.println("三种方式对比:");
        System.out.println("int + 位运算:  最紧凑,但可读性差, 无类型安全");
        System.out.println("BitSet:       长度可变, 适合标志数量不固定的场景");
        System.out.println("EnumSet:      类型安全, API 可读, 内部位向量实现, 最佳实践");
    }
}
