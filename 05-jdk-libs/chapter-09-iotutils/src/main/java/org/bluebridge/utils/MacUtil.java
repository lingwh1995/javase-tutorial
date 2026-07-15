package org.bluebridge.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MAC消息摘要工具类
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class MacUtil {

    /**
     * 字符串 SHA256
     */
    public static byte[] SHA256(byte[] buff) {
        // 返回值
        byte[] byteBuffer;
        try {
            // SHA 加密开始
            // 创建加密对象 并傳入加密類型
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            // 传入要加密的字符串
            messageDigest.update(buff);
            // 得到 byte 類型结果
            byteBuffer = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        return byteBuffer;
    }
}
