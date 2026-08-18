package org.bluebridge.section_25_jdk25.unit_07_kdf;

import org.junit.Test;

import javax.crypto.KDF;
import javax.crypto.SecretKey;
import javax.crypto.spec.HKDFParameterSpec;
import java.util.Arrays;
import java.util.Base64;

/**
 * JDK 25 密钥派生函数 API 测试(STANDARD 正式特性)
 *
 * 密钥派生函数 API(Key Derivation Function API, JEP 510) 是 JDK 25 转正的
 * STANDARD 正式特性, 位于 javax.crypto 包下, 提供了从密钥材料(如口令、共享密钥)
 * 派生出其他密钥(如 AES 密钥)的标准 API, 取代了此前只能依赖 BouncyCastle 等
 * 第三方库或手动实现 HKDF 的方式。
 *
 * 核心 API:
 *   1. KDF.getInstance("HKDF-SHA256"): 获取 KDF 实例
 *   2. kdf.deriveKey(algorithm, params): 根据参数派生密钥
 *   3. HKDFParameterSpec.ofExtract().addIKM(...).addSalt(...).thenExpand(...): 一次
 *      完成 HKDF 的 Extract-then-Expand 两阶段派生
 *   4. HKDFParameterSpec.expandOnly(...): 仅执行 Expand 阶段
 *
 * 演化历程: KDF API JDK 24(JEP 478, 1st PREVIEW) → JDK 25(JEP 510, STANDARD)
 *
 * @author lingwh
 * @date 2026/08/18 09:10
 */
public class KDFTest {

