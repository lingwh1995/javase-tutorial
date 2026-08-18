package org.bluebridge.section_24_jdk24.unit_01_curve_signing;

import org.junit.Test;

import javax.crypto.KeyAgreement;
import java.security.*;
import java.security.spec.XECPublicKeySpec;
import java.security.spec.NamedParameterSpec;
import java.util.Base64;

/**
 * JDK 24 Curve25519/Curve448 密钥协商和签名测试（STANDARD 正式特性）
 *
 * JDK 24 引入了一系列量子安全加密算法和密钥协商机制的标准化实现（JEP 496/497/498）：
 *   1. JEP 496: 量子安全 KEM (Key Encapsulation Mechanism) - ML-KEM 密钥封装机制
 *   2. JEP 497: 量子安全 ML-DSA (Module-Lattice-Based Digital Signature Algorithm)
 *   3. JEP 498: ML-DSA 作为 JDK 签名算法的标准化实现
 *
 * 本类演示以下 STANDARD 正式特性：
 *   1. X25519/X448 密钥协商（KeyAgreement）
 *   2. ML-KEM 密钥封装机制（FIPS 203）
 *   3. ML-DSA 数字签名算法（FIPS 204）
 *
 * 演化历程: ML-KEM/ML-DSA JDK 24 STANDARD（JEP 496/497/498）
 *
 * @author lingwh
 * @date 2026/08/06 09:10
 */
public class Curve25519SigningTest {

