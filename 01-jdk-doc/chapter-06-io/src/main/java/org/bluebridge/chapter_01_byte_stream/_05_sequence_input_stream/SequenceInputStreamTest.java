package org.bluebridge.chapter_01_byte_stream._05_sequence_input_stream;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import org.junit.Test;

/**
 * @author lingwh
 * @desc 合并输入流/输入流的逻辑串联
 * @date 2025/8/16 15:14
 */
public class SequenceInputStreamTest {

    /**
     * 不借助枚举合并两个输入流/合并两个文件（注意:这种方式只能合并两个输入流）
     *
     * @throws IOException
     */
    @Test
    public void mergeInputStreamTest1() throws IOException {
        InputStream is1 = new FileInputStream("d:/io/part_1.txt");
        InputStream is2 = new FileInputStream("d:/io/part_2.txt");
        SequenceInputStream sis = new SequenceInputStream(is1, is2);

        // 创建输出流 - 要把前三个文件的内容读出来并且合并到.txt;
        FileOutputStream fos = new FileOutputStream("d:/io/merge_1.txt");
        int len = 0;
        byte buf[] = new byte[10];
        while ((len = sis.read(buf)) != -1) {
            fos.write(buf, 0, len);
        }
        fos.close();
        sis.close();
    }

    /**
     * 借助枚举合并三个输入流/合并三个文件为一个文件
     *
     * @throws IOException
     */
    @Test
    public void mergeInputStreamTest2() throws IOException {
        InputStream is1 = new FileInputStream("d:/io/part_1.txt");
        InputStream is2 = new FileInputStream("d:/io/part_2.txt");
        InputStream is3 = new FileInputStream("d:/io/part_3.txt");
        List<InputStream> iss = new ArrayList<>();
        iss.add(is1);
        iss.add(is2);
        iss.add(is3);
        Enumeration<InputStream> isse = Collections.enumeration(iss);
        SequenceInputStream fis = new SequenceInputStream(isse);

        // 创建输出流 - 要把前三个文件的内容读出来并且合并到.txt;
        FileOutputStream fos = new FileOutputStream("d:/io/merge_2.txt");
        int len = 0;
        byte buf[] = new byte[10];
        while ((len = fis.read(buf)) != -1) {
            fos.write(buf, 0, len);
        }
        fos.close();
        fis.close();

        // 记得要关闭所有的输入流
        for (InputStream is : iss) {
            is.close();
        }
    }
}
