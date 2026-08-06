﻿package org.bluebridge.section_11_jdk11.unit_02_files;

import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Java11 Files 新增方法测试
 *
 * 1. readString(Path): 将文件中的所有内容读取为一个字符串(默认 UTF-8 编码)
 * 2. writeString(Path, CharSequence): 将字符串写入文件(默认 UTF-8 编码, 文件不存在则创建)
 * 3. readAllLines(Path): 将文件中的所有行读取为字符串列表(默认 UTF-8 编码)
 *
 * @author lingwh
 * @date 2026/08/05 18:23
 */
public class FilesNewMethodsTest {

    /**
     * 测试 Files.readString(Path): 将文件中的内容读取为字符串
     */
    @Test
    public void testReadString() throws IOException {
        // 创建临时文件并写入内容
        Path tempFile = Files.createTempFile("readString", ".txt");
        Files.writeString(tempFile, "hello java11, 你好 Java11!");
        // 使用默认 UTF-8 编码读取文件内容
        String content = Files.readString(tempFile);
        System.out.println("readString 读取的内容: " + content);
        // 指定字符集读取文件内容
        String contentWithCharset = Files.readString(tempFile, StandardCharsets.UTF_8);
        System.out.println("指定字符集读取的内容: " + contentWithCharset);
        // 清理临时文件
        Files.deleteIfExists(tempFile);
    }

    /**
     * 测试 Files.writeString(Path, CharSequence): 将字符串写入文件
     */
    @Test
    public void testWriteString() throws IOException {
        // 创建临时文件
        Path tempFile = Files.createTempFile("writeString", ".txt");
        // 使用默认 UTF-8 编码将字符串写入文件
        Files.writeString(tempFile, "hello java11");
        System.out.println("writeString 写入成功, 文件内容: " + Files.readString(tempFile));
        // 指定字符集将字符串写入文件(默认覆盖原内容)
        Files.writeString(tempFile, "你好 Java11!", StandardCharsets.UTF_8);
        System.out.println("指定字符集写入成功, 文件内容: " + Files.readString(tempFile));
        // 清理临时文件
        Files.deleteIfExists(tempFile);
    }

    /**
     * 测试 Files.readAllLines(Path): 将文件中的所有行读取为字符串列表
     */
    @Test
    public void testReadAllLines() throws IOException {
        // 创建包含多行内容的临时文件
        Path tempFile = Files.createTempFile("readAllLines", ".txt");
        Files.write(tempFile, List.of("java", "python", "go"));
        // 按行读取文件内容
        List<String> lines = Files.readAllLines(tempFile);
        System.out.println("按行读取的内容:");
        lines.forEach(System.out::println);
        System.out.println("文件行数: " + lines.size());
        // 清理临时文件
        Files.deleteIfExists(tempFile);
    }
}