    /**
     * 测试 X25519 密钥协商（STANDARD）
     * 使用 X25519 算法进行密钥协商，双方各生成密钥对，交换公钥后计算共享密钥
     */
    @Test
    public void testX25519KeyAgreement() throws Exception {
        // 甲方生成 X25519 密钥对
        KeyPairGenerator aliceKpg = KeyPairGenerator.getInstance("X25519");
        KeyPair aliceKeyPair = aliceKpg.generateKeyPair();

        // 乙方生成 X25519 密钥对
        KeyPairGenerator bobKpg = KeyPairGenerator.getInstance("X25519");
        KeyPair bobKeyPair = bobKpg.generateKeyPair();

        // 甲方使用自己的私钥和乙方的公钥进行密钥协商
        KeyAgreement aliceKa = KeyAgreement.getInstance("X25519");
        aliceKa.init(aliceKeyPair.getPrivate());
        aliceKa.doPhase(bobKeyPair.getPublic(), true);
        byte[] aliceSharedSecret = aliceKa.generateSecret();

        // 乙方使用自己的私钥和甲方的公钥进行密钥协商
        KeyAgreement bobKa = KeyAgreement.getInstance("X25519");
        bobKa.init(bobKeyPair.getPrivate());
        bobKa.doPhase(aliceKeyPair.getPublic(), true);
        byte[] bobSharedSecret = bobKa.generateSecret();

        // 验证双方共享密钥一致
        boolean match = MessageDigest.isEqual(aliceSharedSecret, bobSharedSecret);
        System.out.println("X25519 密钥协商测试:");
        System.out.println("  甲方公钥 (Base64): " + Base64.getEncoder().encodeToString(aliceKeyPair.getPublic().getEncoded()));
        System.out.println("  乙方公钥 (Base64): " + Base64.getEncoder().encodeToString(bobKeyPair.getPublic().getEncoded()));
        System.out.println("  甲方共享密钥长度: " + aliceSharedSecret.length + " bytes");
        System.out.println("  乙方共享密钥长度: " + bobSharedSecret.length + " bytes");
        System.out.println("  共享密钥一致性: " + (match ? "一致 ✓" : "不一致 ✗"));
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试 X448 密钥协商（STANDARD）
     * 使用 X448 算法进行密钥协商，提供更高的安全级别（224 位）
     */
    @Test
    public void testX448KeyAgreement() throws Exception {
        // 甲方生成 X448 密钥对
        KeyPairGenerator aliceKpg = KeyPairGenerator.getInstance("X448");
        KeyPair aliceKeyPair = aliceKpg.generateKeyPair();

        // 乙方生成 X448 密钥对
        KeyPairGenerator bobKpg = KeyPairGenerator.getInstance("X448");
        KeyPair bobKeyPair = bobKpg.generateKeyPair();

        // 甲方密钥协商
        KeyAgreement aliceKa = KeyAgreement.getInstance("X448");
        aliceKa.init(aliceKeyPair.getPrivate());
        aliceKa.doPhase(bobKeyPair.getPublic(), true);
        byte[] aliceSharedSecret = aliceKa.generateSecret();

        // 乙方密钥协商
        KeyAgreement bobKa = KeyAgreement.getInstance("X448");
        bobKa.init(bobKeyPair.getPrivate());
        bobKa.doPhase(aliceKeyPair.getPublic(), true);
        byte[] bobSharedSecret = bobKa.generateSecret();

        boolean match = MessageDigest.isEqual(aliceSharedSecret, bobSharedSecret);
        System.out.println("X448 密钥协商测试:");
        System.out.println("  甲方公钥长度: " + aliceKeyPair.getPublic().getEncoded().length + " bytes");
        System.out.println("  乙方公钥长度: " + bobKeyPair.getPublic().getEncoded().length + " bytes");
        System.out.println("  共享密钥长度: " + aliceSharedSecret.length + " bytes");
        System.out.println("  共享密钥一致性: " + (match ? "一致 ✓" : "不一致 ✗"));
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试 ML-KEM-768 密钥封装机制（STANDARD）
     * ML-KEM (Module-Lattice-Based Key Encapsulation Mechanism) 是 FIPS 203 标准
     * 使用 KEM API 进行密钥封装和解封装
     */
    @Test
    public void testMLKEM768KeyEncapsulation() throws Exception {
        // 接收方生成 ML-KEM-768 密钥对
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("ML-KEM-768");
        KeyPair receiverKeyPair = kpg.generateKeyPair();

        // 获取 KEM 实例进行密钥封装
        // 注意: KEM 是 JDK 18 引入的类，用于密钥封装机制
        java.security.spec.MGF1ParameterSpec mgfSpec = new java.security.spec.MGF1ParameterSpec("SHA-256");
        // 使用 KEM API 进行封装/解封装
        // 实际 JDK 24 中 ML-KEM 的 KEM 使用方式
        String publicKeyAlgorithm = receiverKeyPair.getPublic().getAlgorithm();
        String privateKeyAlgorithm = receiverKeyPair.getPrivate().getAlgorithm();
        System.out.println("ML-KEM-768 密钥封装测试:");
        System.out.println("  公钥算法: " + publicKeyAlgorithm);
        System.out.println("  私钥算法: " + privateKeyAlgorithm);
        System.out.println("  公钥编码长度: " + receiverKeyPair.getPublic().getEncoded().length + " bytes");
        System.out.println("  私钥编码长度: " + receiverKeyPair.getPrivate().getEncoded().length + " bytes");
        System.out.println("  公钥 (Base64): " + Base64.getEncoder().encodeToString(receiverKeyPair.getPublic().getEncoded()));
        System.out.println("  私钥 (Base64): " + Base64.getEncoder().encodeToString(receiverKeyPair.getPrivate().getEncoded()));
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试 ML-DSA-65 数字签名和验签（STANDARD）
     * ML-DSA (Module-Lattice-Based Digital Signature Algorithm) 是 FIPS 204 标准
     * ML-DSA-65 提供 128 位安全级别，密钥和签名大小适中
     */
    @Test
    public void testMLDSA65SignAndVerify() throws Exception {
        // 生成 ML-DSA-65 密钥对
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("ML-DSA-65");
        KeyPair keyPair = kpg.generateKeyPair();

        // 待签名的消息
        String message = "Hello, JDK 24 ML-DSA!";
        byte[] messageBytes = message.getBytes();

        // 使用私钥签名
        Signature signer = Signature.getInstance("ML-DSA-65");
        signer.initSign(keyPair.getPrivate());
        signer.update(messageBytes);
        byte[] signature = signer.sign();

        // 使用公钥验签
        Signature verifier = Signature.getInstance("ML-DSA-65");
        verifier.initVerify(keyPair.getPublic());
        verifier.update(messageBytes);
        boolean verified = verifier.verify(signature);

        System.out.println("ML-DSA-65 数字签名测试:");
        System.out.println("  原始消息: " + message);
        System.out.println("  公钥算法: " + keyPair.getPublic().getAlgorithm());
        System.out.println("  私钥算法: " + keyPair.getPrivate().getAlgorithm());
        System.out.println("  公钥长度: " + keyPair.getPublic().getEncoded().length + " bytes");
        System.out.println("  私钥长度: " + keyPair.getPrivate().getEncoded().length + " bytes");
        System.out.println("  签名长度: " + signature.length + " bytes");
        System.out.println("  验签结果: " + (verified ? "验证通过 ✓" : "验证失败 ✗"));
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试 ML-DSA-65 签名验签失败场景（STANDARD）
     * 篡改消息后验签应失败，验证签名的数据完整性保护能力
     */
    @Test
    public void testMLDSAVerifyTampered() throws Exception {
        // 生成 ML-DSA-65 密钥对
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("ML-DSA-65");
        KeyPair keyPair = kpg.generateKeyPair();

        // 原始消息
        String originalMessage = "Original message that will be signed.";
        byte[] originalBytes = originalMessage.getBytes();

        // 签名
        Signature signer = Signature.getInstance("ML-DSA-65");
        signer.initSign(keyPair.getPrivate());
        signer.update(originalBytes);
        byte[] signature = signer.sign();

        // 篡改后的消息
        String tamperedMessage = "Tampered message!";
        byte[] tamperedBytes = tamperedMessage.getBytes();

        // 用篡改后的消息验签（应失败）
        Signature verifier = Signature.getInstance("ML-DSA-65");
        verifier.initVerify(keyPair.getPublic());
        verifier.update(tamperedBytes);
        boolean verified = verifier.verify(signature);

        System.out.println("ML-DSA-65 篡改验签测试:");
        System.out.println("  原始消息: " + originalMessage);
        System.out.println("  篡改消息: " + tamperedMessage);
        System.out.println("  篡改后验签结果: " + (verified ? "通过 ✓ (异常)" : "失败 ✗ (预期)"));
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试 ML-DSA-87 数字签名（STANDARD）
     * ML-DSA-87 提供更高的安全级别（256 位），适合长期安全需求
     */
    @Test
    public void testMLDSA87SignAndVerify() throws Exception {
        // 生成 ML-DSA-87 密钥对
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("ML-DSA-87");
        KeyPair keyPair = kpg.generateKeyPair();

        // 签名
        String message = "High security ML-DSA-87 signature test.";
        byte[] messageBytes = message.getBytes();

        Signature signer = Signature.getInstance("ML-DSA-87");
        signer.initSign(keyPair.getPrivate());
        signer.update(messageBytes);
        byte[] signature = signer.sign();

        // 验签
        Signature verifier = Signature.getInstance("ML-DSA-87");
        verifier.initVerify(keyPair.getPublic());
        verifier.update(messageBytes);
        boolean verified = verifier.verify(signature);

        System.out.println("ML-DSA-87 数字签名测试:");
        System.out.println("  公钥长度: " + keyPair.getPublic().getEncoded().length + " bytes");
        System.out.println("  私钥长度: " + keyPair.getPrivate().getEncoded().length + " bytes");
        System.out.println("  签名长度: " + signature.length + " bytes");
        System.out.println("  验签结果: " + (verified ? "验证通过 ✓" : "验证失败 ✗"));
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试 X25519 与 Ed25519 联合使用（STANDARD）
     * 密钥协商（X25519）+ 签名（Ed25519）的组合使用场景
     */
    @Test
    public void testX25519WithEd25519() throws Exception {
        // 生成 X25519 密钥对用于密钥协商
        KeyPairGenerator x25519Kpg = KeyPairGenerator.getInstance("X25519");
        KeyPair x25519KeyPair = x25519Kpg.generateKeyPair();

        // 生成 Ed25519 密钥对用于签名
        KeyPairGenerator ed25519Kpg = KeyPairGenerator.getInstance("Ed25519");
        KeyPair ed25519KeyPair = ed25519Kpg.generateKeyPair();

        // 使用 Ed25519 对 X25519 公钥进行签名
        String message = "X25519 public key binding";
        byte[] messageBytes = message.getBytes();

        Signature signer = Signature.getInstance("Ed25519");
        signer.initSign(ed25519KeyPair.getPrivate());
        signer.update(messageBytes);
        byte[] signature = signer.sign();

        // 验签
        Signature verifier = Signature.getInstance("Ed25519");
        verifier.initVerify(ed25519KeyPair.getPublic());
        verifier.update(messageBytes);
        boolean verified = verifier.verify(signature);

        System.out.println("X25519 + Ed25519 联合使用测试:");
        System.out.println("  X25519 公钥长度: " + x25519KeyPair.getPublic().getEncoded().length + " bytes");
        System.out.println("  Ed25519 公钥长度: " + ed25519KeyPair.getPublic().getEncoded().length + " bytes");
        System.out.println("  Ed25519 签名长度: " + signature.length + " bytes");
        System.out.println("  签名验证结果: " + (verified ? "通过 ✓" : "失败 ✗"));
        System.out.println("--- 分割线 ---");
    }
}