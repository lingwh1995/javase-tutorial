﻿package org.bluebridge.section_13_jdk13.unit_02_text_block;

import org.junit.Test;

/**
 * JDK 13 文本块(PREVIEW 特性)测试
 *
 * 1. 文本块使用三引号 """...""" 声明多行字符串, 所见即所得, 无需手动拼接 \n 换行符
 * 2. 文本块会保留空白(缩进)与换行; 结束三引号所在位置的缩进决定公共缩进量,
 *    内容行比结束三引号多出的缩进空格会被保留
 * 3. 文本块属于 JDK 13 PREVIEW 特性(JEP 355), 需要 javac --enable-preview 编译,
 *    java --enable-preview 运行, 否则无法编译
 *    - JDK 13(JEP 355, PREVIEW): 首次引入文本块
 *    - JDK 14(JEP 368, PREVIEW): 再次预览, 新增 \s 等转义序列
 *    - JDK 15(JEP 378): 文本块转正为标准特性
 *
 * @author lingwh
 * @date 2026/08/05 18:29
 */
public class TextBlockTest {

    /**
     * 测试文本块(PREVIEW): 使用三引号声明多行字符串, 保留换行
     * JDK 13 PREVIEW 特性, 需要 --enable-preview 编译运行
     */
    @Test
    public void testTextBlock_Preview() {
        // 真实文本块: 使用三引号声明多行字符串, 所见即所得, 自动保留换行
        String textBlock = """
            床前明月光,
            疑是地上霜。
            举头望明月,
            低头思故乡。
            """;
        System.out.println("文本块内容: ");
        System.out.println(textBlock);
        System.out.println("--------------------------------------");
        // 文本块与传统 \n 拼接字符串内容完全一致, 只是书写方式不同
        String traditional = "床前明月光,\n疑是地上霜。\n举头望明月,\n低头思故乡。\n";
        System.out.println("文本块与传统拼接内容是否一致: " + textBlock.equals(traditional));
        System.out.println("文本块长度: " + textBlock.length());
    }

    /**
     * 测试文本块(PREVIEW): 保留空白(缩进)与换行
     * JDK 13 PREVIEW 特性, 需要 --enable-preview 编译运行
     */
    @Test
    public void testTextBlockWhitespace_Preview() {
        // 真实文本块: 结束三引号所在缩进决定公共缩进量, 内容行多出的缩进空格会被保留
        String textBlock = """
            Java
              Python
                C++
            """;
        System.out.println("含缩进的文本块内容: ");
        System.out.println(textBlock);
        System.out.println("--------------------------------------");
        // 验证缩进保留效果: 内容行比结束三引号多出的缩进空格被保留
        String traditional = "Java\n  Python\n    C++\n";
        System.out.println("文本块与传统拼接内容是否一致: " + textBlock.equals(traditional));
        System.out.println("最后一行是否以换行符结尾: " + textBlock.endsWith("\n"));
    }

    /**
     * 测试文本块(PREVIEW): 使用文本块声明 HTML 模板字符串
     * JDK 13 PREVIEW 特性, 需要 --enable-preview 编译运行
     */
    @Test
    public void testTextBlockHtml_Preview() {
        // 真实文本块: 声明 HTML 模板字符串, 相比传统 \n 拼接可读性更好
        String html = """
            <html>
                <body>
                    <p>Hello, Text Block!</p>
                </body>
            </html>
            """;
        System.out.println("HTML 文本块内容: ");
        System.out.println(html);
        System.out.println("--------------------------------------");
        // 验证 HTML 文本块内容: 每行比结束三引号多出的缩进(4 个空格)被保留
        String traditional = "<html>\n    <body>\n        <p>Hello, Text Block!</p>\n    </body>\n</html>\n";
        System.out.println("HTML 文本块与传统拼接内容是否一致: " + html.equals(traditional));
    }
}
