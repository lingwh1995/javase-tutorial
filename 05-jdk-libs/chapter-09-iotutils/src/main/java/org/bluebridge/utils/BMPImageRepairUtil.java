package org.bluebridge.utils;

import cn.hutool.core.io.FileUtil;
import java.io.*;
import java.util.Arrays;

/**
 * @author lingwh
 * @desc 修复bmp图片文件的header
 * @date 2025/9/16 14:06
 */
public class BMPImageRepairUtil {

    public static void main(String[] args) throws FileNotFoundException {
        String path = "d:/images";
        // path = "/mnt/images";
        File file = new File(path);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            // 应该分别处理读取和写入，避免同时操作同一文件
            for (File f : files) {
                try (InputStream fis = new FileInputStream(f)) {
                    long fileSize = f.length();
                    byte[] buffer = new byte[Math.toIntExact(fileSize)];
                    fis.read(buffer);

                    // 创建修复后的BMP文件
                    byte[] bmpFile =
                            BmpHeaderUtil.createBmpFile(Arrays.copyOfRange(buffer, 54, buffer.length), 320, 100);
                    FileUtil.writeBytes(bmpFile, f.getAbsolutePath());
                    System.out.println("修复文件：" + f.getName());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
