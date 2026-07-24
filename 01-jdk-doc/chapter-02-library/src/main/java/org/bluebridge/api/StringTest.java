package org.bluebridge.api;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * String 类高级 API
 *
 * @author lingwh
 * @date 2026/4/23 16:29
 */
@Slf4j
public class StringTest {

    /**
     * 测试 String 的 split() 方法
     * 把字符串根据分隔符转换成数组
     */
    @Test
    public void testSplit() {
        String nums = "1,2,3,4,5";
        String[] numsArr = nums.split(",");
        log.info("Arrays.toString(numsArr): {}", Arrays.toString(numsArr));

        String letters = "abcdef";
        String[] letterArray = letters.split("");
        log.info("Arrays.toString(letterArray): {}", Arrays.toString(letterArray));
    }

    /**
     * 测试 String 的 indexOf() 方法
     *
     * 返回指定字符或字符串在当前字符串中第一次出现的索引位置 如果当前字符串中不包含指定的字符或字符串，则返回 -1
     */
    @Test
    public void testIndexOf() {
        String str = "abccdef";
        // 从0位置开始找
        log.debug("str.indexOf('a'): {}", str.indexOf('a'));
        log.debug("str.indexOf('cc'): {}", str.indexOf("cc"));
        log.debug("str.indexOf('d'): {}", str.indexOf('d'));

        // 从2位置开始找，返回结果是从0位置开始找的索引位置
        log.debug("str.indexOf('d'): {}", str.indexOf('d', 2));
    }

    /**
     * String 的 Join 方法
     *
     * 使用指定的拼接符号把数组/集合中每一个元素拼接起来，拼成一个字符串
     */
    @Test
    public void testJoin() {
        // 拼接数组
        String[] nums = { "123", "456", "789" };
        String arrayJoinToString = String.join("-", nums);
        log.debug("arrayJoinToString: {}", arrayJoinToString);

        // 拼接集合
        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        String listJoinToString = String.join("+", list);
        log.debug("listJoinToString: {}", listJoinToString);
    }

    /**
     * 测试 String 的 StringTokenizer 对象
     *
     * 把字符串根据分隔符转换成数组，和 split() 的区别：当分隔符是.等特殊字符时，split() 无法分割，使用 StringTokenizer 对象可以分割该对象
     */
    @Test
    public void testStringTokenizer() {
        String nums = "1,2,3,4,5";
        String separator = ",";
        StringTokenizer numStringTokenizer = new StringTokenizer(nums, separator);
        while (numStringTokenizer.hasMoreTokens()) {
            String numToken = numStringTokenizer.nextToken();
            log.debug("numToken: {}", numToken);
        }
    }

    /**
     * 判断两个字符串是否相等，不区分大小写的情况
     */
    @Test
    public void testEqualsIgnoreCase() {
        String s1 = "HELLO";
        String s2 = "hello";
        log.debug("s1.equals(s2): {}", s1.equals(s2));
        log.debug("s1.equalsIgnoreCase(s2): {}", s1.equalsIgnoreCase(s2));
    }

    /**
     * 返回索引位置的字符的 ascii 码
     */
    @Test
    public void testcCodePoint() {
        String str = "abcde";
        log.debug("str.codePointAt(0): {}", str.codePointAt(0));
    }

    /**
     * 返回索引位置的前一个字符的 ascii 码，如果前面没有字符，即 index<=0 抛出下标越界异常
     */
    @Test
    public void testCodePointBefore() {
        String str = "abcde";
        log.debug("str.codePointBefore(1): {}", str.codePointBefore(1));
    }

    /**
     * 从指定索引位置开始算，返回指定索引位置到结尾处的码点数 码点和长度的区别： 这个 emoj 😂 的编码是 \uD83D 和 \uDE02，这个 emoj 的长度是 2，码点数是 1
     */
    @Test
    public void testCodePointCount() {
        String str = "abcde\uD83D\uDE02";
        log.debug("str.length(): {}", str.length());
        log.debug("str.codePointCount(1, str.length()): {}", str.codePointCount(1, str.length()));
    }

    /**
     * valueOf 和 toString()
     *
     * valueOf()源码:
     * public static String valueOf(Object obj) {
     * return (obj == null) ? "null" : obj.toString();
     * }
     */
    @Test
    public void testValueOfAndToString() {
        // valueOf():不会抛空指针异常
        Integer num = null;
        log.debug("String.valueOf(num): {}", String.valueOf(num));
        // toString():会跑出空指针异常
    }

    @Test
    public void testConcat() {
        String str1 = "abc";
        String str2 = "de";
        log.debug("str1.concat(str2): {}", str1.concat(str2));
    }

    @Test
    public void testReplace() {
        String str1 = "abcdea";
        // 替换全部target字符串，不支持正则
        log.debug(str1.replace("a", "a_"));

        // 替换全部符合正则表达式的字符串
        // public String replaceAll(String regex, String replacement)
    }

    /**
     * 测试编译期优化
     */
    @Test
    public void testCompileOptimize() {
        // 在编译期会进行优化，编译期优化后相当于 String s1 = "qe";
        String s1 = "q" + "e";
        String s2 = "3";
        // 编译时期不会做优化，因为编译的时候还不知道s1和s3代表的具体值是什么
        String s3 = s1 + s2;
        log.debug("s3: {}", s3);
    }

    /**
     * 测试字符串格式化
     */
    @Test
    public void testFormat() {
        String str = "abcde";
        // 左补0
        log.debug("左补0： {}", String.format("%10s", str).replace(' ', '0'));
        // 右补0
        log.debug("右补0： {}", String.format("%-10s", str).replace(' ', '0'));
    }

    /**
     * 测试去掉字符串两端空格
     */
    @Test
    public void testTrim() {
        String str = " abcde ";
        log.debug("str.trim(): {}", str.trim());
    }

    /**
     * 测试把字符串转换为字符流
     */
    @Test
    public void testChars() {
        String str = " abcde ";
        str.chars().forEach(System.out::println);
    }

    /**
     * 测试字符串重复
     */
    @Test
    public void testRepeat() {
        String str = "12345_";
        log.info("str.repeat(3): {}", str.repeat(3));

        // 创建分隔线或装饰线
        String line = "-".repeat(50);
        log.info(line);

        // 创建分隔线或装饰线
        String header = "=".repeat(20) + " 标题 " + "=".repeat(20);
        log.info(header);
    }
}
