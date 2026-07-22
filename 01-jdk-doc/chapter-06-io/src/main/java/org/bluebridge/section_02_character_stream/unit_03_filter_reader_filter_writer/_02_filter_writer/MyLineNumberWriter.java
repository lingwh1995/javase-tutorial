package org.bluebridge.section_02_character_stream.unit_03_filter_reader_filter_writer._02_filter_writer;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * 自定义FilterWriter的子类LineNumberWriter，用于在写入文本时添加行号
 *
 * @author lingwh
 * @date 2025/8/29 13:57
 */
public class MyLineNumberWriter extends FilterWriter {

    private long lineNumber = 0;
    private boolean isFirstChar = true;
    // 行号宽度
    private int numberWidth = 4;

    /**
     * 构造方法
     *
     * @param out 底层的Writer对象
     */
    public MyLineNumberWriter(Writer out) {
        super(out);
    }

    /**
     * 设置行号宽度
     *
     * @param width 行号显示的最小宽度
     */
    public void setNumberWidth(int width) {
        if (width > 0) {
            this.numberWidth = width;
        }
    }

    /**
     * 获取当前行号
     *
     * @return 当前行号
     */
    public long getLineNumber() {
        return lineNumber;
    }

    /**
     * 设置行号
     *
     * @param lineNumber 新的行号
     */
    public void setLineNumber(long lineNumber) {
        this.lineNumber = lineNumber;
    }

    /**
     * 写入单个字符
     *
     * @param c 要写入的字符
     * @throws IOException IO异常
     */
    @Override
    public void write(int c) throws IOException {
        synchronized (lock) {
            if (isFirstChar) {
                lineNumber++;
                writeLineNumber();
                isFirstChar = false;
            }

            out.write(c);

            // 如果是换行符，标记下一次写入为新行开始
            if (c == '\n' || c == '\r') {
                isFirstChar = true;
            }
        }
    }

    /**
     * 写入字符数组的一部分
     *
     * @param cbuf 字符数组
     * @param off  起始位置
     * @param len  写入长度
     * @throws IOException IO异常
     */
    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        synchronized (lock) {
            int start = off;
            int end = off + len;

            for (int i = off; i < end; i++) {
                if (isFirstChar) {
                    lineNumber++;
                    writeLineNumber();
                    isFirstChar = false;
                }

                // 处理换行符
                if (cbuf[i] == '\n' || cbuf[i] == '\r') {
                    out.write(cbuf, start, i - start + 1);
                    start = i + 1;
                    isFirstChar = true;
                }
            }

            // 写入剩余字符
            if (start < end) {
                out.write(cbuf, start, end - start);
            }
        }
    }

    /**
     * 写入字符串的一部分
     *
     * @param str 字符串
     * @param off 起始位置
     * @param len 写入长度
     * @throws IOException IO异常
     */
    @Override
    public void write(String str, int off, int len) throws IOException {
        synchronized (lock) {
            int start = off;
            int end = off + len;

            for (int i = off; i < end; i++) {
                if (isFirstChar) {
                    lineNumber++;
                    writeLineNumber();
                    isFirstChar = false;
                }

                // 处理换行符
                char c = str.charAt(i);
                if (c == '\n' || c == '\r') {
                    out.write(str, start, i - start + 1);
                    start = i + 1;
                    isFirstChar = true;
                }
            }

            // 写入剩余字符
            if (start < end) {
                out.write(str, start, end - start);
            }
        }
    }

    /**
     * 写入行号
     *
     * @throws IOException IO异常
     */
    private void writeLineNumber() throws IOException {
        String lineNumberStr = String.valueOf(lineNumber);
        int padding = numberWidth - lineNumberStr.length();

        // 添加前导空格以对齐行号
        for (int i = 0; i < padding; i++) {
            out.write(' ');
        }

        out.write(lineNumberStr);
        out.write(": ");
    }
}
