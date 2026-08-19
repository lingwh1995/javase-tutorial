package org.bluebridge.section_05_bitwise.bitfield;

/**
 * 使用 int + 位运算模拟 C 语言位域(Bit Field)
 *
 * C 语言位域:
 *   struct PackedFlags {
 *       unsigned int type     : 3;   // 3位, 范围 0-7
 *       unsigned int level    : 5;   // 5位, 范围 0-31
 *       unsigned int code     : 8;   // 8位, 范围 0-255
 *       unsigned int reserved : 16;  // 16位, 范围 0-65535
 *   };  // 共 32 位 = 4 字节, 紧凑存储在一个 int 中
 *
 * Java 没有位域语法，通过位运算将多个小字段打包到一个 int 中实现等价效果。
 *
 * @author lingwh
 * @date 2026/08/19 11:10
 */
public class BitwiseBitField {

    // 各字段的位宽
    private static final int TYPE_BITS     = 3;
    private static final int LEVEL_BITS    = 5;
    private static final int CODE_BITS     = 8;
    private static final int RESERVED_BITS  = 16;

    // 各字段的位偏移(从低位开始)
    private static final int TYPE_OFFSET     = 0;
    private static final int LEVEL_OFFSET    = TYPE_BITS;           // 3
    private static final int CODE_OFFSET     = TYPE_BITS + LEVEL_BITS; // 8
    private static final int RESERVED_OFFSET = TYPE_BITS + LEVEL_BITS + CODE_BITS; // 16

    // 各字段的掩码
    private static final int TYPE_MASK     = (1 << TYPE_BITS) - 1;       // 0x00000007
    private static final int LEVEL_MASK    = (1 << LEVEL_BITS) - 1;      // 0x0000001F
    private static final int CODE_MASK     = (1 << CODE_BITS) - 1;       // 0x000000FF
    private static final int RESERVED_MASK = (1 << RESERVED_BITS) - 1;  // 0x0000FFFF

    // 打包后的数据
    private int packed;

    // ===== 写入(打包) =====

    public void setType(int type) {
        // 清空对应位, 再写入新值
        packed = (packed & ~(TYPE_MASK << TYPE_OFFSET)) | ((type & TYPE_MASK) << TYPE_OFFSET);
    }

    public void setLevel(int level) {
        packed = (packed & ~(LEVEL_MASK << LEVEL_OFFSET)) | ((level & LEVEL_MASK) << LEVEL_OFFSET);
    }

    public void setCode(int code) {
        packed = (packed & ~(CODE_MASK << CODE_OFFSET)) | ((code & CODE_MASK) << CODE_OFFSET);
    }

    public void setReserved(int reserved) {
        packed = (packed & ~(RESERVED_MASK << RESERVED_OFFSET)) | ((reserved & RESERVED_MASK) << RESERVED_OFFSET);
    }

    // ===== 读取(解包) =====

    public int getType() {
        return (packed >> TYPE_OFFSET) & TYPE_MASK;
    }

    public int getLevel() {
        return (packed >> LEVEL_OFFSET) & LEVEL_MASK;
    }

    public int getCode() {
        return (packed >> CODE_OFFSET) & CODE_MASK;
    }

    public int getReserved() {
        return (packed >> RESERVED_OFFSET) & RESERVED_MASK;
    }

    public int getPacked() {
        return packed;
    }
}
