package org.bluebridge.chapter_03_byte_stream_character_stream_convert;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 将字节流转换为字符流
 *
 * InputStreamReader和OutputStreamWriter是字节流为向字符流的桥梁类
 *
 * @author lingwh
 * @date 2025/9/5 17:23
 */
@Slf4j
public class ByteStreamToCharacterStream {

    /**
     * 将输入字节流转换为字符流
     */
    @Test
    public void testInputStreamByteStreamToCharacterStream() {
        /**
         * 基本转换
         */
        /*
        try(InputStream is = new FileInputStream("d:/io/input_byte_stream_to_character_stream.txt");
            InputStreamReader isr = new InputStreamReader(is)){
            char[] buffer = new char[10];
            int length = 0;
            while((length = isr.read(buffer)) != -1){
                log.info("本次读取到的长度：{}，读取到的内容： {}", length, new String(buffer,0, length));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        */

        /**
         * 指定编码格式
         */
        /*
        try(InputStream is = new FileInputStream("d:/io/input_byte_stream_to_character_stream.txt");
            InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8)){
            char[] buffer = new char[10];
            int length = 0;
            while((length = isr.read(buffer)) != -1){
                log.info("本次读取到的长度：{}，读取到的内容： {}", length, new String(buffer,0, length));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        */

        /**
         * 使用BufferedReader包装转换
         */
        try (InputStream is = new FileInputStream("d:/io/input_byte_stream_to_character_stream.txt");
                InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr)) {
            String line = null;
            while ((line = br.readLine()) != null) {
                log.info("本次读取到的内容：{}", line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将输出字节流转换为字符流
     */
    @Test
    public void testOutputStreamByteStreamToCharacterStream() {
        /**
         * 基本转换
         */
        /*
        try(OutputStream os = new FileOutputStream("d:/io/output_byte_stream_to_character_stream.txt");
            OutputStreamWriter osw = new OutputStreamWriter(os)){
            osw.write("hello world!");
            osw.write("将字节流转换为字符流!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        */

        /**
         * 指定编码格式
         */
        /*
        try(OutputStream os = new FileOutputStream("d:/io/output_byte_stream_to_character_stream.txt");
            OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8)){
            osw.write("hello world!");
            osw.write("将字节流转换为字符流!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        */

        /**
         * 使用BufferedWriter包装转换
         */
        try(OutputStream os = new FileOutputStream("d:/io/output_byte_stream_to_character_stream.txt");
            OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
            BufferedWriter br = new BufferedWriter(osw)){
            br.write("hello world!");
            // 如果是Reader或者Writer的包装类，需要调用newLine()方法换行
            br.newLine();
            br.write("将字节流转换为字符流!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
