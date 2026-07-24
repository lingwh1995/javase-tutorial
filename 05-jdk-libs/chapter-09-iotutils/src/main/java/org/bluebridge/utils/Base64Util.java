package org.bluebridge.utils;

import cn.hutool.core.util.HexUtil;

import java.util.Base64;

/**
 * Base64 工具类
 *
 * @author lingwh
 * @date 2025/9/16 10:30
 */
public class Base64Util {

    /**
     * Base64 转字符串
     *
     * @param base64Message
     * @return
     */
    public static String base64ToString(String base64Message) {
        return HexUtil.encodeHexStr(Base64.getDecoder().decode(base64Message));
    }

    /**
     * String 转 Base64 字符串
     *
     * @param stringMessage
     * @return
     */
    public static String stringToBase64(String stringMessage) {
        return new String(Base64.getEncoder().encode(HexUtil.decodeHex(stringMessage)));
    }
}