    /**
     * 测试 KDF 使用 HKDF-SHA256 派生密钥(STANDARD)
     * 通过 Extract-then-Expand 一步完成: 输入密钥材料(IKM) + 盐(salt) + 上下文信息(info)
     */
    @Test
    public void testKDFDeriveKey() throws Exception {
        // ===== 旧版实现方式(JDK 25 之前): 只能手动实现 HKDF 或依赖 BouncyCastle 第三方库 =====
        // 例如 BouncyCastle: new HKDFBytesGenerator(new HMac(new SHA256Digest())), 步骤繁琐
        // ===== 新版实现方式(JDK 25 起): 使用 KDF API 一行配置即可 =====
        // 输入密钥材料(如握手后的共享密钥)
        byte[] ikm = "initial-key-material".getBytes();
        // 盐(随机值, 增强派生结果的安全性)
        byte[] salt = "hkdf-salt".getBytes();
        // 上下文信息(用于区分不同用途的派生密钥)
        byte[] info = "aes-256-encryption-key".getBytes();

        // 获取 KDF 实例
        KDF kdf = KDF.getInstance("HKDF-SHA256");
        // 配置 HKDF 参数: Extract(用 IKM + Salt 生成 PRK) 再 Expand(生成 32 字节密钥)
        HKDFParameterSpec spec = HKDFParameterSpec.ofExtract()
                .addIKM(ikm)
                .addSalt(salt)
                .thenExpand(info, 32);

        // 派生一个 32 字节的 AES 密钥
        SecretKey aesKey = kdf.deriveKey("AES", spec);

        System.out.println("KDF HKDF-SHA256 派生密钥测试:");
        System.out.println("  派生算法: " + aesKey.getAlgorithm());
        System.out.println("  派生密钥长度: " + aesKey.getEncoded().length * 8 + " bit");
        System.out.println("  派生密钥(Base64): " + Base64.getEncoder().encodeToString(aesKey.getEncoded()));
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试 KDF 分阶段 Extract 与 Expand(STANDARD)
     * 先执行 Extract 得到伪随机密钥 PRK, 再基于 PRK 执行 Expand 派生出多个子密钥
     */
    @Test
    public void testKDFExtractThenExpand() throws Exception {
        byte[] ikm = "shared-secret-from-handshake".getBytes();
        byte[] salt = "salt".getBytes();

        KDF kdf = KDF.getInstance("HKDF-SHA256");

        // 第一阶段: Extract, 得到伪随机密钥 PRK
        HKDFParameterSpec extractSpec = HKDFParameterSpec.ofExtract()
                .addIKM(ikm)
                .addSalt(salt)
                .extractOnly();
        SecretKey prk = kdf.deriveKey("HKDF", extractSpec);
        System.out.println("KDF Extract 阶段测试:");
        System.out.println("  PRK(Base64): " + Base64.getEncoder().encodeToString(prk.getEncoded()));

        // 第二阶段: 基于同一个 PRK 派生多个不同用途的密钥(客户端/服务端密钥)
        SecretKey clientKey = kdf.deriveKey("AES",
                HKDFParameterSpec.expandOnly(prk, "client-key".getBytes(), 32));
        SecretKey serverKey = kdf.deriveKey("AES",
                HKDFParameterSpec.expandOnly(prk, "server-key".getBytes(), 32));

        System.out.println("KDF Expand 阶段测试:");
        System.out.println("  客户端密钥(Base64): " + Base64.getEncoder().encodeToString(clientKey.getEncoded()));
        System.out.println("  服务端密钥(Base64): " + Base64.getEncoder().encodeToString(serverKey.getEncoded()));
        System.out.println("  两个用途的密钥是否不同: " + !Arrays.equals(clientKey.getEncoded(), serverKey.getEncoded()));
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试 KDF 派生密钥的确定性(STANDARD)
     * 相同参数派生结果一致, 保证通信双方可以派生出相同的密钥
     */
    @Test
    public void testKDFDeterministic() throws Exception {
        byte[] ikm = "same-ikm".getBytes();
        byte[] salt = "same-salt".getBytes();
        byte[] info = "same-info".getBytes();

        KDF kdf = KDF.getInstance("HKDF-SHA256");
        HKDFParameterSpec spec = HKDFParameterSpec.ofExtract()
                .addIKM(ikm)
                .addSalt(salt)
                .thenExpand(info, 32);

        // 双方使用完全相同的参数派生密钥
        SecretKey keyA = kdf.deriveKey("AES", spec);
        SecretKey keyB = kdf.deriveKey("AES", spec);

        System.out.println("KDF 派生确定性测试:");
        System.out.println("  密钥A(Base64): " + Base64.getEncoder().encodeToString(keyA.getEncoded()));
        System.out.println("  密钥B(Base64): " + Base64.getEncoder().encodeToString(keyB.getEncoded()));
        System.out.println("  两次派生结果一致: " + Arrays.equals(keyA.getEncoded(), keyB.getEncoded()));
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试 KDF 使用不同 info 派生不同密钥(STANDARD)
     * info 上下文信息用于隔离不同用途的密钥, 防止密钥复用
     */
    @Test
    public void testKDFDifferentInfo() throws Exception {
        byte[] ikm = "ikm".getBytes();
        byte[] salt = "salt".getBytes();

        KDF kdf = KDF.getInstance("HKDF-SHA256");

        // 相同 IKM 和 Salt, 仅 info 不同
        SecretKey keyForEncryption = kdf.deriveKey("AES",
                HKDFParameterSpec.ofExtract().addIKM(ikm).addSalt(salt).thenExpand("encryption".getBytes(), 32));
        SecretKey keyForSigning = kdf.deriveKey("AES",
                HKDFParameterSpec.ofExtract().addIKM(ikm).addSalt(salt).thenExpand("signing".getBytes(), 32));

        System.out.println("KDF info 隔离测试:");
        System.out.println("  加密密钥(Base64): " + Base64.getEncoder().encodeToString(keyForEncryption.getEncoded()));
        System.out.println("  签名密钥(Base64): " + Base64.getEncoder().encodeToString(keyForSigning.getEncoded()));
        System.out.println("  不同用途的密钥是否不同: " + !Arrays.equals(keyForEncryption.getEncoded(), keyForSigning.getEncoded()));
        System.out.println("--- 分割线 ---");
    }
}
