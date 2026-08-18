package org.bluebridge.section_18_jdk18.unit_02_utf8;

import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * JDK 18 默认 UTF-8 字符集 (JEP 400) 测试
 *
 * JDK 18 将 UTF-8 指定为所有 Java API 的默认字符集。
 * 在此之前, 默认字符集取决于操作系统和用户区域设置,
 * 导致跨平台行为不一致。JEP 400 统一了默认字符集,
 * 使得 Java 程序的行为在不同平台上更加可预测。
 *
 * <p>演化历程: 默认 UTF-8 JDK 18 STANDARD（JEP 400）
 *
 * @author lingwh
 * @date 2026/08/06 02:19
 */
public class UTF8CharsetTest {

    /**
     * 验证 JDK 18 默认字符集为 UTF-8
     * Charset.defaultCharset() 应返回 UTF-8
     */
    @Test
    public void testDefaultCharsetIsUtf8() {
        // 获取 JVM 默认字符集
        Charset defaultCharset = Charset.defaultCharset();
        System.out.println("默认字符集名称: " + defaultCharset.name());
        System.out.println("默认字符集显示名称: " + defaultCharset.displayName());

        // 验证默认字符集为 UTF-8
        assert defaultCharset.equals(StandardCharsets.UTF_8) :
                "默认字符集不是 UTF-8, 而是 " + defaultCharset.name();
        System.out.println("--------------------------------------");
    }

    /**
     * 测试使用 FileReader 读取文件时默认使用 UTF-8 字符集
     * JDK 18 中, FileReader 和 FileWriter 默认使用 UTF-8 编码
     */
    @Test
    public void testFileReaderDefaultCharset() throws IOException {
        // 创建临时文件并写入 UTF-8 编码的中文内容
        Path tempFile = Files.createTempFile("utf8-test-", ".txt");
        try {
            // 写入 UTF-8 编码的文本
            String content = "你好, JDK 18! Hello, UTF-8!";
            Files.writeString(tempFile, content, StandardCharsets.UTF_8);

            // 使用 FileReader 读取, 验证默认使用 UTF-8
            FileReader fileReader = new FileReader(tempFile.toFile());
            // 获取 FileReader 实际使用的字符集
            System.out.println("FileReader 使用的字符集: " + fileReader.getEncoding());
            // 读取内容
            char[] buffer = new char[1024];
            int len = fileReader.read(buffer);
            String readContent = new String(buffer, 0, len);
            fileReader.close();

            System.out.println("读取到的内容: " + readContent);
            assert content.equals(readContent) : "读取内容与写入内容不一致";
        } finally {
            // 清理临时文件
            Files.deleteIfExists(tempFile);
        }
        System.out.println("--------------------------------------");
    }

    /**
     * 测试使用 FileWriter 写入文件时默认使用 UTF-8 字符集
     * JDK 18 中, FileWriter 默认使用 UTF-8 编码写入文件
     */
    @Test
    public void testFileWriterDefaultCharset() throws IOException {
        // 创建临时文件
        Path tempFile = Files.createTempFile("utf8-write-", ".txt");
        try {
            // 使用 FileWriter 写入中文内容
            String content = "UTF-8 默认编码测试 - 中文内容测试";
            FileWriter fileWriter = new FileWriter(tempFile.toFile());
            fileWriter.write(content);
            fileWriter.close();

            // 验证写入的内容是否以 UTF-8 编码存储
            byte[] fileBytes = Files.readAllBytes(tempFile);
            String readContent = new String(fileBytes, StandardCharsets.UTF_8);
            System.out.println("写入的内容: " + content);
            System.out.println("读取的内容: " + readContent);
            assert content.equals(readContent) : "FileWriter 写入的内容与 UTF-8 读取的内容不一致";
        } finally {
            // 清理临时文件
            Files.deleteIfExists(tempFile);
        }
        System.out.println("--------------------------------------");
    }

