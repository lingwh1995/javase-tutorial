package org.bluebridge.java9.chapter_04_input_stream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.junit.Test;

/**
 * @author lingwh
 * @desc Java9输入流transferTo方法测试
 * @date 2026/7/9 00:00
 */
public class InputStreamTest {

    /**
     * java9 使用 transferTo() 直接将输入流转换为输出流
     *
     * @throws IOException
     */
    @Test
    public void testTransferTo() throws IOException {
        InputStream is = new FileInputStream("d://a.txt");
        FileOutputStream os = new FileOutputStream("d://b.txt");
        is.transferTo(os);
    }
}
