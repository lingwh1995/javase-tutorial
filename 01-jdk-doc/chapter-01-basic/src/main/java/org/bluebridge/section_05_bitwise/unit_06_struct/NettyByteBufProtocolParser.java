package org.bluebridge.section_05_bitwise.unit_06_struct;

import cn.hutool.core.util.HexUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * 方式二: 使用 Netty 提供的 ByteBuf 手动模拟 C 语言结构体解析报文
 *
 * <p>实现思路: 通过 {@link Unpooled#wrappedBuffer(byte[])} 将报文包装为
 * Netty 的堆缓冲区(默认大端序), 用 readUnsignedByte()/readUnsignedShort() 等
 * 无符号读取方法顺序解析各字段, 无需手动 &amp; 0xFF, 代码更简洁。
 *
 * @author lingwh
 * @date 2026/8/21 14:17
 */
public class NettyByteBufProtocolParser {

    private NettyByteBufProtocolParser() {
    }

    /**
     * 使用 Netty ByteBuf 解析报文
     *
     * @param hex 十六进制报文, 如 "68000800970501300126..."
     * @return 解析结果模型
     * @throws IllegalArgumentException 报文格式不合法时抛出
     */
    public static Frame parse(String hex) {
        byte[] bytes = HexUtil.decodeHex(hex);

        // wrappedBuffer 默认引用计数为 1, 使用完必须 release 防止内存泄漏
        ByteBuf buffer = Unpooled.wrappedBuffer(bytes);
        try {
            // 固定头 9 字节: 帧头 1 + 协议类型 1 + 协议版本 1 + 帧长度 2 + 消息序号 1 + 控制域 1 + 命令码 2
            int startFlag = buffer.readUnsignedByte();
            int protocolType = buffer.readUnsignedByte();
            int protocolVersion = buffer.readUnsignedByte();
            int frameLength = buffer.readUnsignedShort();
            int messageSequence = buffer.readUnsignedByte();
            int controlField = buffer.readUnsignedByte();
            // 命令码 2 字节按 hex 字符串解析(如 "3001"), 而非数值
            byte[] commandCodeBytes = new byte[2];
            buffer.readBytes(commandCodeBytes);
            String commandCode = HexUtil.encodeHexStr(commandCodeBytes);

            // 帧头帧尾校验
            checkFrameHead(startFlag);
            checkFrameLength(frameLength, bytes.length);
            checkFrameTail(bytes[bytes.length - 1] & 0xFF);

            // 数据域: 长度 = 帧长度 - 12(固定头 9 + 校验域 2 + 帧尾 1)
            int dataAreaLength = frameLength - Frame.FIXED_OVERHEAD;
            byte[] dataArea = new byte[dataAreaLength];
            buffer.readBytes(dataArea);

            // 校验域 2 字节 + 帧尾 1 字节
            int crc = buffer.readUnsignedShort();
            int endFlag = buffer.readUnsignedByte();

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

            // 控制域位域拆分: direction(bit7) follow(bit6) reserved(bit5) functionCode(bit0-4)
            frame.setDirection((controlField >> 7) & 0x1);
            frame.setFollow((controlField >> 6) & 0x1);
            frame.setReserved((controlField >> 5) & 0x1);
            frame.setFunctionCode(controlField & 0x1F);
            return frame;
        } finally {
            // 释放引用计数, 避免堆外/池化内存泄漏
            buffer.release();
        }
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
