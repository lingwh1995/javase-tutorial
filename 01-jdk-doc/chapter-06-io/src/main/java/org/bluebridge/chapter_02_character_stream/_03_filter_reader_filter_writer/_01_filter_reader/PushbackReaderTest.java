package org.bluebridge.chapter_02_character_stream._03_filter_reader_filter_writer._01_filter_reader;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.StringReader;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * PushbackReader 是 Java IO 中的一个特殊字符流读取器，它允许将读取的字符推回流中。
 *
 * 1. 基本概念
 *    PushbackReader 是 FilterReader 的子类，提供了字符推回功能，它维护一个内部缓冲区，用于存储被推回的字符
 *    主要用于需要"预读"字符然后根据情况决定是否使用的场景
 * 2. 主要特性
 *    推回功能：可以将已读取的字符推回流中，下次读取时会先读取推回的字符
 *    缓冲机制：内部维护一个推回缓冲区
 *    向前查看：支持向前查看字符而不影响后续读取
 * 3. 常用方法
 *    read()：读取单个字符
 *    read(char[] cbuf, int off, int len)：读取字符数组
 *    unread(int c)：将字符推回流中
 *    unread(char[] cbuf, int off, int len)：将字符数组推回流中
 *    close()：关闭流
 *
 * @author lingwh
 * @date 2025/8/29 13:34
 */
@Slf4j
public class PushbackReaderTest {

    @Test
    public void testPushbackReader() throws Exception {
        try {
            // 创建PushbackReader
            String content = "Hello World 123";
            StringReader stringReader = new StringReader(content);
            PushbackReader pushbackReader = new PushbackReader(stringReader);

            int ch;
            while ((ch = pushbackReader.read()) != -1) {
                char c = (char) ch;
                // 如果是数字，推回并处理
                if (Character.isDigit(c)) {
                    // 推回字符
                    pushbackReader.unread(c);
                    // 现在可以读取完整的数字
                    StringBuilder number = new StringBuilder();
                    int next;
                    while ((next = pushbackReader.read()) != -1 && Character.isDigit(next)) {
                        number.append((char) next);
                    }
                    log.info("发现数字: " + number.toString());
                    if (next != -1) {
                        pushbackReader.unread(next);
                    }
                } else {
                    log.info("发现字符: " + c);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
