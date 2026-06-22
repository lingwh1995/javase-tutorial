package org.bluebridge.chapter_04_random_access_file;

/**
 * @author lingwh
 * @desc RandomAccessFile主要完成随机读取的功能，可以读取指定位置的内容。不属于InputStream和OutputStream类系的，本质上属于字节流。
 * @date 2025/9/22 11:46
 */

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * RandomAccessFile特点
 *   1.RandomAccessFile 直接继承自 Object 类，没有继承任何字符流或字节流的基类
 *   2.它以字节为单位进行读写操作，而不是字符
 *
 * RandomAccessFile优势
 *   1.随机访问：可以跳转到文件任意位置进行读写
 *   2.双向操作：同时支持读取和写入操作
 *   3.基本数据类型支持：提供读写 int、double 等基本数据类型的方法
 *   4.高效性：对于需要频繁修改文件部分内容的场景非常高效
 *   5.灵活性：可以实现复杂的文件结构和数据管理
 *
 * 注意：
 *   虽然 RandomAccessFile 不是传统意义上的 InputStream 或 OutputStream，但它本质上是按字节处理数据的，因此属于字节流范畴。
 */
@Slf4j
public class RandomAccessFileReadAndWriteTest {

    /**
     * 测试 RandomAccessFile 读写原始数据类型
     */
    @Test
    public void testRandomAccessFileReadAndWriteOriginalDataType() throws Exception {
        // 创建 RandomAccessFile 对象，"rw" 表示可读可写
        try (RandomAccessFile raf = new RandomAccessFile("d:/io/random_access_file.txt", "rw")){
            // 写入各种基本数据类型
            raf.writeBoolean(true);
            raf.writeByte(100);
            raf.writeChar('A');
            raf.writeShort(1000);
            raf.writeInt(123456);
            raf.writeLong(123456789L);
            raf.writeFloat(3.14f);
            raf.writeDouble(3.1415926);
            raf.writeUTF("Hello World");

            // 重置文件指针到开头
            raf.seek(0);

            // 按写入顺序读取
            log.info("Boolean: {}",  raf.readBoolean());
            log.info("Byte: {}",  raf.readByte());
            log.info("Char: {}",  raf.readChar());
            log.info("Short: {}",  raf.readShort());
            log.info("Int: {}",  raf.readInt());
            log.info("Long: {}",  raf.readLong());
            log.info("Float: {}",  raf.readFloat());
            log.info("Double: {}",  raf.readDouble());
            log.info("UTF: {}",  raf.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试 RandomAccessFile 随机读写数据
     */
    @Test
    public void testRandomAccessFileRandomReadAndWrite() throws Exception {
        // 创建 RandomAccessFile 对象，"rw" 表示可读可写
        try (RandomAccessFile raf = new RandomAccessFile("d:/io/random_access_file.txt", "rw")){
            // 写入一些数据
            raf.writeUTF("第一条记录");
            raf.writeUTF("第二条记录");
            raf.writeUTF("第三条记录");

            // 获取文件长度
            log.info("文件长度: " + raf.length());

            // 跳转到第二条记录开始位置读取
            raf.seek(0); // 回到开头
            String first = raf.readUTF(); // 读取第一条
            long secondPosition = raf.getFilePointer(); // 记录第二条位置
            String second = raf.readUTF(); // 读取第二条
            String third = raf.readUTF(); // 读取第三条

            log.info("第一条: {}",  first);
            log.info("第二条: {}",  second);
            log.info("第三条: {}",  third);

            // 直接跳转到第二条记录位置进行修改
            raf.seek(secondPosition);
            raf.writeUTF("修改后的第二条记录");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCopyFileWithProgressTest() throws Exception {
        String source = "d:/io/random_access_file.txt";
        String target = "d:/io/random_access_file_copy.txt";
        try (RandomAccessFile src = new RandomAccessFile(source, "r");
             RandomAccessFile dest = new RandomAccessFile(target, "rw")) {

            long fileSize = src.length();
            byte[] buffer = new byte[1024];
            long copied = 0;

            while (copied < fileSize) {
                int bytesRead = src.read(buffer);
                if (bytesRead == -1){
                    break;
                }

                dest.write(buffer, 0, bytesRead);
                copied += bytesRead;

                // 显示进度
                double progress = (double) copied / fileSize * 100;
                log.info("复制进度: {}%", String.format("%.2f", progress));
            }
        }
    }

}
