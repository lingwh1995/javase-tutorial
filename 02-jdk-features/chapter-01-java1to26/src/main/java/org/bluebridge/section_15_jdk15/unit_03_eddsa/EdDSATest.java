package org.bluebridge.section_15_jdk15.unit_03_eddsa;

import org.junit.Test;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;
import java.util.Base64;

/**
 * JDK 15 EdDSA 签名算法（STANDARD 特性，JEP 339）
 * EdDSA（Edwards-Curve Digital Signature Algorithm）是一种基于 Edwards 曲线的数字签名算法
 * JDK 15 引入 EdDSA 实现，支持 Ed25519 和 Ed448 算法
 *
 * 演化历程: EdDSA 签名 JDK 15 STANDARD（JEP 339）
 *
 * @author lingwh
 * @date 2026/08/06 02:18
 */
public class EdDSATest {

    /**
     * 测试使用 Ed25519 算法生成密钥对
     * Ed25519 是 EdDSA 在 Curve25519 曲线上的实现，提供 128 位安全级别
     */
    @Test
    public void testGenerateEd25519KeyPair() throws Exception {
        // 使用 Ed25519 算法生成密钥对
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("Ed25519");
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        // 打印公钥和私钥信息
        System.out.println("Ed25519 公钥算法: " + keyPair.getPublic().getAlgorithm());
        System.out.println("Ed25519 私钥算法: " + keyPair.getPrivate().getAlgorithm());
        System.out.println("Ed25519 公钥格式: " + keyPair.getPublic().getFormat());
        System.out.println("Ed25519 私钥格式: " + keyPair.getPrivate().getFormat());
        System.out.println("Ed25519 公钥编码长度: " + keyPair.getPublic().getEncoded().length + " bytes");
        System.out.println("Ed25519 私钥编码长度: " + keyPair.getPrivate().getEncoded().length + " bytes");
        System.out.println("公钥 (Base64): " + Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded()));
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试使用 Ed448 算法生成密钥对
     * Ed448 是 EdDSA 在 Curve448 曲线上的实现，提供 224 位安全级别
     */
    @Test
    public void testGenerateEd448KeyPair() throws Exception {
        // 使用 Ed448 算法生成密钥对
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("Ed448");
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        System.out.println("Ed448 公钥算法: " + keyPair.getPublic().getAlgorithm());
        System.out.println("Ed448 私钥算法: " + keyPair.getPrivate().getAlgorithm());
        System.out.println("Ed448 公钥编码长度: " + keyPair.getPublic().getEncoded().length + " bytes");
        System.out.println("Ed448 私钥编码长度: " + keyPair.getPrivate().getEncoded().length + " bytes");
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试 Ed25519 签名和验签完整流程
     */
    @Test
    public void testEd25519SignAndVerify() throws Exception {
        // 生成 Ed25519 密钥对
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("Ed25519");
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        // 待签名的原始消息
        String message = "Hello, JDK 15 EdDSA!";
        byte[] messageBytes = message.getBytes();

        // 使用私钥进行签名
        Signature signer = Signature.getInstance("Ed25519");
        signer.initSign(keyPair.getPrivate());
        signer.update(messageBytes);
        byte[] signature = signer.sign();

        System.out.println("原始消息: " + message);
        System.out.println("签名长度: " + signature.length + " bytes");
        System.out.println("签名 (Base64): " + Base64.getEncoder().encodeToString(signature));

        // 使用公钥进行验签
        Signature verifier = Signature.getInstance("Ed25519");
        verifier.initVerify(keyPair.getPublic());
        verifier.update(messageBytes);
        boolean verified = verifier.verify(signature);

        System.out.println("验签结果: " + (verified ? "验证通过 ✓" : "验证失败 ✗"));
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试 Ed448 签名和验签完整流程
     */
    @Test
    public void testEd448SignAndVerify() throws Exception {
        // 生成 Ed448 密钥对
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("Ed448");
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        // 待签名的原始消息
        String message = "EdDSA with Ed448 provides higher security level.";
        byte[] messageBytes = message.getBytes();

        // 使用私钥进行签名
        Signature signer = Signature.getInstance("Ed448");
        signer.initSign(keyPair.getPrivate());
        signer.update(messageBytes);
        byte[] signature = signer.sign();

        System.out.println("原始消息: " + message);
        System.out.println("Ed448 签名长度: " + signature.length + " bytes");

        // 使用公钥进行验签
        Signature verifier = Signature.getInstance("Ed448");
        verifier.initVerify(keyPair.getPublic());
        verifier.update(messageBytes);
        boolean verified = verifier.verify(signature);

        System.out.println("Ed448 验签结果: " + (verified ? "验证通过 ✓" : "验证失败 ✗"));
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试 ECDSA 与 EdDSA 签名算法对比
     * 展示 EdDSA 相比传统 ECDSA 的优势（更小更快的密钥生成）
     */
    @Test
    public void testCompareEdDSAByAlgorithmName() throws Exception {
        // 使用通用算法名称 "EdDSA" 获取 KeyPairGenerator
        KeyPairGenerator eddsaKeyGen = KeyPairGenerator.getInstance("EdDSA");
        // EdDSA 默认使用 Ed25519
        KeyPair eddsaKeyPair = eddsaKeyGen.generateKeyPair();

        // 使用 EdDSA 签名
        String data = "Comparison test between ECDSA and EdDSA.";
        byte[] dataBytes = data.getBytes();

        Signature eddsaSigner = Signature.getInstance("EdDSA");
        eddsaSigner.initSign(eddsaKeyPair.getPrivate());
        eddsaSigner.update(dataBytes);
        byte[] eddsaSignature = eddsaSigner.sign();

        // 验签
        Signature eddsaVerifier = Signature.getInstance("EdDSA");
        eddsaVerifier.initVerify(eddsaKeyPair.getPublic());
        eddsaVerifier.update(dataBytes);
        boolean eddsaVerified = eddsaVerifier.verify(eddsaSignature);

        System.out.println("EdDSA (通用名称) 算法: " + eddsaKeyPair.getPublic().getAlgorithm());
        System.out.println("EdDSA 公钥长度: " + eddsaKeyPair.getPublic().getEncoded().length + " bytes");
        System.out.println("EdDSA 签名长度: " + eddsaSignature.length + " bytes");
        System.out.println("EdDSA 验签结果: " + (eddsaVerified ? "通过 ✓" : "失败 ✗"));
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试 EdDSA 验签失败场景（篡改消息后验签应失败）
     */
    @Test
    public void testEdDSASignVerifyTampered() throws Exception {
        // 生成 Ed25519 密钥对
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("Ed25519");
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        // 原始消息
        String originalMessage = "Original message that will be signed.";
        byte[] originalBytes = originalMessage.getBytes();

        // 签名
        Signature signer = Signature.getInstance("Ed25519");
        signer.initSign(keyPair.getPrivate());
        signer.update(originalBytes);
        byte[] signature = signer.sign();

        // 篡改后的消息
        String tamperedMessage = "Tampered message!";
        byte[] tamperedBytes = tamperedMessage.getBytes();

        // 用篡改后的消息验签（应失败）
        Signature verifier = Signature.getInstance("Ed25519");
        verifier.initVerify(keyPair.getPublic());
        verifier.update(tamperedBytes);
        boolean verified = verifier.verify(signature);

        System.out.println("原始消息: " + originalMessage);
        System.out.println("篡改消息: " + tamperedMessage);
        System.out.println("篡改后验签结果: " + (verified ? "通过 ✓ (异常)" : "失败 ✗ (预期)"));
        System.out.println("--- 分割线 ---");
    }
}