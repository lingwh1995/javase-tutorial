package org.bluebridge.section_23_jdk23.unit_01_markdown_doc;

import org.junit.Test;

/**
 * JDK 23 Markdown 文档注释测试(STANDARD 正式特性)
 *
 * Markdown 文档注释(Markdown Documentation Comments, JEP 467) 是 JDK 23
 * 的 STANDARD 正式特性, 无需 --enable-preview。
 *
 * 传统 Javadoc 使用 /** ... *&#47; 格式, 内容使用 HTML 标签和 @tag 标记。
 * JDK 23 引入使用 /// 标记的 Markdown 文档注释, 支持:
 *   1. 使用 /// 行标记代替 /** *&#47; 块标记
 *   2. 在文档注释中使用 Markdown 语法(标题、列表、代码块、链接等)
 *   3. 仍然支持 @param, @return, @throws 等 Javadoc 标签
 *   4. 代码块使用 ``` 围栏, 不需要 &lt;pre&gt; 等 HTML 标签
 *
 * 注意: 本文件使用 JDK 23 正式特性的真实语法编写, 使用 /// 行标记书写 Markdown 文档注释。
 *
 * 演化历程: Markdown 文档注释 JDK 23(JEP 467, STANDARD 正式特性)
 *
 * @author lingwh
 * @date 2026/08/06 09:10
 */
public class MarkdownDocCommentTest {

    /**
     * 测试传统 Javadoc 注释格式
     * 这是使用 /** ... *&#47; 格式的传统 Javadoc 注释,
     * 使用 @param 和 @return 等标签,
     * 内容中需要使用 HTML 标签如 &lt;code&gt; 等。
     * 这是 STANDARD 标准功能, 不需要 --enable-preview。
     */
    @Test
    public void testTraditionalJavadoc() {
        // 传统 Javadoc 注释是 JDK 标准功能
        // 使用 /** ... */ 格式, 内容使用 HTML 标签
        System.out.println("traditionalJavadoc 方法: 计算两个整数的和");
        int result = addTraditional(10, 20);
        System.out.println("10 + 20 = " + result);
    }

    /**
     * 传统 Javadoc 示例方法
     * @param a 第一个加数
     * @param b 第二个加数
     * @return 两个数的和
     */
    public int addTraditional(int a, int b) {
        return a + b;
    }

    // 以下是 JDK 23 使用 /// Markdown 文档注释的方法
    // 注意: 由于当前 IDE 可能不支持 /// 注释语法高亮,
    // 但是 javac 可以正常编译(JEP 467 是 JDK 23 正式特性)

    /// 测试 Markdown 文档注释格式(STANDARD)
    ///
    /// 这是使用 `///` 标记的 Markdown 文档注释。
    ///
    /// Markdown 注释支持:
    ///   - **粗体文本** 和 *斜体文本*
    ///   - `行内代码` 和 ``` 代码块 ```
    ///   - [链接文本](https://example.com)
    ///   - 无序列表和有序列表
    ///   - 标题 (通过 # 符号)
    ///
    /// ## 代码示例
    ///
    /// ```java
    /// /// 计算两个数字的和
    /// /// @param a 第一个加数
    /// /// @param b 第二个加数
    /// /// @return 两个数的和
    /// public int add(int a, int b) {
    ///     return a + b;
    /// }
    /// ```
    ///
    /// JDK 23 STANDARD 正式特性，无需 --enable-preview
    ///
    /// @see "JEP 467: Markdown Documentation Comments"
    @Test
    public void testMarkdownDocComment_Standard() {
        // JDK 23 STANDARD 正式特性，无需 --enable-preview
        // 使用 /// 标记的 Markdown 文档注释是 JDK 23 的新特性
        // 本方法的文档注释使用了 /// 语法
        System.out.println("markdownDocComment 方法: 演示 JDK 23 Markdown 文档注释");
        int result = addMarkdown(30, 40);
        System.out.println("30 + 40 = " + result);
        System.out.println("--------------------------------------");
        System.out.println("/// 注释优点: 更简洁, 不需要 HTML 标签, 支持 Markdown 语法");
        System.out.println("/// 仍然支持 @param, @return, @throws 等标准 Javadoc 标签");
    }

    /// 使用 Markdown 文档注释的计算方法(STANDARD)
    ///
    /// 计算两个整数的和并返回结果。
    ///
    /// ## 参数说明
    /// - `a` - 第一个加数
    /// - `b` - 第二个加数
    ///
    /// ## 返回值
    /// 返回 `a` 和 `b` 的和。
    ///
    /// ## 示例
    /// ```java
    /// int sum = addMarkdown(5, 3);  // 返回 8
    /// ```
    ///
    /// JDK 23 STANDARD 正式特性，无需 --enable-preview
    ///
    /// @param a 第一个加数
    /// @param b 第二个加数
    /// @return 两个数的和
    public int addMarkdown(int a, int b) {
        return a + b;
    }

    /// 测试 Markdown 文档注释中的各种格式(STANDARD)
    ///
    /// ## 标题级别演示
    ///
    /// ### 三级标题
    ///
    /// #### 四级标题
    ///
    /// ## 文本格式
    ///
    /// - **粗体**: 使用两个星号包裹
    /// - *斜体*: 使用一个星号包裹
    /// - ~~删除线~~: 使用两个波浪线包裹
    /// - `行内代码`: 使用反引号包裹
    ///
    /// ## 列表
    ///
    /// 无序列表:
    /// - 第一项
    /// - 第二项
    /// - 第三项
    ///
    /// 有序列表:
    /// 1. 第一步
    /// 2. 第二步
    /// 3. 第三步
    ///
    /// ## 代码块
    ///
    /// ```java
    /// public class Hello {
    ///     public static void main(String[] args) {
    ///         System.out.println("Hello, Markdown!");
    ///     }
    /// }
    /// ```
    ///
    /// ## 链接
    ///
    /// - [OpenJDK JEP 467](https://openjdk.org/jeps/467)
    ///
    /// JDK 23 STANDARD 正式特性，无需 --enable-preview
    @Test
    public void testMarkdownFormatting_Standard() {
        // JDK 23 STANDARD 正式特性，无需 --enable-preview
        // 本方法的文档注释演示了 Markdown 的各种格式
        System.out.println("markdownFormatting 方法: 演示 Markdown 文档注释中的各种格式");
        System.out.println("/// 注释支持: 标题、粗体、斜体、列表、代码块、链接等");
        System.out.println("/// 相比传统 Javadoc 的 HTML 标签, Markdown 更简洁易读");
    }
}