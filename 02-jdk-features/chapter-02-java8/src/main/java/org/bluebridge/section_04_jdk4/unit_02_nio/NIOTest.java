package org.bluebridge.section_04_jdk4.unit_02_nio;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * JDK 1.4 引入的 NIO (New I/O) 测试
 * 核心概念：Buffer（缓冲区）、Channel（通道）、Charset（字符集）
 *
 * @author lingwh
 * @date 2026/08/05 19:01
 */
public class NIOTest {

    /**
     * 测试 ByteBuffer 的基本读写操作
     * 创建缓冲区、写入数据、读取数据
     */
    @Test
    public void testByteBufferReadWrite() {
        // 创建容量为 10 的 ByteBuffer
        ByteBuffer buffer = ByteBuffer.allocate(10);

        // 写入数据
        buffer.put((byte) 1);
        buffer.put((byte) 2);
        buffer.put((byte) 3);
        System.out.println("写入 3 个字节后，position=" + buffer.position() + ", limit=" + buffer.limit() + ", capacity=" + buffer.capacity());

        // 切换为读模式
        buffer.flip();
        System.out.println("flip() 后，position=" + buffer.position() + ", limit=" + buffer.limit() + ", capacity=" + buffer.capacity());

        // 读取数据
        while (buffer.hasRemaining()) {
            byte b = buffer.get();
            System.out.println("读取到字节：" + b);
        }
    }

    /**
     * 测试 Buffer 的 position、limit、capacity 三个属性
     * position：下一个要读/写的索引
     * limit：缓冲区中有效数据的界限
     * capacity：缓冲区的最大容量
     */
    @Test
    public void testBufferProperties() {
        ByteBuffer buffer = ByteBuffer.allocate(16);
        System.out.println("初始状态：position=" + buffer.position() + ", limit=" + buffer.limit() + ", capacity=" + buffer.capacity());

        // 写入 5 个字节
        buffer.put((byte) 10);
        buffer.put((byte) 20);
        buffer.put((byte) 30);
        buffer.put((byte) 40);
        buffer.put((byte) 50);
        System.out.println("写入 5 字节后：position=" + buffer.position() + ", limit=" + buffer.limit() + ", capacity=" + buffer.capacity());

        // flip 切换到读模式
        buffer.flip();
        System.out.println("flip 后：position=" + buffer.position() + ", limit=" + buffer.limit() + ", capacity=" + buffer.capacity());

        // 读取 2 个字节
        buffer.get();
        buffer.get();
        System.out.println("读取 2 字节后：position=" + buffer.position() + ", limit=" + buffer.limit() + ", capacity=" + buffer.capacity());
    }

    /**
     * 测试 Buffer 的 flip()、clear()、rewind() 方法
     * flip()：切换到读模式
     * clear()：清空缓冲区，切换到写模式
     * rewind()：重置 position 为 0，允许重新读取
     */
    @Test
    public void testBufferFlipClearRewind() {
        ByteBuffer buffer = ByteBuffer.allocate(8);

        // 写入数据
        buffer.put((byte) 'A');
        buffer.put((byte) 'B');
        buffer.put((byte) 'C');
        System.out.println("写入 ABC 后：position=" + buffer.position());

        // flip() 切换读模式
        buffer.flip();
        System.out.println("读取：");
        while (buffer.hasRemaining()) {
            System.out.print((char) buffer.get() + " ");
        }
        System.out.println();

        // rewind() 重置 position，可重新读取
        buffer.rewind();
        System.out.println("rewind() 后 position=" + buffer.position() + "，重新读取：");
        while (buffer.hasRemaining()) {
            System.out.print((char) buffer.get() + " ");
        }
        System.out.println();

        // clear() 清空缓冲区，切换写模式
        buffer.clear();
        System.out.println("clear() 后：position=" + buffer.position() + ", limit=" + buffer.limit() + ", capacity=" + buffer.capacity());
    }

