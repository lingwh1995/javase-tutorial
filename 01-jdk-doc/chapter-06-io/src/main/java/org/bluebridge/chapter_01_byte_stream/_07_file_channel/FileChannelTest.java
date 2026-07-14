package org.bluebridge.chapter_01_byte_stream._07_file_channel;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * FileChannel
 *
 * 用于读取、写入文件的通道,只能被RandomAccessFile、FileInputStream、FileOutputStream创建
 *
 * @author lingwh
 * @date 2019/9/25 13:14
 */
public class FileChannelTest {

    public static void main(String[] args) {
        read();
        write();
    }

    public static void read() {
        File file = new File("d:/io/input_byte_stream_to_character_stream.txt");
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            // 获取管道
            FileChannel channel = fileInputStream.getChannel();
            // 创建缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            // 从通道读取数据到缓冲区
            channel.read(buffer);
            // 反转缓冲区(limit设置为position,position设置为0,mark设置为-1)
            buffer.flip();
            // 就是判断position和limit之间是否有元素
            while (buffer.hasRemaining()) {
                // 按照字节的格式获取数据
                System.out.print((char) buffer.get());
            }
            // 读完将缓冲区还原(position设置为0,limit设置为capacity,mark设置为-1)
            buffer.clear();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void write() {
        String data = "helloworld!";
        FileOutputStream fileOutputStream = null;
        File file = new File("d:/io/file_output_stream.txt");
        try {
            FileOutputStream fileOutputStream1 = new FileOutputStream(file);
            // 创建缓冲区
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            // 把数据装入到缓冲区
            byteBuffer.put(data.getBytes());
            // 反转缓冲区(limit设置为position,position设置为0,mark设置为-1)
            byteBuffer.flip();
            // 获取通道
            FileChannel channel = fileOutputStream1.getChannel();
            // 写文件
            channel.write(byteBuffer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
