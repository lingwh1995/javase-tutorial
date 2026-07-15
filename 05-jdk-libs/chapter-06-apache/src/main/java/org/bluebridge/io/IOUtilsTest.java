package org.bluebridge.io;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.*;

/**
 * IOUtils工具类测试
 *
 * @author lingwh
 * @date 2025/9/15 11:34
 */
@Slf4j
public class IOUtilsTest {

    /**
     * 从输入流中读取数据 + 向输出流中写入数据
     */
    @Test
    public void testReadFromInputStreamAndWriteToOutputStream() throws IOException {
        // 从InputStream读取内容到字符串
        String content = IOUtils.toString(new FileInputStream("d:/io_utils/io_utils_read.txt"), "UTF-8");
        log.info("content：{}", content);
        // 将字符串写入OutputStream
        IOUtils.write("你是一只小青蛙，呱呱呱呱！", new FileOutputStream("d:/io_utils/io_utils_write.txt"), "UTF-8");
    }

    /**
     * 安全的关闭流
     *
     * @throws FileNotFoundException
     */
    @Test
    public void testCloseQuietly() throws FileNotFoundException {
        // 安全地关闭流
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("d:/io_utils/io_utils_read.txt");
            // 处理流
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
    }
}
