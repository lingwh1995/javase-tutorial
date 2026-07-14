package org.bluebridge.utils;

import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.symmetric.DESede;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * 3DES加密工具类
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class DESedeUtil {

    /**
     * 3des加密
     *
     * @param sourceBytes 源
     * @return 加密后的密文
     */
    public static byte[] encrypt(byte[] sourceBytes, byte[] key) throws Exception {
        Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS7Padding");
        // Cipher cipher = Cipher.getInstance("DESede/ECB/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, "DESede"));
        return cipher.doFinal(sourceBytes);
    }

    /**
     * 3des解密
     *
     * @param sourceBytes 密文
     * @return 源字符串
     */
    public static byte[] decrypt(byte[] sourceBytes, byte[] key) throws Exception {
        Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS7Padding");
        // Cipher cipher = Cipher.getInstance("DESede/ECB/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, "DESede"));
        return cipher.doFinal(sourceBytes);
    }

    /**
     * 3des加密
     *
     * @param sourceBytes 源
     * @return 加密后的密文
     */
    public static byte[] encryptNoPadding(byte[] sourceBytes, byte[] key) throws Exception {
        Cipher cipher = Cipher.getInstance("DESede/ECB/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, "DESede"));
        return cipher.doFinal(sourceBytes);
    }

    /**
     * 3des解密
     *
     * @param sourceBytes 密文
     * @return 源字符串
     */
    public static byte[] decryptNoPadding(byte[] sourceBytes, byte[] key) throws Exception {
        Cipher cipher = Cipher.getInstance("DESede/ECB/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, "DESede"));
        return cipher.doFinal(sourceBytes);
    }

    public static void main(String[] args) throws Exception {
        // 待加密数据
        byte[] content = new byte[8];
        // 加密密钥
        byte[] key = new byte[24];

        // 使用hutool封装过的实现
        DESede des = new DESede("ECB", "PKCS7Padding", key);
        // 加密
        byte[] encrypt = des.encrypt(content);
        System.out.println(HexUtil.encodeHexStr(encrypt));
        // 解密
        byte[] decrypt = des.decrypt(encrypt);
        System.out.println(HexUtil.encodeHexStr(decrypt));

        // 使用jdk自带工具类实现
        // 加密
        byte[] encryptByJdk = encrypt(content, key);
        System.out.println(HexUtil.encodeHexStr(encryptByJdk));
        // 解密
        byte[] decryptByJdk = decrypt(encryptByJdk, key);
        System.out.println(HexUtil.encodeHexStr(decryptByJdk));
    }
}
