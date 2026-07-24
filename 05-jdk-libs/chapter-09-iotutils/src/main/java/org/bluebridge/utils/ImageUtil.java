package org.bluebridge.utils;

import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.stream.IntStream;

/**
 * 还原表端二进制数据为图片流程
 *
 * 1. 先 zip 解压 压缩以后的字节数组
 * 2. 将解压后的字节数组（1000 字节） 按 200 字节一组，分为五组
 * 3. 将 200 字节还原成 1600 字节
 * 4. 加上 bmp 头+1600 字节输出为 bmp 文件
 *
 * @author lingwh
 * @date 2025/9/16 10:30
 */
@Slf4j
public class ImageUtil {

    /**
     * 把一个 byte 转换为 8 个 bits，并且把这 8 个 bits 放入到一个数组中
     *
     * @param b
     * @return
     */
    private static byte[] transByteToBitsArray(byte b) {
        StringBuilder binaryString = new StringBuilder();
        for (int i = 7; i >= 0; i--) {
            binaryString.append((b >> i) & 1);
        }
        byte[] target = new byte[8];
        IntStream.range(0, 8).forEach(i -> {
            target[i] = (byte) Integer.parseInt(binaryString.substring(i, i + 1)) == 1 ? (byte) 255 : 0;
        });
        return target;
    }

    private static byte[] transImageBitsToBytes(byte[] src) {
        // 把 byte[] 数组转换为 bit 数组
        byte[] des = new byte[src.length * 8];
        for (int i = 0; i < src.length; i++) {
            byte[] trans = transByteToBitsArray(src[i]);
            for (int j = 0; j < 8; j++) {
                int index = i * 8 + j;
                des[index] = trans[j];
            }
        }
        return des;
    }

    /**
     * 将原始二进制数据转换为图片并输出
     *
     * @param source      原始数据
     * @param imagePrefix 图片保存位置（目录）
     * @param imageName   图像名称
     */
    public static void transferBytesToBmpImages(byte[] source, String imagePrefix, String imageName) {
        // 使用 gzip 算法解压原始数据
        // byte[] unGZipBytes = ZipUtils.unGZip(source);
        // 获取还原后的字节数组
        byte[] desBytes = transImageBitsToBytes(source);
        for (int i = 0; i < desBytes.length; i++) {
            if (i % 100 == 0 && i != 0) {
                System.out.println();
            }
            if (i % 100 <= 30) {
                desBytes[i] = 0;
            }
            System.out.print((desBytes[i] & 0xFF) + "\t");
        }
        // 把图片顺时针旋转 90 度
        desBytes = ImageRotationUtils.rotateImage(desBytes, 100, 320, 90);
        byte[] bmpBytes = BmpHeaderUtil.createBmpFile(desBytes, 320, 100);
        FileUtil.writeBytes(bmpBytes, imagePrefix + imageName);
        File file = new File(imagePrefix + imageName);
        file.setReadable(true, false);
    }

    /**
     * 将原始二进制数据转换为图片并输出
     *
     * @param source      原始数据
     * @param imagePrefix 图片保存位置（目录）
     * @param imageName   图像名称
     */
    public static void transferBytesToJpegImages(byte[] source, String imagePrefix, String imageName, int degrees) {
        // try {
        // source = ImageRotationUtils.rotateJpegByDegrees(source, degrees);
        // FileUtil.writeBytes(source, imagePrefix + imageName);
        // File file = new File(imagePrefix + imageName);
        // file.setReadable(true, false);
        // } catch (IOException e) {
        // throw new RuntimeException("JPEG图片旋转失败: " + e.getMessage(), e);
        // }
        FileUtil.writeBytes(source, imagePrefix + imageName);
        File file = new File(imagePrefix + imageName);
        file.setReadable(true, false);
    }
}
