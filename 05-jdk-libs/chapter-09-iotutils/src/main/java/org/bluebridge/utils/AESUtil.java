package org.bluebridge.utils;

import cn.hutool.crypto.symmetric.AES;

/**
 * @author lingwh
 * @desc AES加密算法 - 使用hutool封装过的实现
 *       <a* href="https://doc.hutool.cn/pages/SymmetricCrypto/#%E9%80%9A%E7%94%A8%E4%BD%BF%E7%94%A8">官方文档</a>
 * @date 2026/7/9 00:00
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