    /**
     * 测试使用 PrintStream 输出时默认使用 UTF-8 字符集
     * JDK 18 中, PrintStream 和 PrintWriter 也默认使用 UTF-8 编码
     */
    @Test
    public void testPrintStreamDefaultCharset() throws IOException {
        // 创建临时文件
        Path tempFile = Files.createTempFile("utf8-print-", ".txt");
        try {
            // 使用 PrintStream 写入 UTF-8 内容
            String content = "PrintStream 默认 UTF-8 编码输出";
            PrintStream printStream = new PrintStream(tempFile.toFile());
            printStream.println(content);
            printStream.close();

            // 验证内容
            byte[] fileBytes = Files.readAllBytes(tempFile);
            String readContent = new String(fileBytes, StandardCharsets.UTF_8).trim();
            System.out.println("PrintStream 写入内容: " + readContent);
            assert content.equals(readContent) : "PrintStream 写入的内容与 UTF-8 读取的内容不一致";
        } finally {
            // 清理临时文件
            Files.deleteIfExists(tempFile);
        }
        System.out.println("--------------------------------------");
    }

    /**
     * 测试使用 Scanner 读取文件时默认使用 UTF-8 字符集
     * JDK 18 中, Scanner 默认使用 UTF-8 编码读取输入
     */
    @Test
    public void testScannerDefaultCharset() throws IOException {
        // 创建临时文件
        Path tempFile = Files.createTempFile("utf8-scanner-", ".txt");
        try {
            // 写入 Unicode 文本
            String content = "Scanner 默认 UTF-8 读取测试";
            Files.writeString(tempFile, content, StandardCharsets.UTF_8);

            // 使用 Scanner 读取
            Scanner scanner = new Scanner(tempFile.toFile());
            String readContent = scanner.nextLine();
            scanner.close();

            System.out.println("Scanner 读取内容: " + readContent);
            assert content.equals(readContent) : "Scanner 读取的内容与写入内容不一致";
        } finally {
            // 清理临时文件
            Files.deleteIfExists(tempFile);
        }
        System.out.println("--------------------------------------");
    }

    /**
     * 测试使用 InputStreamReader 默认使用 UTF-8 字符集
     * JDK 18 中, 不指定字符集的流式 API 均默认使用 UTF-8
     */
    @Test
    public void testInputStreamReaderDefaultCharset() throws IOException {
        // 创建临时文件
        Path tempFile = Files.createTempFile("utf8-stream-", ".txt");
        try {
            // 写入 UTF-8 内容
            String content = "InputStreamReader 默认 UTF-8 编码测试";
            Files.writeString(tempFile, content, StandardCharsets.UTF_8);

            // 使用 InputStreamReader 读取, 不指定字符集
            FileInputStream fileInputStream = new FileInputStream(tempFile.toFile());
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            System.out.println("InputStreamReader 使用的字符集: " + inputStreamReader.getEncoding());

            char[] buffer = new char[1024];
            int len = inputStreamReader.read(buffer);
            String readContent = new String(buffer, 0, len);
            inputStreamReader.close();

            System.out.println("InputStreamReader 读取内容: " + readContent);
            assert content.equals(readContent) : "InputStreamReader 读取的内容与写入内容不一致";
        } finally {
            // 清理临时文件
            Files.deleteIfExists(tempFile);
        }
        System.out.println("--------------------------------------");
    }

    /**
     * 测试使用 OutputStreamWriter 默认使用 UTF-8 字符集
     * JDK 18 中, OutputStreamWriter 不指定字符集时默认使用 UTF-8
     */
    @Test
    public void testOutputStreamWriterDefaultCharset() throws IOException {
        // 创建临时文件
        Path tempFile = Files.createTempFile("utf8-output-writer-", ".txt");
        try {
            // 使用 OutputStreamWriter 写入, 不指定字符集
            String content = "OutputStreamWriter 默认 UTF-8 编码写入测试";
            FileOutputStream fileOutputStream = new FileOutputStream(tempFile.toFile());
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            System.out.println("OutputStreamWriter 使用的字符集: " + outputStreamWriter.getEncoding());
            outputStreamWriter.write(content);
            outputStreamWriter.close();

            // 验证内容
            byte[] fileBytes = Files.readAllBytes(tempFile);
            String readContent = new String(fileBytes, StandardCharsets.UTF_8);
            System.out.println("写入内容: " + content);
            System.out.println("读取内容: " + readContent);
            assert content.equals(readContent) : "OutputStreamWriter 写入的内容与 UTF-8 读取的内容不一致";
        } finally {
            // 清理临时文件
            Files.deleteIfExists(tempFile);
        }
        System.out.println("--------------------------------------");
    }
}