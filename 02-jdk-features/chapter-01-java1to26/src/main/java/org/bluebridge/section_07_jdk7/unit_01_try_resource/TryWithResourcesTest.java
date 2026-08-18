package org.bluebridge.section_07_jdk7.unit_01_try_resource;

import org.junit.Test;

import java.io.*;

/**
 * JDK 7 引入的 try-with-resources 测试
 *
 * @author lingwh
 * @date 2026/08/05 19:01
 */
public class TryWithResourcesTest {

    /**
     * 测试 try-with-resources 自动关闭实现了 AutoCloseable 的资源
     */
    @Test
    public void testTryWithResources() {
        String filePath = "src/main/java/org/bluebridge/section_07_jdk7/unit_01_try_resource/test.txt";
        // try-with-resources 语法，资源会在 try 块结束后自动关闭
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("读取内容: " + line);
            }
        } catch (IOException e) {
            System.err.println("发生 IO 异常: " + e.getMessage());
        }
    }

    /**
     * 测试多个资源同时关闭
     */
    @Test
    public void testMultipleResources() {
        String srcPath = "src/main/java/org/bluebridge/section_07_jdk7/unit_01_try_resource/source.txt";
        String destPath = "src/main/java/org/bluebridge/section_07_jdk7/unit_01_try_resource/dest.txt";
        // 多个资源用分号分隔，会按照资源声明的逆序自动关闭
        try (BufferedReader reader = new BufferedReader(new FileReader(srcPath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(destPath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
            System.out.println("文件复制完成");
        } catch (IOException e) {
            System.err.println("发生 IO 异常: " + e.getMessage());
        }
    }

    /**
     * 测试自定义 AutoCloseable 实现
     */
    @Test
    public void testCustomAutoCloseable() {
        // 自定义资源会在 try 块结束后自动调用 close() 方法
        try (MyResource resource = new MyResource("自定义资源A");
             MyResource anotherResource = new MyResource("自定义资源B")) {
            resource.doSomething();
            anotherResource.doSomething();
        } catch (Exception e) {
            System.err.println("发生异常: " + e.getMessage());
        }
    }

    /**
     * 对比传统 try-finally 写法
     */
    @Test
    public void testTraditionalTryFinally() {
        String filePath = "src/main/java/org/bluebridge/section_07_jdk7/unit_01_try_resource/test.txt";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("读取内容: " + line);
            }
        } catch (IOException e) {
            System.err.println("发生 IO 异常: " + e.getMessage());
        } finally {
            // 传统写法需要在 finally 块中手动关闭资源，并处理关闭时的异常
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.err.println("关闭资源时发生异常: " + e.getMessage());
                }
            }
        }
    }

    /**
     * 自定义 AutoCloseable 实现类
     */
    private static class MyResource implements AutoCloseable {
        private final String name;

        public MyResource(String name) {
            this.name = name;
            System.out.println("创建资源: " + name);
        }

        public void doSomething() {
            System.out.println("使用资源: " + name);
        }

        @Override
        public void close() throws Exception {
            System.out.println("关闭资源: " + name);
        }
    }
}