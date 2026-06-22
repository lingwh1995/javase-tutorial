package org.bluebridge.chapter_02_character_stream._02_buffered_reader_buffered_writer._01_buffered_reader_buffered_writer;

/**
 * @author lingwh
 * @desc 带有缓冲区的输入流（通过缓冲字符来提高字符读取效率，特别适合读取大量文本数据）
 * @date 2025/8/21 18:45
 */

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.*;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * BufferedReader主要特点
 *    缓冲机制: 内部使用缓冲区减少实际的读取操作次数
 *    行读取支持: 提供高效的按行读取方法
 *    性能优化: 比直接读取字符流更高效
 *    字符流操作: 专门用于处理字符数据而非字节数据
 * 最佳实践
 *    始终关闭资源: 使用 try-with-resources 或确保在 finally 块中关闭 BufferedReader
 *    合适的缓冲区大小: 根据数据特征选择合适的缓冲区大小，默认通常是8192字符
 *    优先使用 readLine(): 处理文本行时优先使用 readLine() 而非逐字符读取
 *    处理 null 值: readLine() 返回 null 表示到达文件末尾
 *    异常处理: 妥善处理 IOException 异常、
 *
 * BufferedReader 是处理文本数据时非常重要的类，通过缓冲机制显著提高了文本读取的性能，特别是在处理大量文本数据时效果明显。
 */
@Slf4j
public class BufferedReaderTest {

    /**
     * 测试BufferedReader的readLine()方法
     * 该方法一次读取一行文本，返回包含该行内容的字符串，不包括任何行终止符
     */
    @Test
    public void testBufferedReaderReadLine() {
        try (BufferedReader br = new BufferedReader(new FileReader("d:/io/buffered_reader.txt"))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                log.info("line： {}", line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 测试指定缓冲区大小
     */
    @Test
    public void testBufferedReaderReadByBuffer() {
        try (BufferedReader br = new BufferedReader(new FileReader("d:/io/buffered_reader.txt"), 1024)) {
            int character;
            while ((character = br.read()) != -1) {
                log.info("本次读取到的内容： {}", (char) character);
                System.out.print((char) character);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 测试指定缓冲区大小
     */
    @Test
    public void testBufferedReaderMarkAndReset() {
        try {
            String text = "hello world~";
            StringReader stringReader = new StringReader(text);
            BufferedReader bufferedReader = new BufferedReader(stringReader);

            // 读取前5个字符
            char[] buffer = new char[5];
            bufferedReader.read(buffer, 0, 5);
            log.info("前5个字符： {}", new String(buffer));

            // 标记当前位置，允许回读10个字符
            bufferedReader.mark(10);

            // 继续读取接下来的5个字符
            bufferedReader.read(buffer, 0, 5);
            log.info("接下来5个字符： {}", new String(buffer));

            // 重置到标记位置
            bufferedReader.reset();
            bufferedReader.read(buffer, 0, 5);
            log.info("重置后读取5个字符： {}", new String(buffer));
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 逐字符读取与行读取对比
     */
    @Test
    public void testBufferedReaderReadLineAndReadChar() {
        // 生成1000行测试数据
        String testData = "这是性能测试数据\n".repeat(1000);
        StringReader stringReader = new StringReader(testData);

        // 测试逐行读取性能
        long startTime = Instant.now().toEpochMilli();
        try(BufferedReader bufferedReader = new BufferedReader(stringReader)) {
            String line;
            int lineCount = 0;
            while ((line = bufferedReader.readLine()) != null) {
                lineCount++;
            }
            long endTime = Instant.now().toEpochMilli();
            log.info("读取了 {} 行", lineCount);
            log.info("逐行读取耗时: {} 毫秒", (endTime - startTime));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试读取大文件
     */
    @Test
    public void testBufferedReaderReadBigFile() {
        try {
            // 创建一个大文件用于测试
            createLargeFile("d:/io/buffered_reader_large_file.txt", 10000);

            // 使用 BufferedReader 处理大文件
            FileReader fileReader = new FileReader("d:/io/buffered_reader_large_file.txt");
            // 8kb缓冲区
            BufferedReader bufferedReader = new BufferedReader(fileReader, 8192);

            Map<String, Integer> wordCount = new HashMap<>();
            String line;
            int lineCount = 0;

            while ((line = bufferedReader.readLine()) != null) {
                lineCount++;
                String[] words = line.split("\\s+");
                log.info("words： {}", words);
                for (String word : words) {
                    if (!word.isEmpty()) {
                        wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
                    }
                }

                // 每处理1000行输出一次进度
                if (lineCount % 1000 == 0) {
                    log.info("已处理 " + lineCount + " 行");
                }
            }

            log.info("总共处理了 {} 行", lineCount);
            log.info("不同单词及数量: {}", wordCount.size());

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建一个大文件用于测试
     * @param filename
     * @param lines
     * @throws IOException
     */
    private static void createLargeFile(String filename, int lines) throws IOException {
        FileWriter writer = new FileWriter(filename);
        for (int i = 1; i <= lines; i++) {
            writer.write("这是第 " + i + " 行文本，包含一些示例单词 java programming\n");
        }
        writer.close();
    }

}
