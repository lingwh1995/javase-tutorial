package org.bluebridge.section_05_bitwise.unit_04_struct;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * 使用 ByteBuffer 模拟 C 语言结构体的内存布局
 *
 * C 语言结构体的核心优势是精确控制内存布局:
 *   struct Student {
 *       int   id;         // 偏移 0,  4 字节
 *       char  name[16];   // 偏移 4,  16 字节
 *       int   age;        // 偏移 20, 4 字节
 *       float score;      // 偏移 24, 4 字节
 *   };  // 共 28 字节
 *
 * Java 中使用 ByteBuffer 按相同偏移读写, 可精确模拟 C 结构体的内存布局。
 * 这种方式常用于网络协议解析、二进制文件读写、JNI 交互等场景。
 *
 * @author lingwh
 * @date 2026/08/19 11:37
 */
public class ByteBufferStruct {

    // 模拟 C 结构体: int(4) + bytes(16) + int(4) + float(4) = 28 字节
    private static final int SIZE = 28;
    private static final int ID_OFFSET     = 0;
    private static final int NAME_OFFSET    = 4;
    private static final int NAME_LENGTH    = 16;
    private static final int AGE_OFFSET     = 20;
    private static final int SCORE_OFFSET   = 24;

    private final ByteBuffer buffer;

    public ByteBufferStruct() {
        // 分配非 Direct ByteBuffer, 用小端序(与 x86 一致)
        buffer = ByteBuffer.allocate(SIZE).order(ByteOrder.LITTLE_ENDIAN);
    }

    public ByteBufferStruct setId(int id) {
        buffer.putInt(ID_OFFSET, id);
        return this;
    }

    public ByteBufferStruct setName(String name) {
        byte[] bytes = name.getBytes();
        int len = Math.min(bytes.length, NAME_LENGTH - 1);
        // 先清零 name 区域
        for (int i = 0; i < NAME_LENGTH; i++) {
            buffer.put(NAME_OFFSET + i, (byte) 0);
        }
        // 写入名字
        for (int i = 0; i < len; i++) {
            buffer.put(NAME_OFFSET + i, bytes[i]);
        }
        return this;
    }

    public ByteBufferStruct setAge(int age) {
        buffer.putInt(AGE_OFFSET, age);
        return this;
    }

    public ByteBufferStruct setScore(float score) {
        buffer.putFloat(SCORE_OFFSET, score);
        return this;
    }

    public int getId() {
        return buffer.getInt(ID_OFFSET);
    }

    public String getName() {
        // 读取以 \0 结尾的字符串
        int len = 0;
        while (len < NAME_LENGTH && buffer.get(NAME_OFFSET + len) != 0) {
            len++;
        }
        byte[] bytes = new byte[len];
        for (int i = 0; i < len; i++) {
            bytes[i] = buffer.get(NAME_OFFSET + i);
        }
        return new String(bytes);
    }

    public int getAge() {
        return buffer.getInt(AGE_OFFSET);
    }

    public float getScore() {
        return buffer.getFloat(SCORE_OFFSET);
    }

    public byte[] toByteArray() {
        return buffer.array();
    }
}
