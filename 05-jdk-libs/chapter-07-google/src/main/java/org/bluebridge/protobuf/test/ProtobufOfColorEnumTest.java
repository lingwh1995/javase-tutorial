package org.bluebridge.protobuf.test;

import lombok.extern.slf4j.Slf4j;
import org.bluebridge.protobuf.proto.ColorProto;
import org.junit.Test;

/**
 * 测试 Protobuf 枚举
 *
 * @author lingwh
 * @date 2025/11/7 15:19
 */
@Slf4j
public class ProtobufOfColorEnumTest {

    /**
     * 测试 Color 枚举的基本使用
     */
    @Test
    public void testColorEnum() {
        // 创建不同的颜色枚举值
        ColorProto.Color red = ColorProto.Color.COLOR_RED;
        ColorProto.Color green = ColorProto.Color.COLOR_GREEN;
        ColorProto.Color blue = ColorProto.Color.COLOR_BLUE;
        log.info("Red: {}", red);
        log.info("Green: {}", green);
        log.info("Blue: {}", blue);

        // 遍历所有枚举值
        log.info("\nAll available colors:");
        for (ColorProto.Color color : ColorProto.Color.values()) {
            if (color != ColorProto.Color.UNRECOGNIZED) {
                log.info("- {} ({})", color.name(), color.getNumber());
            }
        }
    }
}
