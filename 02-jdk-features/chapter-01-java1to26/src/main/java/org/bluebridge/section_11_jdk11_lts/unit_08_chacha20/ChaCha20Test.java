package org.bluebridge.section_11_jdk11_lts.unit_08_chacha20;

import org.junit.Test;

import javax.crypto.AEADBadTagException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.ChaCha20ParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

/**
 * JDK 11 ChaCha20/ChaCha20-Poly1305 加密算法测试(STANDARD 正式特性)
 *
 * ChaCha20/ChaCha20-Poly1305 (JEP 329) 是 JDK 11 引入的正式特性, 是 Google 设计
 * 的流密码 ChaCha20 及基于它的认证加密算法 ChaCha20-Poly1305 的标准化实现。
 * 在缺少 AES 硬件加速指令的环境下, ChaCha20 通常比 AES 有更好的性能。
 *
 * 本类演示以下 STANDARD 正式特性:
 *   1. ChaCha20 流密码(Cipher.getInstance("ChaCha20"))
 *   2. ChaCha20-Poly1305 认证加密(Cipher.getInstance("ChaCha20-Poly1305"))
 *   3. 篡改密文后 ChaCha20-Poly1305 认证失败
 *
 * 演化历程: ChaCha20/ChaCha20-Poly1305 JDK 11 STANDARD(JEP 329)
 *
 * @author lingwh
 * @date 2026/08/18 09:10
 */
public class ChaCha20Test {

    /**
     * 测试 ChaCha20 流密码加解密(STANDARD)
     * ChaCha20 是对称流密码, 需要 32 字节(256 位)密钥、12 字节随机数(Nonce)和初始计数器
     */
    @Test
    public void testChaCha20StreamCipher() throws Exception {
        // 生成 256 位密钥
        KeyGenerator keyGenerator = KeyGenerator.getInstance("ChaCha20");
        SecretKey key = keyGenerator.generateKey();

        // 生成 12 字节随机数
        byte[] nonce = new byte[12];
        new SecureRandom().nextBytes(nonce);

        // 初始计数器必须为 1
        int counter = 1;

        String plainText = "Hello, ChaCha20 Stream Cipher!";
        byte[] plainBytes = plainText.getBytes();

        // 加密
        Cipher encryptCipher = Cipher.getInstance("ChaCha20");
        encryptCipher.init(Cipher.ENCRYPT_MODE, key, new ChaCha20ParameterSpec(nonce, counter));
        byte[] cipherText = encryptCipher.doFinal(plainBytes);

        // 解密: 使用相同的密钥、随机数和计数器
        Cipher decryptCipher = Cipher.getInstance("ChaCha20");
        decryptCipher.init(Cipher.DECRYPT_MODE, key, new ChaCha20ParameterSpec(nonce, counter));
        byte[] decryptedText = decryptCipher.doFinal(cipherText);

        System.out.println("ChaCha20 流密码测试:");
        System.out.println("  原始明文: " + plainText);
        System.out.println("  密钥长度: " + key.getEncoded().length * 8 + " bit");
        System.out.println("  随机数(Base64): " + Base64.getEncoder().encodeToString(nonce));
        System.out.println("  密文长度: " + cipherText.length + " 字节(与明文等长)");
        System.out.println("  解密结果: " + new String(decryptedText));
        System.out.println("  加解密一致: " + Arrays.equals(plainBytes, decryptedText));
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试 ChaCha20-Poly1305 认证加密加解密(STANDARD)
     * ChaCha20-Poly1305 是 AEAD 算法, 加密的同时生成认证标签, 可检测密文是否被篡改,
     * 与 AES-GCM 用法类似, 还支持关联数据(AAD)
     */
    @Test
    public void testChaCha20Poly1305AEAD() throws Exception {
        // 生成密钥
        KeyGenerator keyGenerator = KeyGenerator.getInstance("ChaCha20");
        SecretKey key = keyGenerator.generateKey();

        // 12 字节随机数
        byte[] nonce = new byte[12];
        new SecureRandom().nextBytes(nonce);

        // 关联数据(不加密但参与认证, 例如协议头)
        byte[] aad = "protocol-header-v1".getBytes();

        String plainText = "Hello, ChaCha20-Poly1305 AEAD!";
        byte[] plainBytes = plainText.getBytes();

        // 加密
        Cipher encryptCipher = Cipher.getInstance("ChaCha20-Poly1305");
        encryptCipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(nonce));
        encryptCipher.updateAAD(aad);
        byte[] cipherText = encryptCipher.doFinal(plainBytes);

        // 解密
        Cipher decryptCipher = Cipher.getInstance("ChaCha20-Poly1305");
        decryptCipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(nonce));
        decryptCipher.updateAAD(aad);
        byte[] decryptedText = decryptCipher.doFinal(cipherText);

