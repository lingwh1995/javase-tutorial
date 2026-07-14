package org.bluebridge.chapter_05_file_channel;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * FileChannel是Java NIO中处理文件I/O的强大工具，特别适用于需要高性能和复杂文件操作的场景。
 *
 * 适用场景
 * 1. 大文件处理：处理大文件时，FileChannel 的内存映射功能可以显著提高性能
 * 2. 高并发场景：在多线程环境中，FileChannel 提供更好的线程安全性
 * 3. 高性能要求：对 I/O 性能有较高要求的应用
 * 4. 随机访问：需要在文件中随机位置读写数据的场景
 *
 * @author lingwh
 * @date 2025/9/22 15:09
 */
@Slf4j
public class FileChannelTest {

    /**
     * 测试获取FileChannel
     *
     * @throws Exception
     */
    @Test
    public void testGetFileChannel() throws Exception {
        try {
            // 1. 通过 FileInputStream 获取（只读）
            FileInputStream fis = new FileInputStream("d:/io/file_channel.txt");
            FileChannel channelFis = fis.getChannel();

            // 2. 通过 FileOutputStream 获取（只写）
            FileOutputStream fos = new FileOutputStream("d:/io/file_channel.txt");
            FileChannel channelFos = fos.getChannel();

            // 3. 通过 RandomAccessFile 获取
            RandomAccessFile raf = new RandomAccessFile("d:/io/file_channel.txt", "rw");
            FileChannel channelRaf = raf.getChannel();

            // 4. 通过 Files 类获取（Java 7+）
            FileChannel channelJDK7 = FileChannel.open(Paths.get("d:/io/file_channel.txt"), StandardOpenOption.READ, StandardOpenOption.WRITE);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试FileChannel的读写API
     *
     * @throws Exception
     */
    @Test
    public void testFileChannelAPI_1() throws Exception {
        // 获取输入流和输入流Channel
        try (FileInputStream fis = new FileInputStream("d:/io/file_input_stream_channel.txt");
                FileChannel channelFis = fis.getChannel()) {

            // 读取数据
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int length;
            while ((length = channelFis.read(buffer)) != -1) {
                // 切换到读取模式
                buffer.flip();

                // 解码字节为字符串
                CharBuffer readContent = StandardCharsets.UTF_8.decode(buffer);
                log.info("读取到的字节长度： {}", length);
                log.info("读取到的文本内容： \n{}", readContent);

                // 清理缓冲区，为下次使用做准备
                buffer.clear();
            }

            // 获取输出流和输出流Channel
            try (FileOutputStream fos = new FileOutputStream("d:/io/file_output_stream_channel.txt");
                    FileChannel channelFos = fos.getChannel()) {
                // 写入数据
                buffer = ByteBuffer.wrap("Hello World".getBytes());
                int bytesWritten = channelFos.write(buffer);
                // 强制刷新到磁盘
                channelFos.force(true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试FileChannel的其他API
     *
     * @throws Exception
     */
    @Test
    public void testFileChannelAPI_2() throws Exception {
        // 获取输入流和输入流Channel
        try (FileInputStream fis = new FileInputStream("d:/io/file_input_stream_channel.txt");
                FileChannel channelFis = fis.getChannel()) {

            // 读取数据
            ByteBuffer buffer = ByteBuffer.allocate(100);
            int length = channelFis.read(buffer);
            // 切换到读取模式
            buffer.flip();
            // 解码字节为字符串
            CharBuffer readContent = StandardCharsets.UTF_8.decode(buffer);
            log.info("读取到的字节长度： {}", length);
            log.info("读取到的文本内容： \n{}", readContent);

            // 获取当前读取位置
            long position = channelFis.position();
            log.info("当前读取的位置： {}", position);

            // 设置当前读取位置
            channelFis.position(200);
            position = channelFis.position();
            log.info("当前读取的位置： {}", position);

            // 获取文件大小
            long size = channelFis.size();
            log.info("文件大小： {}", size);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试使用FileChannel复制文件
     *
     * @throws Exception
     */
    @Test
    public void testFileChannelCopyFile() throws Exception {
        String source = "d:/io/file_input_stream_channel.txt";
        String target = "d:/io/file_output_stream_channel_copy.txt";
        try (RandomAccessFile sourceFile = new RandomAccessFile(source, "r");
                RandomAccessFile targetFile = new RandomAccessFile(target, "rw");
                FileChannel sourceChannel = sourceFile.getChannel();
                FileChannel targetChannel = targetFile.getChannel()) {

            // 使用 transferTo 方法高效复制文件
            sourceChannel.transferTo(0, sourceChannel.size(), targetChannel);
        }
    }

    /**
     * 内存映射
     *
     * @throws IOException
     */
    @Test
    public void memoryMappedFile() throws IOException {
        try {
            RandomAccessFile raf = new RandomAccessFile("d:/io/file_channel_memory_mapped.txt", "rw");
            FileChannel fileChannel = raf.getChannel();

            // 创建一个可读可写的内存映射文件
            MappedByteBuffer mappedBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 1024);

            // 写入数据
            String data = "Memory mapped data";
            mappedBuffer.put(data.getBytes());

            // 读取数据
            mappedBuffer.flip();
            byte[] bytes = new byte[mappedBuffer.remaining()];
            mappedBuffer.get(bytes);
            System.out.println(new String(bytes));

            // 清理资源
            fileChannel.close();
            raf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