    /**
     * 测试其他 Buffer 类型：CharBuffer 和 IntBuffer
     */
    @Test
    public void testOtherBufferTypes() {
        // CharBuffer 示例
        CharBuffer charBuffer = CharBuffer.allocate(10);
        charBuffer.put('H');
        charBuffer.put('e');
        charBuffer.put('l');
        charBuffer.put('l');
        charBuffer.put('o');
        charBuffer.flip();
        System.out.print("CharBuffer 内容：");
        while (charBuffer.hasRemaining()) {
            System.out.print(charBuffer.get());
        }
        System.out.println();

        // IntBuffer 示例
        IntBuffer intBuffer = IntBuffer.allocate(5);
        intBuffer.put(100);
        intBuffer.put(200);
        intBuffer.put(300);
        intBuffer.flip();
        System.out.print("IntBuffer 内容：");
        while (intBuffer.hasRemaining()) {
            System.out.print(intBuffer.get() + " ");
        }
        System.out.println();
    }

    /**
     * 测试 FileChannel 的基本使用（只读方式读取文件）
     * 注意：此测试需要存在目标文件，如果文件不存在会抛出异常
     */
    @Test
    public void testFileChannelRead() {
        // 演示 FileChannel 的创建和基本概念
        // FileChannel 通过 FileInputStream、FileOutputStream 或 RandomAccessFile 获取
        System.out.println("FileChannel 获取方式：");

        // 方式一：通过 FileInputStream 获取（只读）
        System.out.println("1. FileInputStream.getChannel() — 只读通道");

        // 方式二：通过 FileOutputStream 获取（只写）
        System.out.println("2. FileOutputStream.getChannel() — 只写通道");

        // 方式三：通过 RandomAccessFile 获取（读写）
        System.out.println("3. RandomAccessFile.getChannel() — 读写通道");

        // 演示 FileChannel 的读取过程（使用 try-with-resources）
        // 这里不实际执行文件读取，因为需要依赖外部文件
        System.out.println("FileChannel 读取步骤：创建通道 -> 分配 Buffer -> 通道读取数据到 Buffer -> flip -> 从 Buffer 读取数据");
    }

    /**
     * 测试 Charset 编码和解码
     * 将字符串编码为字节序列，再将字节序列解码为字符串
     */
    @Test
    public void testCharsetEncodeDecode() {
        String original = "你好，NIO！Java 1.4";

        // 获取 Charset 实例
        Charset utf8 = StandardCharsets.UTF_8;
        Charset iso8859 = Charset.forName("ISO-8859-1");

        // 编码：字符串 -> ByteBuffer
        ByteBuffer encodedBuffer = utf8.encode(original);
        System.out.println("UTF-8 编码后字节数：" + encodedBuffer.limit());

        // 解码：ByteBuffer -> 字符串
        String decoded = utf8.decode(encodedBuffer).toString();
        System.out.println("UTF-8 解码后：" + decoded);

        // 演示不同字符集编码差异
        System.out.println("UTF-8 编码 " + original + " 的字节：");
        encodedBuffer.rewind();
        while (encodedBuffer.hasRemaining()) {
            System.out.printf("%02x ", encodedBuffer.get());
        }
        System.out.println();

        // 列出可用的字符集
        System.out.println("可用字符集数量：" + Charset.availableCharsets().size());
    }

    /**
     * 测试 wrap 方法：将数组包装为 Buffer
     */
    @Test
    public void testBufferWrap() {
        // 使用 wrap 将数组包装为 Buffer
        byte[] array = {1, 2, 3, 4, 5};
        ByteBuffer buffer = ByteBuffer.wrap(array);
        System.out.println("wrap 后：position=" + buffer.position() + ", limit=" + buffer.limit() + ", capacity=" + buffer.capacity());

        // 修改数组会反映到 Buffer 中
        array[0] = 99;
        buffer.rewind();
        System.out.println("数组修改后 Buffer 的第一个元素：" + buffer.get());
    }

    /**
     * 测试批量移动方法 get(byte[]) 和 put(byte[])
     */
    @Test
    public void testBulkGetPut() {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        // 批量 put
        byte[] source = {1, 2, 3, 4, 5};
        buffer.put(source);
        System.out.println("批量 put 后：position=" + buffer.position());

        // 批量 get
        buffer.flip();
        byte[] target = new byte[5];
        buffer.get(target);
        System.out.println("批量 get 后：" + Arrays.toString(target));
    }
}