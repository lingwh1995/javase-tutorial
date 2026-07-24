package org.bluebridge.utils;

import cn.hutool.core.util.HexUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;

/**
 * 命令工具类
 *
 * @author lingwh
 * @date 2025/9/16 10:30
 */
@Data
@AllArgsConstructor
public class CommandUtil {

    /**
     * 主密钥
     */
    private String mainSecret;

    /**
     * 随机通信码
     */
    private String randomCode;

    /**
     * 数据区
     */
    private String dataAreaHex;

    /**
     * 是否加密数据区
     */
    private boolean isEncryp;

    /**
     * 是否计算 mac 并添加 mac 到数据区尾部
     */
    private boolean withMac;

    /**
     * 构建数据区
     *
     * @return
     * @throws Exception
     */
    public final String buildFinalDataArea() throws Exception {
        byte[] dataAreaBytes = buildDataArea();
        // 如果需要补全数据区则进行以下处理
        if (isPaddingDataArea()) {
            dataAreaBytes = paddingDataArea(dataAreaBytes);
        }
        // 如果需要加密数据区则进行以下处理
        if (isEncryp()) {
            dataAreaBytes = encryptDataArea(dataAreaBytes);
        }
        // 如果计算 mac 并将计算结果拼接到数据区尾部
        if (withMac()) {
            dataAreaBytes = calcMacAndAppendMacToDataAreaTail(dataAreaBytes);
        }
        return HexUtil.encodeHexStr(dataAreaBytes);
    }

    /**
     * 构建命令明文数据区
     *
     * @return 数据区
     */
    protected byte[] buildDataArea() {
        return HexUtil.decodeHex(this.dataAreaHex);
    }

    /**
     * 补齐数据区 需要 16 字节补齐，报文长度少于 16 个字节，需要补满 16 个字节，补 (16-len) 个 (16-len)。
     * 如果报文长度正好时 N 字节的整数倍，则需要补 16 个十进制 16。
     *
     * @param dataArea 没有补齐的数据区
     * @return 补齐后的数据区
     */
    protected byte[] paddingDataArea(byte[] dataArea) {
        byte[] paddingDataArea;
        int paddingNumber = dataArea.length % 16;
        if (paddingNumber == 0) {
            int length = dataArea.length + 16;
            paddingDataArea = new byte[length];
            System.arraycopy(dataArea, 0, paddingDataArea, 0, dataArea.length);
            for (int i = dataArea.length; i < paddingDataArea.length; i++) {
                paddingDataArea[i] = (byte) 16;
            }
        } else {
            int value = 16 - paddingNumber;
            paddingDataArea = new byte[dataArea.length + value];
            System.arraycopy(dataArea, 0, paddingDataArea, 0, dataArea.length);
            for (int i = dataArea.length; i < paddingDataArea.length; i++) {
                paddingDataArea[i] = (byte) value;
            }
        }
        return paddingDataArea;
    }

    /**
     * 加密数据区
     *
     * @param paddingDataArea 补齐后的数据区
     * @return 加密后的数据区
     * @throws Exception
     */
    protected byte[] encryptDataArea(byte[] paddingDataArea) throws Exception {
        byte[] mainSecretBytes = ParseUtil.hexStringToByte(mainSecret);
        return AESUtil.encrypt(paddingDataArea, mainSecretBytes);
    }

    /**
     * 计算 mac 并且把计算后的 mac值拼接到数据区尾部
     *
     * @param encryptDataArea 加密后的数据区
     * @return 可以直接用于组包的数据区
     * @throws Exception
     */
    protected byte[] calcMacAndAppendMacToDataAreaTail(byte[] encryptDataArea) throws Exception {
        byte[] randomCommunicationCodeBytes = ParseUtil.hexStringToByte(randomCode);
        byte[] mainSecretBytes = ParseUtil.hexStringToByte(mainSecret);
        // 使用主密钥对随机通信码进行 AES128 计算取前 16 字节得到加密密钥
        byte[] secretKeyBytes = AESUtil.encrypt(randomCommunicationCodeBytes, mainSecretBytes);
        secretKeyBytes = secretKeyBytes.length > 16 ? Arrays.copyOfRange(secretKeyBytes, 0, 16) : secretKeyBytes;

        // 1.在数据对象前面添加随机通信码
        byte[] calcDataAreaBytes = new byte[randomCommunicationCodeBytes.length + encryptDataArea.length];
        System.arraycopy(randomCommunicationCodeBytes, 0, calcDataAreaBytes, 0, randomCommunicationCodeBytes.length);
        System.arraycopy(encryptDataArea, 0, calcDataAreaBytes, randomCommunicationCodeBytes.length,
                encryptDataArea.length);
        // 2.经过 MAC 算法计算出 MAC 值
        byte[] mac = HmacUtil.hmacsha256Encrypt(calcDataAreaBytes, secretKeyBytes);
        // 3.最后将 MAC 追加在数据对象内容后作为数据域。
        byte[] finalDataArea = new byte[encryptDataArea.length + mac.length];
        System.arraycopy(encryptDataArea, 0, finalDataArea, 0, encryptDataArea.length);
        System.arraycopy(mac, 0, finalDataArea, encryptDataArea.length, mac.length);
        return finalDataArea;
    }

    /**
     * 是否补全数据区
     *
     * @return
     */
    protected boolean isPaddingDataArea() {
        return true;
    }

    /**
     * 是否加密数据区
     *
     * @return
     */
    protected boolean isEncryp() {
        return this.isEncryp;
    }

    /**
     * 是否计算 mac 并将计算结果拼接到数据区尾部
     *
     * @return
     */
    protected boolean withMac() {
        return this.withMac;
    }

    /**
     * 解析命令的方法（把加密报文中的数据区以名文形式解析处理）
     *
     * @return
     * @throws Exception
     */
    public String parseDataArea() throws Exception {
        byte[] dataAreaBytes = HexUtil.decodeHex(this.dataAreaHex);
        if (withMac()) {
            // 移除数据区中的 mac 部分
            dataAreaBytes = Arrays.copyOfRange(dataAreaBytes, 0, dataAreaBytes.length - 32);
        }
        byte[] mainSecretBytes = ParseUtil.hexStringToByte(mainSecret);
        // 解密数据区
        if (isEncryp()) {
            dataAreaBytes = AESUtil.decrypt(dataAreaBytes, mainSecretBytes);
        }
        return HexUtil.encodeHexStr(dataAreaBytes);
    }
}
