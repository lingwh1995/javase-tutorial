package org.bluebridge.utils;

import cn.hutool.crypto.symmetric.AES;

/**
 * AES 加密算法
 *
 * 使用 hutool 封装过的实现 - https://doc.hutool.cn/pages/SymmetricCrypto/#%E9%80%9A%E7%94%A8%E4%BD%BF%E7%94%A8
 *
 * @author lingwh
 * @date 2025/9/16 10:30
 */
public class AESUtil {

    public static byte[] decrypt(byte[] data, byte[] key) {
        AES aes = new AES("ECB", "NoPadding", key);
        return aes.decrypt(data);
    }

    public static byte[] encrypt(byte[] data, byte[] key) {
        AES aes = new AES("ECB", "NoPadding", key);
        return aes.encrypt(data);
    }
}
