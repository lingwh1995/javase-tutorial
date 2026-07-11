package org.bluebridge.utils;

import cn.hutool.core.util.HexUtil;
import java.util.Base64;

/**
 * @author lingwh
 * @desc Base64工具类
 * @date 2026/7/9 00:00
 */
public class Base64Util {

    /**
     * Base64转字符串
     *
     * @param base64Message
     * @return
     */
    public static String base64ToString(String base64Message) {
        return HexUtil.encodeHexStr(Base64.getDecoder().decode(base64Message));
    }

    /**
     * String转Base64字符串
     *
     * @param stringMessage
     * @return
     */
    public static String stringToBase64(String stringMessage) {
        return new String(Base64.getEncoder().encode(HexUtil.decodeHex(stringMessage)));
    }
}
