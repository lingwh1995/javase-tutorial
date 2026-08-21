package org.bluebridge.section_05_bitwise.unit_06_struct;

import cn.hutool.core.util.HexUtil;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * 方式一: 使用 JDK 提供的 ByteBuffer 手动模拟 C 语言结构体解析报文
 *
 * <p>实现思路: 通过 {@link ByteBuffer#wrap(byte[])} 将报文包装为大端序缓冲区,
 * 按帧结构定义的偏移依次读取各字段(单字节用 get() 后 &amp; 0xFF 转无符号,
 * 双字节用 getShort() 后 &amp; 0xFFFF 转无符号), 控制域再按位拆分出位域。
 *
 * @author lingwh
 * @date 2026/8/21 14:17
 */
public class Jdk7ByteBufferProtocolParser {

    private Jdk7ByteBufferProtocolParser() {
    }

    /**
     * 使用 ByteBuffer 解析报文
     *
     * @param hex 十六进制报文, 如 "68000800970501300126..."
     * @return 解析结果模型
     * @throws IllegalArgumentException 报文格式不合法时抛出
     */
    public static Frame parse(String hex) {
        byte[] bytes = HexUtil.decodeHex(hex);

        // 大端序包装, 与帧结构定义一致
        ByteBuffer buffer = ByteBuffer.wrap(bytes).order(ByteOrder.BIG_ENDIAN);

        // 固定头 9 字节: 帧头 1 + 协议类型 1 + 协议版本 1 + 帧长度 2 + 消息序号 1 + 控制域 1 + 命令码 2
        int startFlag = buffer.get() & 0xFF;
        int protocolType = buffer.get() & 0xFF;
        int protocolVersion = buffer.get() & 0xFF;
        int frameLength = buffer.getShort() & 0xFFFF;
        int messageSequence = buffer.get() & 0xFF;
        int controlField = buffer.get() & 0xFF;
        int commandCode = buffer.getShort() & 0xFFFF;

        // 帧头帧尾校验
        checkFrameHead(startFlag);
        checkFrameLength(frameLength, bytes.length);
        checkFrameTail(bytes[bytes.length - 1] & 0xFF);

        // 数据域: 长度 = 帧长度 - 12(固定头 9 + 校验域 2 + 帧尾 1)
        int dataAreaLength = frameLength - Frame.FIXED_OVERHEAD;
        byte[] dataArea = new byte[dataAreaLength];
        buffer.get(dataArea);

        // 校验域 2 字节 + 帧尾 1 字节
        int crc = buffer.getShort() & 0xFFFF;
        int endFlag = buffer.get() & 0xFF;

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
