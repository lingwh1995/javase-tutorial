﻿package org.bluebridge.section_09_jdk9.unit_08_try_with_resources;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

/**
 * Java9 Try-With-Resources 增强测试
 *
 * Java9 对 Try-With-Resources 进行了增强, 允许在 try 中使用 effectively final 的变量,
 * 无需在 try 中声明新的变量:
 * 1. Java7 中必须在 try 中声明资源变量, 资源类必须实现 AutoCloseable
 * 2. Java9 中可以直接使用 already declared 的 effectively final 变量
 * 3. 可以同时组合多个资源, 无论它们是新声明的还是早已声明的
 *
 * @author lingwh
 * @date 2026/08/06 14:07
 */
public class TryWithResourcesTest {

    /**
     * 测试 Java9 Try-With-Resources: 使用 effectively final 变量
     * 资源变量在 try 外部声明, 在 try 中直接使用
     */
    @Test
    public void testEffectivelyFinalVariable() throws IOException {
        // 资源在 try 外部声明, 是 effectively final 的
        BufferedReader reader = new BufferedReader(new StringReader("hello"));
        // Java9 可以直接在 try 中使用 reader, 无需重新声明变量
        try (reader) {
            String line = reader.readLine();
            System.out.println("读取到的内容: " + line);
        }
    }

    /**
     * 测试 Java9 Try-With-Resources: 多个资源组合
     * 在 try 中同时使用多个 already declared 的变量
     */
    @Test
    public void testMultipleResources() throws IOException {
        BufferedReader reader1 = new BufferedReader(new StringReader("资源1"));
        BufferedReader reader2 = new BufferedReader(new StringReader("资源2"));
        // 多个 already declared 的资源组合使用
        try (reader1; reader2) {
            String line1 = reader1.readLine();
            String line2 = reader2.readLine();
            System.out.println("资源1: " + line1 + ", 资源2: " + line2);
        }
    }

    /**
     * 测试 Java9 Try-With-Resources: 混合使用新声明和已有变量
     * 新声明的资源和 already declared 的变量可以混用
     */
    @Test
    public void testMixedResources() throws IOException {
        BufferedReader existingReader = new BufferedReader(new StringReader("已有资源"));
        // 混用: 已有变量 + 新声明的资源
        try (existingReader;
             BufferedReader newReader = new BufferedReader(new StringReader("新资源"))) {
            String line1 = existingReader.readLine();
            String line2 = newReader.readLine();
            System.out.println("已有资源: " + line1 + ", 新资源: " + line2);
        }
    }

    /**
     * 测试 Java9 Try-With-Resources: 验证资源自动关闭
     */
    @Test
    public void testAutoClose() throws IOException {
        // 创建一个自定义资源, 跟踪关闭状态
        AutoCloseableResource resource = new AutoCloseableResource("测试资源");
        System.out.println("资源是否已关闭(初始): " + resource.isClosed());
        // Java9 风格: 使用 already declared 的变量
        try (resource) {
            System.out.println("在 try 块中使用资源: " + resource.getName());
            System.out.println("资源是否已关闭(try 块内): " + resource.isClosed());
        }
        // try 块结束后, 资源自动关闭
        System.out.println("资源是否已关闭(try 结束后): " + resource.isClosed());
    }

    /**
     * 测试 Java9 Try-With-Resources: 多个自定义资源自动关闭
     */
    @Test
    public void testMultipleAutoClose() throws IOException {
        AutoCloseableResource resource1 = new AutoCloseableResource("资源A");
        AutoCloseableResource resource2 = new AutoCloseableResource("资源B");
        // 使用多个 already declared 的资源, 关闭顺序与声明顺序相反
        try (resource1; resource2) {
            System.out.println("使用 " + resource1.getName() + " 和 " + resource2.getName());
        }
        System.out.println("资源A是否已关闭: " + resource1.isClosed());
        System.out.println("资源B是否已关闭: " + resource2.isClosed());
    }

    /**
     * 自定义资源类, 实现 AutoCloseable, 用于验证自动关闭行为
     */
    private static class AutoCloseableResource implements AutoCloseable {
        private final String name;
        private boolean closed = false;

        public AutoCloseableResource(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public boolean isClosed() {
            return closed;
        }

        @Override
        public void close() {
            closed = true;
            System.out.println("资源 '" + name + "' 已关闭");
        }
    }
}