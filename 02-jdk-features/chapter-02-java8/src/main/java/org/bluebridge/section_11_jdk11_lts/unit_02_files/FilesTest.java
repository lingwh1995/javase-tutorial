package org.bluebridge.section_11_jdk11_lts.unit_02_files;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * JDK 11 Files 新方法测试
 *
 * @author lingwh
 * @date 2026/08/05 19:12
 */
public class FilesTest {

    /**
     * 测试 Files.writeString() 方法
     * 将字符串直接写入文件，无需转为 byte[]
     */
    @Test
    public void testWriteString() throws IOException {
        // 创建临时文件路径
        Path tempFile = Files.createTempFile("test_", ".txt");
        System.out.println("临时文件路径: " + tempFile.toAbsolutePath());

        // 写入字符串到文件
        String content = "Hello, JDK 11 Files.writeString!";
        Path writtenPath = Files.writeString(tempFile, content);
        System.out.println("文件已写入: " + writtenPath.toAbsolutePath());

        // 清理临时文件
        Files.deleteIfExists(tempFile);
    }

    /**
     * 测试 Files.readString() 方法
     * 将文件内容直接读取为字符串，无需手动处理编码
     */
    @Test
    public void testReadString() throws IOException {
        // 创建临时文件并写入内容
        Path tempFile = Files.createTempFile("test_", ".txt");
        String originalContent = "Hello, JDK 11 Files.readString!";
        Files.writeString(tempFile, originalContent);

        // 读取文件内容为字符串
        String readContent = Files.readString(tempFile);
        System.out.println("读取的文件内容: " + readContent);

        // 验证写入和读取的内容一致
        System.out.println("内容一致: " + originalContent.equals(readContent));

        // 清理临时文件
        Files.deleteIfExists(tempFile);
    }

    /**
     * 测试 Files.writeString() 和 Files.readString() 组合使用
     * 写入多行文本后再读取
     */
    @Test
    public void testWriteAndReadString() throws IOException {
        Path tempFile = Files.createTempFile("test_", ".txt");

        // 写入多行文本
        String multilineContent = "第一行\n第二行\n第三行";
        Files.writeString(tempFile, multilineContent);

        // 读取并验证
        String result = Files.readString(tempFile);
        System.out.println("写入的多行内容:");
        System.out.println(result);

        // 清理临时文件
        Files.deleteIfExists(tempFile);
    }
}