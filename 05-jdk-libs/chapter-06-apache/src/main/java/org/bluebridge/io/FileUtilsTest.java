package org.bluebridge.io;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.junit.Test;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * FileUtils 工具类测试
 *
 * @author lingwh
 * @date 2025/9/15 10:55
 */
@Slf4j
public class FileUtilsTest {

    /**
     * 读取文件内容到字符串
     *
     * @throws IOException
     */
    @Test
    public void testReadFileToString() throws IOException {
        String content = FileUtils.readFileToString(new File("d:/file_utils/file_utils_read.txt"),
                StandardCharsets.UTF_8.toString());
        log.info("content: {}", content);
    }

    /**
     * 读取文件内容到字符串
     *
     * @throws IOException
     */
    @Test
    public void testWriteStringToFile() throws IOException {
        FileUtils.writeStringToFile(new File("d:/file_utils/file_utils_write.txt"), "你是一只小青蛙，呱呱呱呱！",
                StandardCharsets.UTF_8.toString());
    }

    /**
     * 复制文件
     *
     * @throws IOException
     */
    @Test
    public void testCopyFile() throws IOException {
        FileUtils.copyFile(new File("d:/file_utils/source.txt"), new File("d:/file_utils/destination.txt"));
    }

    /**
     * 删除文件
     *
     * @throws IOException
     */
    @Test
    public void testDeleteDirectory() throws IOException {
        FileUtils.deleteDirectory(new File("d:/file_utils"));
    }

    /**
     * 过滤文件
     */
    @Test
    public void testFilterFiles() {
        // 查找所有.txt 文件
        FileFilter txtFilter = new SuffixFileFilter(".txt");
        File[] txtFiles = new File("d:/file_utils").listFiles(txtFilter);
        Arrays.stream(txtFiles).forEach(file -> log.info("fileName: {}", file.getName()));
    }

    /**
     * 过滤文件夹
     */
    @Test
    public void testFilterDirectory() {
        // 查找所有目录
        FileFilter dirFilter = DirectoryFileFilter.DIRECTORY;
        File[] directories = new File("d:/").listFiles(dirFilter);
        Arrays.stream(directories).forEach(directory -> log.info("directoryName: {}", directory.getName()));
    }

    public static void main(String[] args) throws Exception {
        // 创建文件监听器
        FileAlterationObserver observer = new FileAlterationObserver("d:/file_utils/file_utils_write.txt");
        observer.addListener(new FileAlterationListenerAdaptor() {
            @Override
            public void onFileCreate(File file) {
                log.info("File created: " + file.getName());
            }

            @Override
            public void onFileChange(File file) {
                log.info("File changed: " + file.getName());
            }
        });

        // 创建监控器
        FileAlterationMonitor monitor = new FileAlterationMonitor(1000, observer);
        monitor.start();
    }

    /**
     * 格式化字节大小，kb 转换为 mb
     */
    @Test
    public void testByteCountToDisplaySize() {
        long sizeInBytes = 1024 * 1024 * 5; // 5MB
        String readableSize = FileUtils.byteCountToDisplaySize(sizeInBytes);
        log.info("readableSize: {}", readableSize);
    }
}
