package org.bluebridge.section_25_jdk25.unit_08_kem;

import org.junit.Test;

import javax.crypto.KEM;
import javax.crypto.SecretKey;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.util.Base64;

/**
 * JDK 25 KEM 密钥封装机制 API 测试(STANDARD 正式特性)
 *
 * 密钥封装机制 API(Key Encapsulation Mechanism API, JEP 452) 在 JDK 25 中转正为
 * STANDARD 正式特性, 位于 javax.crypto 包下, 提供了标准的密钥封装/解封装接口,
 * 常与 JDK 24 引入的量子安全算法 ML-KEM(JEP 496) 配合, 用于后量子安全的密钥协商。
 *
 * KEM 工作流程:
 *   1. 接收方生成密钥对(KeyPairGenerator), 将公钥发送给发送方
 *   2. 发送方 newEncapsulator(公钥) 封装: 生成一个共享密钥 + 封装消息(密文)
 *   3. 发送方将封装消息发送给接收方
 *   4. 接收方 newDecapsulator(私钥).decapsulate(封装消息) 解封装, 得到相同的共享密钥
 *
 * 核心 API:
 *   1. KEM.getInstance(alg): 获取 KEM 实例
 *   2. kem.newEncapsulator(publicKey): 创建封装器(发送方)
 *   3. kem.newDecapsulator(privateKey): 创建解封装器(接收方)
 *   4. KEM.Encapsulated: 封装结果, 包含 key()(共享密钥)和 encapsulation()(封装消息)
 *
 * 演化历程: KEM API JDK 21(JEP 452, 1st PREVIEW) → JDK 25(STANDARD); ML-KEM 算法 JDK 24(JEP 496, STANDARD)
 *
 * @author lingwh
 * @date 2026/08/18 09:10
 */
public class KEMTest {

