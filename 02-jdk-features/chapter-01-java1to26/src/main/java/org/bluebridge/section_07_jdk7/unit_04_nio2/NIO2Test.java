package org.bluebridge.section_07_jdk7.unit_04_nio2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

/**
 * JDK 7 引入的 NIO.2（java.nio.file）测试
 *
 * @author lingwh
 * @date 2026/08/05 19:02
 */
public class NIO2Test {

    private static final String TEST_DIR = "src/main/java/org/bluebridge/section_07_jdk7/unit_04_nio2/test_nio2";

    @Before
    public void setUp() throws IOException {
        // 测试前创建测试目录
        Files.createDirectories(Paths.get(TEST_DIR));
    }

    @After
    public void tearDown() throws IOException {
        // 测试后清理测试目录
        Path testDir = Paths.get(TEST_DIR);
        if (Files.exists(testDir)) {
            Files.walkFileTree(testDir, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    Files.delete(dir);
                    return FileVisitResult.CONTINUE;
                }
            });
        }
    }

    /**
     * 测试 Path 接口和 Paths 工具类
     */
    @Test
    public void testPathAndPaths() {
        // 使用 Paths.get() 创建 Path 对象
        Path path = Paths.get(TEST_DIR, "test.txt");
        System.out.println("完整路径: " + path.toAbsolutePath());
        System.out.println("文件名: " + path.getFileName());
        System.out.println("父目录: " + path.getParent());
        System.out.println("根目录: " + path.getRoot());
        System.out.println("路径层级数: " + path.getNameCount());

        // 路径操作
        Path resolved = path.resolve("subdir/file.txt");
        System.out.println("解析后的路径: " + resolved);

        Path relativized = Paths.get(TEST_DIR).relativize(path);
        System.out.println("相对路径: " + relativized);
    }

    /**
     * 测试 Files 工具类的基本操作
     */
    @Test
    public void testFilesBasicOperations() throws IOException {
        Path filePath = Paths.get(TEST_DIR, "nio2_test.txt");

        // 创建文件
        Files.createFile(filePath);
        System.out.println("文件创建成功: " + filePath);

        // 检查文件是否存在
        boolean exists = Files.exists(filePath);
        System.out.println("文件是否存在: " + exists);

        // 写入文件
        Files.write(filePath, "Hello NIO.2!\nJava 7 新特性".getBytes());
        System.out.println("文件写入成功");

        // 读取所有行
        List<String> lines = Files.readAllLines(filePath);
        System.out.println("文件读取内容:");
        for (String line : lines) {
            System.out.println("  " + line);
        }

        // 复制文件
        Path copyPath = Paths.get(TEST_DIR, "nio2_copy.txt");
        Files.copy(filePath, copyPath, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("文件复制成功: " + copyPath);

        // 移动/重命名文件
        Path movePath = Paths.get(TEST_DIR, "nio2_moved.txt");
        Files.move(copyPath, movePath, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("文件移动成功: " + movePath);

        // 删除文件
        Files.delete(movePath);
        System.out.println("文件删除成功");
        Files.delete(filePath);
    }

    /**
     * 测试 FileVisitor 遍历目录
     */
    @Test
    public void testFileVisitor() throws IOException {
        // 创建测试目录结构
        Path subDir = Paths.get(TEST_DIR, "subdir");
        Files.createDirectories(subDir);
        Files.createFile(Paths.get(TEST_DIR, "file1.txt"));
        Files.createFile(Paths.get(TEST_DIR, "file2.txt"));
        Files.createFile(Paths.get(subDir, "subfile1.txt"));

        // 使用 FileVisitor 遍历目录
        System.out.println("遍历目录结构:");
        Files.walkFileTree(Paths.get(TEST_DIR), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                System.out.println("进入目录: " + dir.getFileName());
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println("  文件: " + file.getFileName() + " (大小: " + attrs.size() + " 字节)");
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
                System.out.println("离开目录: " + dir.getFileName());
                return FileVisitResult.CONTINUE;
            }
        });
    }

    /**
     * 测试 WatchService 监控文件变化
     * 注意：此测试需要在单独的线程中运行 WatchService，
     * 当前测试仅演示 WatchService 的创建和注册
     */
    @Test
    public void testWatchService() throws IOException, InterruptedException {
        Path watchDir = Paths.get(TEST_DIR);

        // 创建 WatchService
        WatchService watchService = FileSystems.getDefault().newWatchService();

        // 注册监控事件：创建、修改、删除
        watchDir.register(watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_MODIFY,
                StandardWatchEventKinds.ENTRY_DELETE);

        System.out.println("开始监控目录: " + watchDir.toAbsolutePath());
        System.out.println("请在 3 秒内修改目录中的文件...");

        // 模拟文件变化
        Files.createFile(Paths.get(TEST_DIR, "watch_test.txt"));
        System.out.println("创建了文件 watch_test.txt");

        // 获取监控事件（非阻塞方式）
        WatchKey key = watchService.poll();
        if (key != null) {
            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind<?> kind = event.kind();
                Path filename = (Path) event.context();
                System.out.println("检测到事件: " + kind.name() + " - 文件: " + filename);
            }
            boolean valid = key.reset();
            System.out.println("WatchKey 是否有效: " + valid);
        }

        // 清理创建的文件
        Files.deleteIfExists(Paths.get(TEST_DIR, "watch_test.txt"));

        watchService.close();
        System.out.println("WatchService 已关闭");
    }
}