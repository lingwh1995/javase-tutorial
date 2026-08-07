package org.bluebridge.section_15_jdk15.unit_01_text_block;

import org.junit.Test;

/**
 * JDK 15 文本块（STANDARD 特性，JEP 378）
 * 文本块在 JDK 13 作为预览特性引入，JDK 14 再次预览，JDK 15 正式转正
 *
 * 演化历程: 文本块 JDK 13(1st PREVIEW) → JDK 14(2nd PREVIEW) → JDK 15(JEP 378, STANDARD)
 *
 * @author lingwh
 * @date 2026/08/05 19:11
 */
public class TextBlockStandardTest {

    /**
     * 测试文本块基本用法：多行 HTML 字符串
     */
    @Test
    public void testTextBlockHtml() {
        // 使用文本块定义多行 HTML
        String html = """
                <html>
                    <body>
                        <p>Hello, World!</p>
                    </body>
                </html>
                """;
        System.out.println("HTML 文本块:");
        System.out.println(html);
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试文本块：JSON 字符串
     */
    @Test
    public void testTextBlockJson() {
        // 使用文本块定义多行 JSON（比传统拼接更清晰）
        String json = """
                {
                    "name": "Java",
                    "version": 15,
                    "features": ["Text Block", "Sealed Class", "EdDSA"],
                    "isReleased": true
                }
                """;
        System.out.println("JSON 文本块:");
        System.out.println(json);
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试文本块保留空白和换行
     */
    @Test
    public void testTextBlockWhitespace() {
        // 文本块保留缩进和换行，去除公共前导空白
        String code = """
                public class Hello {
                    public static void main(String[] args) {
                        System.out.println("Hello, Text Block!");
                    }
                }
                """;
        System.out.println("代码文本块:");
        System.out.println(code);
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试文本块中的格式化（String.formatted()）
     */
    @Test
    public void testTextBlockFormatted() {
        // 文本块支持使用 %s 占位符，通过 formatted() 方法传入参数
        String name = "Java 15";
        String version = "15";
        String template = """
                Language: %s
                Version:  %s
                Status:   Released
                """.formatted(name, version);
        System.out.println("格式化后的文本块:");
        System.out.println(template);
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试文本块中的转义序列
     */
    @Test
    public void testTextBlockEscape() {
        // 文本块中可以使用 \n、\t 等转义字符
        // 使用 \s 表示一个空格（JDK 14 引入的转义序列）
        // 使用 \ 行尾表示不换行（JDK 14 引入的转义序列）
        String text = """
                第一行\
                续接在同一行
                第二行
                带缩进的内容\t缩进后
                """;
        System.out.println("转义序列测试:");
        System.out.println(text);
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试文本块与传统字符串拼接对比
     */
    @Test
    public void testTextBlockComparison() {
        // 传统方式：字符串拼接，可读性差
        String traditional = "<html>\n" +
                "    <body>\n" +
                "        <p>Hello, World!</p>\n" +
                "    </body>\n" +
                "</html>\n";

        // 文本块方式：更直观，更易维护
        String textBlock = """
                <html>
                    <body>
                        <p>Hello, World!</p>
                    </body>
                </html>
                """;

        System.out.println("传统方式:");
        System.out.println(traditional);
        System.out.println("文本块方式:");
        System.out.println(textBlock);
        System.out.println("两者是否相等: " + traditional.equals(textBlock));
        System.out.println("--- 分割线 ---");
    }
}