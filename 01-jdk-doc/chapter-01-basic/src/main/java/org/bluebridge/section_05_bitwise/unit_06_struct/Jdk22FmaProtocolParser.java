package org.bluebridge.section_05_bitwise.unit_06_struct;

import cn.hutool.core.util.HexUtil;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

/**
 * 方式三: 使用 JDK 22 提供的 Foreign Function &amp; Memory API(FFM API) 模拟 C 语言结构体解析报文
 *
 * <p>实现思路: FFM API 是 JDK 22 的正式特性(JEP 454)，用于替代 Unsafe 的裸内存操作。
 * 先将报文字节拷贝到 Arena 管理的堆外内存，再按字段偏移用
 * {@link MemorySegment#get(ValueLayout, long)} 绝对读取各字段；
 * 双字节字段位于奇数偏移，用单字节组合读取以避免对齐检查；
 * 数据域变长，用 asSlice 截取；Arena 自动释放内存。
 *
 * <p>固定头结构体布局(9 字节, 大端序):
 *
 * 偏移   大小   字段
 * 0      1      startFlag        帧头
 * 1      1      protocolType     协议类型
 * 2      1      protocolVersion  协议框架版本
 * 3      2      frameLength      帧长度
 * 5      1      messageSequence  消息序号
 * 6      1      controlField     控制域
 * 7      2      commandCode      命令码
 *
 * @author lingwh
 * @date 2026/8/21 14:17
 */
public class Jdk22FmaProtocolParser {

    private Jdk22FmaProtocolParser() {
    }

    /**
     * 使用 FFM API 解析报文
     *
     * @param hex 十六进制报文, 如 "68000800970501300126..."
     * @return 解析结果模型
     * @throws IllegalArgumentException 报文格式不合法时抛出
     */
    public static Frame parse(String hex) {
        byte[] bytes = HexUtil.decodeHex(hex);

        // Arena 管理堆外内存的生命周期, try-with-resources 自动释放
        try (Arena arena = Arena.ofConfined()) {
            // 分配堆外内存并把报文字节拷贝进去
            MemorySegment segment = arena.allocate(bytes.length);
            MemorySegment.copy(MemorySegment.ofArray(bytes), 0, segment, 0, bytes.length);

            // 按结构体偏移绝对读取固定头各字段(与方法一/二读取顺序一致)
            int startFlag = segment.get(ValueLayout.JAVA_BYTE, 0) & 0xFF;
            int protocolType = segment.get(ValueLayout.JAVA_BYTE, 1) & 0xFF;
            int protocolVersion = segment.get(ValueLayout.JAVA_BYTE, 2) & 0xFF;
            int frameLength = readUnsignedShortBigEndian(segment, 3);
            int messageSequence = segment.get(ValueLayout.JAVA_BYTE, 5) & 0xFF;
            int controlField = segment.get(ValueLayout.JAVA_BYTE, 6) & 0xFF;
            int commandCode = readUnsignedShortBigEndian(segment, 7);

            // 帧头帧尾校验
            checkFrameHead(startFlag);
            checkFrameLength(frameLength, bytes.length);
            checkFrameTail(bytes[bytes.length - 1] & 0xFF);

            // 数据域: 长度 = 帧长度 - 12, 从固定头 9 字节处截取
            int dataAreaLength = frameLength - Frame.FIXED_OVERHEAD;
            byte[] dataArea = segment.asSlice(Frame.HEADER_LENGTH, dataAreaLength)
                    .toArray(ValueLayout.JAVA_BYTE);

            // 校验域 2 字节 + 帧尾 1 字节
            int crc = readUnsignedShortBigEndian(segment, Frame.HEADER_LENGTH + dataAreaLength);
            int endFlag = segment.get(ValueLayout.JAVA_BYTE, Frame.HEADER_LENGTH + dataAreaLength + 2) & 0xFF;

            Frame frame = new Frame();
            frame.setStartFlag(startFlag);
            frame.setProtocolType(protocolType);
            frame.setProtocolVersion(protocolVersion);
            frame.setFrameLength(frameLength);
            frame.setMessageSequence(messageSequence);
            frame.setControlField(controlField);
            frame.setCommandCode(commandCode);
            frame.setDataArea(dataArea);
            frame.setCrc(crc);
            frame.setEndFlag(endFlag);

            // 控制域位域拆分: direction(bit7) follow(bit6) reserved(bit5) functionCode(bit0-5)
            frame.setDirection((controlField >> 7) & 0x1);
            frame.setFollow((controlField >> 6) & 0x1);
            frame.setReserved((controlField >> 5) & 0x1);
            frame.setFunctionCode(controlField & 0x3F);
            return frame;
        }
    }

    /**
     * 大端序读取无符号双字节(两个单字节组合)
     *
     * <p>JAVA_SHORT 布局默认要求 2 字节对齐, 而紧凑报文的双字节字段位于奇数偏移,
     * 直接使用 get(ValueLayout.JAVA_SHORT, offset) 会抛 Misaligned access;
     * 改为两次单字节读取再按大端组合, 可访问任意偏移。
     *
     * @param segment 内存段
     * @param offset  起始偏移
     * @return 无符号 16 位值
     */
    private static int readUnsignedShortBigEndian(MemorySegment segment, long offset) {
        int high = segment.get(ValueLayout.JAVA_BYTE, offset) & 0xFF;
        int low = segment.get(ValueLayout.JAVA_BYTE, offset + 1) & 0xFF;
        return (high << 8) | low;
    }

    private static void checkFrameHead(int startFlag) {
        if (startFlag != Frame.START_FLAG) {
            throw new IllegalArgumentException("帧头校验失败, 期望 0x" + Integer.toHexString(Frame.START_FLAG)
                    + ", 实际为 0x" + Integer.toHexString(startFlag));
        }
    }

    private static void checkFrameTail(int endFlag) {
        if (endFlag != Frame.END_FLAG) {
            throw new IllegalArgumentException("帧尾校验失败, 期望 0x" + Integer.toHexString(Frame.END_FLAG)
                    + ", 实际为 0x" + Integer.toHexString(endFlag));
        }
    }

    private static void checkFrameLength(int frameLength, int actualLength) {
        if (frameLength != actualLength) {
            throw new IllegalArgumentException("帧长度字段与实际报文长度不一致, 帧长度字段 = " + frameLength
                    + ", 实际报文长度 = " + actualLength);
        }
    }
}