    /**
     * 测试 ML-KEM-768 密钥封装与解封装(STANDARD)
     * 完整的封装/解封装流程, 双方应得到相同的共享密钥
     */
    @Test
    public void testMLKEM768EncapsulateDecapsulate() throws Exception {
        // ===== 旧版实现方式(JDK 21 之前): 密钥协商只能使用 ECDH 等传统算法 =====
        // KeyPairGenerator kpg = KeyPairGenerator.getInstance("X25519");
        // KeyAgreement ka = KeyAgreement.getInstance("X25519");
        // ...(传统 ECDH 密钥协商, 无法抵御量子攻击)
        // ===== 新版实现方式(JDK 25 起): KEM API + ML-KEM 量子安全算法 =====
        // 接收方生成 ML-KEM-768 密钥对
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("ML-KEM-768");
        KeyPair receiverKeyPair = kpg.generateKeyPair();

        // 获取 KEM 实例
        KEM kem = KEM.getInstance("ML-KEM-768");

        // 发送方: 使用接收方公钥进行封装, 得到共享密钥和封装消息
        KEM.Encapsulator encapsulator = kem.newEncapsulator(receiverKeyPair.getPublic());
        KEM.Encapsulated encapsulated = encapsulator.encapsulate();
        SecretKey senderSecret = encapsulated.key();
        byte[] encapsulation = encapsulated.encapsulation();

        // 接收方: 使用自己的私钥和封装消息进行解封装, 得到相同的共享密钥
        KEM.Decapsulator decapsulator = kem.newDecapsulator(receiverKeyPair.getPrivate());
        SecretKey receiverSecret = decapsulator.decapsulate(encapsulation);

        System.out.println("ML-KEM-768 密钥封装测试:");
        System.out.println("  发送方共享密钥长度: " + senderSecret.getEncoded().length * 8 + " bit");
        System.out.println("  接收方共享密钥长度: " + receiverSecret.getEncoded().length * 8 + " bit");
        System.out.println("  封装消息长度: " + encapsulation.length + " 字节");
        System.out.println("  封装消息(Base64): " + Base64.getEncoder().encodeToString(encapsulation));
        System.out.println("  双方共享密钥一致: " + MessageDigest.isEqual(senderSecret.getEncoded(), receiverSecret.getEncoded()));
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试 ML-KEM-512 密钥封装与解封装(STANDARD)
     * ML-KEM-512 提供 128 位安全级别, 密钥和封装消息相对较小
     */
    @Test
    public void testMLKEM512EncapsulateDecapsulate() throws Exception {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("ML-KEM-512");
        KeyPair receiverKeyPair = kpg.generateKeyPair();

        KEM kem = KEM.getInstance("ML-KEM-512");
        KEM.Encapsulated encapsulated = kem.newEncapsulator(receiverKeyPair.getPublic()).encapsulate();
        SecretKey receiverSecret = kem.newDecapsulator(receiverKeyPair.getPrivate())
                .decapsulate(encapsulated.encapsulation());

        System.out.println("ML-KEM-512 密钥封装测试:");
        System.out.println("  共享密钥长度: " + receiverSecret.getEncoded().length * 8 + " bit");
        System.out.println("  封装消息长度: " + encapsulated.encapsulation().length + " 字节");
        System.out.println("  解封装成功, 共享密钥(Base64): " + Base64.getEncoder().encodeToString(receiverSecret.getEncoded()));
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试 ML-KEM-1024 密钥封装与解封装(STANDARD)
     * ML-KEM-1024 提供 256 位安全级别, 适合高安全需求的场景
     */
    @Test
    public void testMLKEM1024EncapsulateDecapsulate() throws Exception {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("ML-KEM-1024");
        KeyPair receiverKeyPair = kpg.generateKeyPair();

        KEM kem = KEM.getInstance("ML-KEM-1024");
        KEM.Encapsulated encapsulated = kem.newEncapsulator(receiverKeyPair.getPublic()).encapsulate();
        SecretKey senderSecret = encapsulated.key();
        SecretKey receiverSecret = kem.newDecapsulator(receiverKeyPair.getPrivate())
                .decapsulate(encapsulated.encapsulation());

        System.out.println("ML-KEM-1024 密钥封装测试:");
        System.out.println("  公钥长度: " + receiverKeyPair.getPublic().getEncoded().length + " 字节");
        System.out.println("  私钥长度: " + receiverKeyPair.getPrivate().getEncoded().length + " 字节");
        System.out.println("  封装消息长度: " + encapsulated.encapsulation().length + " 字节");
        System.out.println("  双方共享密钥一致: " + MessageDigest.isEqual(senderSecret.getEncoded(), receiverSecret.getEncoded()));
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试 KEM 的 DHKEM 传统算法(STANDARD)
     * DHKEM(RFC 9180) 是基于 Diffie-Hellman 的 KEM 实现, 使用 X25519 密钥
     */
    @Test
    public void testDHKEMEncapsulateDecapsulate() throws Exception {
        // 生成 X25519 密钥对
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("X25519");
        KeyPair receiverKeyPair = kpg.generateKeyPair();

        // DHKEM: Diffie-Hellman 密钥封装(基于 RFC 9180)
        KEM kem = KEM.getInstance("DHKEM");
        KEM.Encapsulated encapsulated = kem.newEncapsulator(receiverKeyPair.getPublic()).encapsulate();
        SecretKey senderSecret = encapsulated.key();
        SecretKey receiverSecret = kem.newDecapsulator(receiverKeyPair.getPrivate())
                .decapsulate(encapsulated.encapsulation());

        System.out.println("DHKEM(X25519) 密钥封装测试:");
        System.out.println("  封装消息长度: " + encapsulated.encapsulation().length + " 字节");
        System.out.println("  双方共享密钥一致: " + MessageDigest.isEqual(senderSecret.getEncoded(), receiverSecret.getEncoded()));
        System.out.println("--- 分割线 ---");
    }
}
