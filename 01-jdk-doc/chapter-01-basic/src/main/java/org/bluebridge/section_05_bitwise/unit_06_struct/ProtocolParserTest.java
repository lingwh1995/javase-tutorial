package org.bluebridge.section_05_bitwise.unit_06_struct;

/**
 * 超声波燃气表报文四种解析方式对比测试
 *
 * 1. JDK ByteBuffer(手动模拟结构体)
 * 2. Netty ByteBuf(手动模拟结构体)
 * 3. Java 22 FFM API(结构体布局自动映射)
 * 4. Javolution Struct(结构体自动映射)
 *
 * 解析并输出全部字段, 最后比对四种方式的解析结果是否一致。
 *
 * @author lingwh
 * @date 2026/8/21 14:34
 */
public class ProtocolParserTest {

    /**
     * 南海港华超声波燃气表示例报文(十六进制), 帧长度字段 = 0x0097, 总长 = 151 字节
     */
    private static final String REPORT_MESSAGE = """
            680008009705013001260820165326000400040C39393939393939\
            393939393900000000000000000000000000000000000000000145\
            43383030472D434E00000100756100014C5B36365B4C0E79AF44A7\
            A744AF0A90FFB9001A000001267991759826383630303631303631\
            39303430373500006D2C19BE87EC151735B7B4C4FDAE01C21A9FFF\
            73106784F18A5AC4AD0845D66CE3FC16""";

    public static void main(String[] args) {
        Frame frameByByteBuffer = Jdk7ByteBufferProtocolParser.parse(REPORT_MESSAGE);
        System.out.println("---------------- 方式一: JDK ByteBuffer ----------------");
        System.out.println(frameByByteBuffer);

        Frame frameByNetty = NettyByteBufProtocolParser.parse(REPORT_MESSAGE);
        System.out.println("---------------- 方式二: Netty ByteBuf ----------------");
        System.out.println(frameByNetty);

        Frame frameByFma = Jdk22FmaProtocolParser.parse(REPORT_MESSAGE);
        System.out.println("---------------- 方式三: Java 22 FFM API ----------------");
        System.out.println(frameByFma);

        Frame frameByJavolution = JavolutionProtocolParser.parse(REPORT_MESSAGE);
        System.out.println("---------------- 方式四: Javolution Struct ----------------");
        System.out.println(frameByJavolution);
    }
}
