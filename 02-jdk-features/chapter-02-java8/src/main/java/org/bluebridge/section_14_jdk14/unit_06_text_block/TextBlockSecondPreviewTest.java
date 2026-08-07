package org.bluebridge.section_14_jdk14.unit_06_text_block;

import org.junit.Test;

/**
 * JDK 14 文本块第二次预览（PREVIEW 特性，JEP 368）
 * 文本块在 JDK 13 作为第一次预览特性引入（JEP 355），JDK 14 进行第二次预览（JEP 368），JDK 15 正式转正（JEP 378, STANDARD）
 * 相比 JDK 13 的变化：新增 \s 转义序列（保留尾部空格）和 \ 续行符（拼接下一行，不换行）
 * 注意：该特性在 JDK 14 为预览特性，编译和运行需要 --enable-preview
 *
 * 演化历程：JDK 13(JEP 355, 1st PREVIEW) → JDK 14(JEP 368, 2nd PREVIEW) → JDK 15(JEP 378, STANDARD)
 *
 * @author lingwh
 * @date 2026/08/06 18:39
 */
public class TextBlockSecondPreviewTest {

    /**
     * 测试文本块基本用法（JDK 14 PREVIEW 特性，需要 --enable-preview）
     */
    @Test
    public void testTextBlockBasic_Preview() {
        // 使用文本块定义多行 HTML 字符串
        String html = """
                <html>
                    <body>
                        <p>Hello, Text Block!</p>
                    </body>
                </html>
                """;
        System.out.println("HTML 文本块:");
        System.out.println(html);
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试 \ 续行符（JDK 14 新增转义序列）
     * 行尾的 \ 表示续行：拼接下一行内容，不产生换行符
     */
    @Test
    public void testLineContinuation_Preview() {
        // 行尾 \ 续行符：line1 和 line2 拼接在同一行，中间无换行
        String text = """
                line1\
                line2""";
        System.out.println("\\ 续行符拼接结果:");
        System.out.println(text);
        System.out.println("--- 分割线 ---");
    }

    /**
     * 测试 \s 转义序列（JDK 14 新增转义序列）
     * \s 表示一个空格，用于保留行尾空格（默认情况下文本块会去除行尾空白）
     */
    @Test
    public void testEscapeSpace_Preview() {
        // \s 保留尾部空格：hello 后面的空格不会被去除
        String withEscape = """
                hello \s
                world""";
        System.out.println("\\s 保留尾部空格:");
        System.out.println(withEscape);
        System.out.println("--- 分割线 ---");
    }
}
