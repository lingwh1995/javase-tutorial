package org.bluebridge.section_05_bitwise.unit_06_struct;

import cn.hutool.core.util.HexUtil;
import javolution.io.Struct;
import javolution.io.Struct.Unsigned8;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * 方式四: 使用 Javolution 库的 Struct 类将 Java 类直接映射为 C 语言结构体解析报文
 *
 * <p>实现思路: 继承 {@link Struct} 声明帧结构的固定头, 成员类型用无符号类型
 * {@link Unsigned8}/{@link Unsigned16} 与 C 结构体对应, 通过
 * {@link Struct#setByteBuffer(ByteBuffer)} 绑定报文字节后自动完成内存布局映射,
 * 无需手动计算偏移; 数据域为变长字段, 无法用 Struct 成员直接表达, 改为
 * 手动按偏移读取, 这是自动映射方式对变长字段的常规处理。
 *
 * @author lingwh
 * @date 2026/8/21 14:18
 */
public class JavolutionProtocolParser {

    /**
     * 固定头 9 字节对应的 C 结构体:
     *
     * struct FrameHead {
     *     unsigned char  startFlag;       // 帧头
     *     unsigned char  protocolType;    // 协议类型
     *     unsigned char  protocolVersion; // 协议框架版本
     *     unsigned short frameLength;     // 帧长度
     *     unsigned char  messageSequence; // 消息序号
     *     unsigned char  controlField;    // 控制域
     *     unsigned short commandCode;     // 命令码
     * };
     *
     * 说明: Unsigned16 成员按 C 结构体规则会按 2 字节对齐, 导致紧凑报文(帧长度起始于奇数偏移 3)
     * 解析错位，因此双字节字段改用两个 Unsigned8 按大端组合表示，与报文紧凑布局严格一致。
     */
    private static class FrameHead extends Struct {
        private final Unsigned8 startFlag = new Unsigned8();
        private final Unsigned8 protocolType = new Unsigned8();
        private final Unsigned8 protocolVersion = new Unsigned8();
        private final Unsigned8 frameLengthHigh = new Unsigned8();
        private final Unsigned8 frameLengthLow = new Unsigned8();
        private final Unsigned8 messageSequence = new Unsigned8();
        private final Unsigned8 controlField = new Unsigned8();
        private final Unsigned8 commandCodeHigh = new Unsigned8();
        private final Unsigned8 commandCodeLow = new Unsigned8();
    }

    private JavolutionProtocolParser() {
    }

    /**
     * 使用 Javolution Struct 解析报文
     *
     * @param hex 十六进制报文，如 "68000800970501300126..."
     * @return 解析结果模型
     * @throws IllegalArgumentException 报文格式不合法时抛出
     */
    public static Frame parse(String hex) {
        byte[] bytes = HexUtil.decodeHex(hex);

        ByteBuffer buffer = ByteBuffer.wrap(bytes).order(ByteOrder.BIG_ENDIAN);

        // 绑定报文字节到结构体，自动完成字段与内存布局的映射(第二个参数为结构体在缓冲区中的起始位置)
        FrameHead head = new FrameHead();
        head.setByteBuffer(buffer, 0);

        // 无符号类型 get() 返回 short, 与 0xFF 相与转成真正的无符号值; 双字节字段按大端组合
        int startFlag = head.startFlag.get() & 0xFF;
        int protocolType = head.protocolType.get() & 0xFF;
        int protocolVersion = head.protocolVersion.get() & 0xFF;
        int frameLength = ((head.frameLengthHigh.get() & 0xFF) << 8) | (head.frameLengthLow.get() & 0xFF);
        int messageSequence = head.messageSequence.get() & 0xFF;
        int controlField = head.controlField.get() & 0xFF;
        // 命令码 2 字节按 hex 字符串解析(如 "3001"), 而非数值
        String commandCode = HexUtil.encodeHexStr(new byte[]{
                (byte) (head.commandCodeHigh.get() & 0xFF),
                (byte) (head.commandCodeLow.get() & 0xFF)
        });

        // 帧头帧尾校验
        checkFrameHead(startFlag);
        checkFrameLength(frameLength, bytes.length);
        checkFrameTail(bytes[bytes.length - 1] & 0xFF);

        // 数据域: 长度 = 帧长度 - 12, 从固定头 9 字节处按绝对偏移读取
        int dataAreaLength = frameLength - Frame.FIXED_OVERHEAD;
        byte[] dataArea = new byte[dataAreaLength];
        for (int i = 0; i < dataAreaLength; i++) {
            dataArea[i] = buffer.get(Frame.HEADER_LENGTH + i);
        }

        // 校验域 2 字节 + 帧尾 1 字节(绝对偏移读取, 不移动缓冲区 position)
        int crc = buffer.getShort(Frame.HEADER_LENGTH + dataAreaLength) & 0xFFFF;
        int endFlag = buffer.get(Frame.HEADER_LENGTH + dataAreaLength + 2) & 0xFF;

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
