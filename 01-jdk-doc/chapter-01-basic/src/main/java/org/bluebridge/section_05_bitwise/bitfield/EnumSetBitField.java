package org.bluebridge.section_05_bitwise.bitfield;

import java.util.EnumSet;

/**
 * 使用 EnumSet 模拟 C 语言位域(Bit Field)
 *
 * C 语言中常用枚举常量 + 位运算模拟标志位域:
 *   enum FilePermission {
 *       READ    = 1 << 0,   // 0x01
 *       WRITE   = 1 << 1,   // 0x02
 *       EXECUTE = 1 << 2,   // 0x04
 *       DELETE  = 1 << 3,   // 0x08
 *   };
 *   // 使用: int flags = READ | WRITE;
 *
 * Java 中 enum + EnumSet 是最地道的标志位域替代方案。
 * enum 定义标志常量, EnumSet 提供位向量存储, 无需手动指定位偏移。
 * EnumSet 内部用位向量实现, 性能与 int 位运算相当, 且类型安全、API 可读性强。
 * 演化历程: EnumSet 从 JDK 1.5 开始提供, 是 Java 中模拟 C 位域标志的最佳实践。
 *
 * @author lingwh
 * @date 2026/08/19 11:35
 */
enum EnumSetBitField {
    READ, WRITE, EXECUTE, DELETE;
}
