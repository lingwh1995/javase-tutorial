package org.bluebridge.chapter_01_byte_stream._06_filter_input_stream_filter_output_stream._02_data_input_stream_data_output_stream;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.*;

/**
 * @author lingwh
 * @desc DataInputStream 数据输入流允许应用程序以机器无关的方式从底层输入流中读取基本的Java类型
 * @date 2025/8/16 17:33
 */
@Slf4j
public class DataInputStream_DataOutputStreamTest {

    @Test
    public void testDataInputStream() {
        try(DataOutputStream dos = new DataOutputStream(new FileOutputStream("d:/io/data_output_stream.txt"));
            DataInputStream dis = new DataInputStream(new FileInputStream("d:/io/data_output_stream.txt"))) {
            dos.writeUTF("α");
            dos.writeInt(1234567);
            dos.writeBoolean(true);
            dos.writeShort((short)123);
            dos.writeLong((long)456);
            dos.writeDouble(99.98);
            log.info("dis.readUTF()： {}", dis.readUTF());
            log.info("dis.readInt()： {}", dis.readInt());
            log.info("dis.readBoolean()： {}", dis.readBoolean());
            log.info("dis.readShort()： {}", dis.readShort());
            log.info("dis.readLong()： {}", dis.readLong());
            log.info("dis.readDouble()： {}", dis.readDouble());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
