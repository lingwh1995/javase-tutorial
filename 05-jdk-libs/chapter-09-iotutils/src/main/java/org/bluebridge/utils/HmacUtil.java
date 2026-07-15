package org.bluebridge.utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * HMAC工具类
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class HmacUtil {

    /**
     * HmacSHA256 散列消息认证码
     *
     * @param data   要进行加密的数据
     * @param secret 加密密钥
     * @return 加密后的字节数组
     * @throws Exception 异常
     */
    public static byte[] hmacsha256Encrypt(byte[] data, byte[] secret) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(secret, "HmacSHA256");
        mac.init(secretKeySpec);
        return mac.doFinal(data);
    }

    /**
     * HmacSHA1 散列消息认证码
     *
     * @param data   要进行加密的数据
     * @param secret 加密密钥
     * @return 加密后的字节数组
     * @throws Exception 异常
     */
    public static byte[] hmacsha1Encrypt(byte[] data, byte[] secret) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA1");
        SecretKeySpec secretKeySpec = new SecretKeySpec(secret, "HmacSHA256");
        mac.init(secretKeySpec);
        return mac.doFinal(data);
    }
}
