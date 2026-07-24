package org.bluebridge.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.stream.IntStream;
import java.util.zip.GZIPInputStream;

/**
 * 压缩解压工具类
 *
 * @author lingwh
 * @date 2025/9/16 10:30
 */
public class ZipUtils {

    /**
     * GZip 解压
     *
     * @param data
     * @return
     */
    public static byte[] unGZip(byte[] data) {
        byte[] b = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(data);
            GZIPInputStream gzip = new GZIPInputStream(bis);
            byte[] buf = new byte[1024];
            int num = -1;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((num = gzip.read(buf, 0, buf.length)) != -1) {
                baos.write(buf, 0, num);
            }
            b = baos.toByteArray();
            baos.flush();
            baos.close();
            gzip.close();
            bis.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return b;
    }

    /**
     * 将 int[] 转换为 byte[]
     *
     * @param intArray
     * @return
     */
    public static byte[] intArrToByteArr(int[] intArray) {
        byte[] bytes = new byte[intArray.length];
        IntStream.range(0, intArray.length).forEach(i -> bytes[i] = (byte) intArray[i]);
        return bytes;
    }
}