        System.out.println("ChaCha20-Poly1305 认证加密测试:");
        System.out.println("  原始明文: " + plainText);
        System.out.println("  关联数据: " + new String(aad));
        System.out.println("  密文长度: " + cipherText.length + " 字节(明文 + 16 字节认证标签)");
        System.out.println("  解密结果: " + new String(decryptedText));
        System.out.println("  加解密一致: " + Arrays.equals(plainBytes, decryptedText));
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试 ChaCha20-Poly1305 篡改检测(STANDARD)
     * 修改密文或关联数据后解密会抛出 AEADBadTagException, 实现数据的完整性保护
     */
    @Test
    public void testChaCha20Poly1305TamperDetection() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("ChaCha20");
        SecretKey key = keyGenerator.generateKey();

        byte[] nonce = new byte[12];
        new SecureRandom().nextBytes(nonce);

        byte[] aad = "header".getBytes();
        byte[] plainBytes = "Secret message that must not be tampered.".getBytes();

        // 加密
        Cipher encryptCipher = Cipher.getInstance("ChaCha20-Poly1305");
        encryptCipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(nonce));
        encryptCipher.updateAAD(aad);
        byte[] cipherText = encryptCipher.doFinal(plainBytes);

        // 篡改密文中的一个字节
        byte[] tampered = Arrays.copyOf(cipherText, cipherText.length);
        tampered[0] ^= 0x01;

        // 正常解密(对照组)
        Cipher normalCipher = Cipher.getInstance("ChaCha20-Poly1305");
        normalCipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(nonce));
        normalCipher.updateAAD(aad);
        byte[] normalText = normalCipher.doFinal(cipherText);

        System.out.println("ChaCha20-Poly1305 篡改检测测试:");
        System.out.println("  正常解密: " + new String(normalText));

        // 篡改后解密(应失败)
        try {
            Cipher tamperCipher = Cipher.getInstance("ChaCha20-Poly1305");
            tamperCipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(nonce));
            tamperCipher.updateAAD(aad);
            byte[] tamperedText = tamperCipher.doFinal(tampered);
            System.out.println("  篡改后解密(不应走到这里): " + new String(tamperedText));
        } catch (AEADBadTagException e) {
            System.out.println("  篡改后解密被拒绝: AEADBadTagException(认证标签校验失败)");
        }
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试 ChaCha20-Poly1305 关联数据(AAD)篡改检测(STANDARD)
     * 修改 AAD 同样会导致认证失败, 保证头部等元数据不被篡改
     */
    @Test
    public void testChaCha20Poly1305AADTamper() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("ChaCha20");
        SecretKey key = keyGenerator.generateKey();

        byte[] nonce = new byte[12];
        new SecureRandom().nextBytes(nonce);

        byte[] aad = "header-v1".getBytes();
        byte[] plainBytes = "payload".getBytes();

        // 加密
        Cipher encryptCipher = Cipher.getInstance("ChaCha20-Poly1305");
        encryptCipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(nonce));
        encryptCipher.updateAAD(aad);
        byte[] cipherText = encryptCipher.doFinal(plainBytes);

        // 使用被篡改的 AAD 解密(应失败)
        try {
            Cipher tamperCipher = Cipher.getInstance("ChaCha20-Poly1305");
            tamperCipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(nonce));
            tamperCipher.updateAAD("header-v2".getBytes());
            tamperCipher.doFinal(cipherText);
            System.out.println("  AAD 被篡改后解密(不应走到这里)");
        } catch (AEADBadTagException e) {
            System.out.println("AAD 篡改检测: 修改关联数据后解密被拒绝(AEADBadTagException)");
        }
        System.out.println("--- 分割线 ---");
    }
}
