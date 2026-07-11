package org.bluebridge;

import cn.hutool.core.util.HexUtil;
import org.bluebridge.utils.PaddingUtil;

/**
 * @author lingwh
 * @desc 填充工具测试类
 * @date 2026/7/9 00:00
 */
public class PaddingUtilTest {

    public static void main(String[] args) {
        byte[] dataAreaBytes = HexUtil.decodeHex("00112233445566778899AABBCCDDEEFF");
        System.out.println("数组长度:" + dataAreaBytes.length);
        byte[] dataAreaBytesPadding = PaddingUtil.padding(dataAreaBytes);
        String dataArea = HexUtil.encodeHexStr(dataAreaBytesPadding);
        System.out.println(dataArea);
    }
}
