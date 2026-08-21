package org.bluebridge.section_05_bitwise.unit_06_struct;

import cn.hutool.core.util.HexUtil;
import lombok.Data;

/**
 * 南海港华超声波燃气表报文解析结果模型
 *
 * <p>帧结构（大端序）：
 * <pre>
 * 偏移   大小   字段              说明
 * 0      1      startFlag        帧头
 * 1      1      protocolType     协议类型
 * 2      1      protocolVersion  协议框架版本
 * 3      2      frameLength      帧长度(整个报文的字节数)
 * 5      1      messageSequence  消息序号
 * 6      1      controlField     控制域(位域)
 * 7      2      commandCode      命令码
 * 9      N      dataArea         数据域, N = frameLength - 12
 * 9+N    2      crc              校验域
 * 11+N   1      endFlag          帧尾
 * </pre>
 * 控制域(1 字节)按位拆分：
 * <pre>
 * 位序号  位长   名称          说明
 * 7       1      direction     方向
 * 6       1      follow        后续帧标识
 * 5       1      reserved      保留
 * 0-5     6      functionCode  功能码
 * </pre>
 *
 * @author lingwh
 * @date 2026/8/21 14:16
 */
@Data
public class Frame {

    /**
     * 帧头固定值
     */
    public static final int START_FLAG = 0x68;

    /**
     * 帧尾固定值
     */
    public static final int END_FLAG = 0x16;

    /**
     * 固定头长度(9 字节) = 帧头 + 协议类型 + 协议版本 + 帧长度 + 消息序号 + 控制域 + 命令码
     */
    public static final int HEADER_LENGTH = 9;

    /**
     * 除数据域外的固定开销(12 字节) = 固定头 9 + 校验域 2 + 帧尾 1，故数据域长度 = 帧长度 - 12
     */
    public static final int FIXED_OVERHEAD = 12;

    /**
     * 帧头
     */
    private int startFlag;

    /**
     * 协议类型
     */
    private int protocolType;

    /**
     * 协议框架版本
     */
    private int protocolVersion;

    /**
     * 帧长度(整个报文的字节数)
     */
    private int frameLength;

    /**
     * 消息序号
     */
    private int messageSequence;

    /**
     * 控制域(1 字节，含位域)
     */
    private int controlField;

    /**
     * 命令码
     */
    private int commandCode;

    /**
     * 数据域
     */
    private byte[] dataArea;

    /**
     * 校验域
     */
    private int crc;

    /**
     * 帧尾
     */
    private int endFlag;

    /**
     * 方向(控制域 bit7)
     */
    private int direction;

    /**
     * 后续帧标识(控制域 bit6)
     */
    private int follow;

    /**
     * 保留位(控制域 bit5)
     */
    private int reserved;

    /**
     * 功能码(控制域 bit0-5，6 位)
     */
    private int functionCode;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("startFlag              = ").append(String.format("0x%02X (%d)", startFlag, startFlag)).append("\n");
        sb.append("protocolType           = ").append(String.format("0x%02X (%d)", protocolType, protocolType)).append("\n");
        sb.append("protocolVersion        = ").append(String.format("0x%02X (%d)", protocolVersion, protocolVersion)).append("\n");
        sb.append("frameLength            = ").append(String.format("0x%04X (%d)", frameLength, frameLength)).append("\n");
        sb.append("messageSequence        = ").append(String.format("0x%02X (%d)", messageSequence, messageSequence)).append("\n");
        sb.append("controlField           = ").append(String.format("0x%02X (%s)", controlField, String.format("%8s", Integer.toBinaryString(controlField)).replace(' ', '0'))).append("\n");
        sb.append("|- direction(bit7)       = ").append(direction).append("\n");
        sb.append("|- follow(bit6)          = ").append(follow).append("\n");
        sb.append("|- reserved(bit5)        = ").append(reserved).append("\n");
        sb.append("|- functionCode(bit0-5)  = ").append(functionCode).append("\n");
        sb.append("commandCode            = ").append(String.format("0x%04X (%d)", commandCode, commandCode)).append("\n");
        sb.append("dataArea               = ").append(dataArea != null ? HexUtil.encodeHexStr(dataArea) : "null").append("\n");
        sb.append("crc                    = ").append(String.format("0x%04X (%d)", crc, crc)).append("\n");
        sb.append("endFlag                = ").append(String.format("0x%02X (%d)", endFlag, endFlag)).append("\n");
        return sb.toString();
    }
}
