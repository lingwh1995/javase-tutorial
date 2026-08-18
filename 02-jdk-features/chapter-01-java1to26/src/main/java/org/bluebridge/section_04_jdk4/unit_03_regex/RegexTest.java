package org.bluebridge.section_04_jdk4.unit_03_regex;

import org.junit.Test;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * JDK 1.4 引入的 java.util.regex 包测试
 * 核心类：Pattern（正则表达式编译表示）、Matcher（匹配器）
 *
 * @author lingwh
 * @date 2026/08/05 19:02
 */
public class RegexTest {

    /**
     * 测试 Pattern 和 Matcher 的基本使用
     * Pattern.compile() 编译正则表达式
     * Matcher.matches() 尝试将整个区域与正则表达式匹配
     */
    @Test
    public void testPatternAndMatcherBasic() {
        // 编译正则表达式
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,}$");
        String email = "user@example.com";

        // 创建 Matcher 并进行匹配
        Matcher matcher = pattern.matcher(email);
        boolean isMatch = matcher.matches();
        System.out.println("邮箱 " + email + " 是否匹配：" + isMatch);

        // 不匹配的示例
        String invalidEmail = "invalid-email";
        Matcher invalidMatcher = pattern.matcher(invalidEmail);
        System.out.println("无效邮箱 " + invalidEmail + " 是否匹配：" + invalidMatcher.matches());
    }

    /**
     * 测试 matches()、find()、group() 方法
     * matches()：整个字符串匹配
     * find()：查找子串匹配
     * group()：获取匹配的分组
     */
    @Test
    public void testMatchesFindGroup() {
        String text = "我的电话号码是 010-12345678，备用电话是 021-87654321";

        // 编译正则表达式，匹配电话号码
        Pattern pattern = Pattern.compile("(\\d{3,4})-(\\d{7,8})");
        Matcher matcher = pattern.matcher(text);

        // find() 查找所有匹配的子串
        System.out.println("使用 find() 查找所有电话号码：");
        while (matcher.find()) {
            System.out.println("  完整匹配：" + matcher.group());
            System.out.println("  区号：" + matcher.group(1));
            System.out.println("  号码：" + matcher.group(2));
            System.out.println("  匹配位置：[" + matcher.start() + ", " + matcher.end() + ")");
        }

        // 测试 matches() 需要全匹配
        String phoneLine = "010-12345678";
        Matcher fullMatcher = pattern.matcher(phoneLine);
        System.out.println("matches() 全匹配结果：" + fullMatcher.matches());
    }

    /**
     * 测试 replaceAll() 和 replaceFirst() 替换方法
     */
    @Test
    public void testReplace() {
        String text = "Java 1.4 引入了正则表达式，Java 8 引入了 Lambda，Java 11 引入了新特性";

        // 替换所有匹配
        Pattern pattern = Pattern.compile("Java \\d+\\.\\d+");
        Matcher matcher = pattern.matcher(text);

        String replacedAll = matcher.replaceAll("Java 版本");
        System.out.println("replaceAll 结果：" + replacedAll);

        // 替换第一个匹配
        matcher.reset(text);
        String replacedFirst = matcher.replaceFirst("Java 版本");
        System.out.println("replaceFirst 结果：" + replacedFirst);

        // 使用 String 的 replaceAll 方法（底层也是使用 Pattern）
        String simpleReplace = text.replaceAll("Java \\d+\\.\\d+", "JDK");
        System.out.println("String.replaceAll 结果：" + simpleReplace);
    }

    /**
     * 测试 split() 分割方法
     */
    @Test
    public void testSplit() {
        String text = "苹果,香蕉;橘子|葡萄 西瓜";

        // 使用正则表达式分割字符串
        Pattern pattern = Pattern.compile("[,;|\\s]+");
        String[] fruits = pattern.split(text);
        System.out.println("分割结果：" + Arrays.toString(fruits));

        // 限制分割次数
        String[] limitedSplit = pattern.split(text, 3);
        System.out.println("限制分割次数（3次）：" + Arrays.toString(limitedSplit));

        // 使用 String 的 split 方法
        String[] simpleSplit = text.split("[,;|\\s]+");
        System.out.println("String.split 结果：" + Arrays.toString(simpleSplit));
    }

    /**
     * 测试常用正则表达式模式
     */
    @Test
    public void testCommonPatterns() {
        // 验证手机号（中国大陆）
        Pattern phonePattern = Pattern.compile("^1[3-9]\\d{9}$");
        System.out.println("手机号 13800138000 是否有效：" + phonePattern.matcher("13800138000").matches());
        System.out.println("手机号 12345678901 是否有效：" + phonePattern.matcher("12345678901").matches());

        // 验证 IP 地址
        Pattern ipPattern = Pattern.compile("^((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)$");
        System.out.println("IP 192.168.1.1 是否有效：" + ipPattern.matcher("192.168.1.1").matches());
        System.out.println("IP 256.1.1.1 是否有效：" + ipPattern.matcher("256.1.1.1").matches());

        // 验证 URL
        Pattern urlPattern = Pattern.compile("^https?://[\\w.-]+(:\\d+)?(/[\\w./%-]*)?$");
        System.out.println("URL https://www.example.com 是否有效：" + urlPattern.matcher("https://www.example.com").matches());

        // 验证日期格式 yyyy-MM-dd
        Pattern datePattern = Pattern.compile("^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$");
        System.out.println("日期 2025-12-02 是否有效：" + datePattern.matcher("2025-12-02").matches());
        System.out.println("日期 2025-13-01 是否有效：" + datePattern.matcher("2025-13-01").matches());
    }

    /**
     * 测试正则表达式标志位（Pattern flags）
     * Pattern.CASE_INSENSITIVE：不区分大小写
     * Pattern.MULTILINE：多行模式
     * Pattern.DOTALL：点号匹配所有字符（包括换行符）
     */
    @Test
    public void testPatternFlags() {
        // CASE_INSENSITIVE 不区分大小写
        Pattern caseInsensitive = Pattern.compile("java", Pattern.CASE_INSENSITIVE);
        System.out.println("不区分大小写匹配 Java：" + caseInsensitive.matcher("Java").matches());

        // MULTILINE 多行模式
        Pattern multiline = Pattern.compile("^\\d+", Pattern.MULTILINE);
        String multiLineText = "123 apple\n456 banana\n789 cherry";
        Matcher matcher = multiline.matcher(multiLineText);
        System.out.println("多行模式匹配每行开头的数字：");
        while (matcher.find()) {
            System.out.println("  " + matcher.group());
        }

        // DOTALL 模式，点号匹配换行符
        Pattern dotAll = Pattern.compile(".*", Pattern.DOTALL);
        String multiLine = "第一行\n第二行\n第三行";
        Matcher dotAllMatcher = dotAll.matcher(multiLine);
        System.out.println("DOTALL 模式匹配结果：" + dotAllMatcher.matches());
    }

    /**
     * 测试贪婪匹配与懒惰匹配
     */
    @Test
    public void testGreedyVsReluctant() {
        String text = "<div>内容1</div><div>内容2</div>";

        // 贪婪匹配：尽可能多地匹配
        Pattern greedy = Pattern.compile("<div>.*</div>");
        Matcher greedyMatcher = greedy.matcher(text);
        if (greedyMatcher.find()) {
            System.out.println("贪婪匹配结果：" + greedyMatcher.group());
        }

        // 懒惰匹配（非贪婪）：尽可能少地匹配
        Pattern reluctant = Pattern.compile("<div>.*?</div>");
        Matcher reluctantMatcher = reluctant.matcher(text);
        System.out.println("懒惰匹配结果：");
        while (reluctantMatcher.find()) {
            System.out.println("  " + reluctantMatcher.group());
        }
    }
}