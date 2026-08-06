package org.bluebridge.section_07_jdk7.unit_06_string_switch;

import org.junit.Test;

/**
 * JDK 7 引入的字符串 switch 测试
 *
 * @author lingwh
 * @date 2026/08/05 19:02
 */
public class StringSwitchTest {

    /**
     * 测试 switch 语句中使用 String 表达式
     */
    @Test
    public void testStringSwitch() {
        String day = "MONDAY";
        // JDK 7 之前，switch 只能使用 byte、short、char、int 和枚举类型
        // JDK 7 开始，switch 支持 String 类型
        switch (day) {
            case "MONDAY":
                System.out.println("今天是星期一");
                break;
            case "TUESDAY":
                System.out.println("今天是星期二");
                break;
            case "WEDNESDAY":
                System.out.println("今天是星期三");
                break;
            case "THURSDAY":
                System.out.println("今天是星期四");
                break;
            case "FRIDAY":
                System.out.println("今天是星期五");
                break;
            case "SATURDAY":
                System.out.println("今天是星期六");
                break;
            case "SUNDAY":
                System.out.println("今天是星期日");
                break;
            default:
                System.out.println("无效的星期: " + day);
                break;
        }
    }

    /**
     * 测试字符串 switch 的 case 匹配（大小写敏感）
     */
    @Test
    public void testStringSwitchCaseSensitive() {
        String fruit = "apple";

        // switch 中的字符串比较是大小写敏感的
        switch (fruit) {
            case "apple":
                System.out.println("苹果");
                break;
            case "Apple":
                System.out.println("Apple（大写）");
                break;
            case "APPLE":
                System.out.println("APPLE（全大写）");
                break;
            default:
                System.out.println("未知水果: " + fruit);
                break;
        }
    }

    /**
     * 测试字符串 switch 处理 null 值
     */
    @Test
    public void testStringSwitchWithNull() {
        String str = null;

        // 注意：switch 表达式为 null 时会抛出 NullPointerException
        // 因此在使用前需要做 null 检查
        if (str == null) {
            System.out.println("字符串为 null，无法进行 switch 匹配");
            return;
        }

        switch (str) {
            case "hello":
                System.out.println("你好");
                break;
            case "world":
                System.out.println("世界");
                break;
            default:
                System.out.println("未知字符串: " + str);
                break;
        }
    }

    /**
     * 测试字符串 switch 结合多个 case 匹配同一分支
     */
    @Test
    public void testStringSwitchMultiCase() {
        String season = "spring";

        // 多个 case 匹配同一个分支
        switch (season) {
            case "spring":
            case "springtime":
                System.out.println("春季");
                break;
            case "summer":
            case "summertime":
                System.out.println("夏季");
                break;
            case "autumn":
            case "fall":
                System.out.println("秋季");
                break;
            case "winter":
                System.out.println("冬季");
                break;
            default:
                System.out.println("未知季节: " + season);
                break;
        }
    }

    /**
     * 测试对比传统 if-else 写法
     */
    @Test
    public void testTraditionalIfElse() {
        String color = "red";
        String description;

        // 传统 if-else 链写法
        if ("red".equals(color)) {
            description = "红色";
        } else if ("green".equals(color)) {
            description = "绿色";
        } else if ("blue".equals(color)) {
            description = "蓝色";
        } else {
            description = "未知颜色";
        }
        System.out.println("if-else 结果: " + description);

        // 使用 switch 的写法更简洁
        switch (color) {
            case "red":
                description = "红色";
                break;
            case "green":
                description = "绿色";
                break;
            case "blue":
                description = "蓝色";
                break;
            default:
                description = "未知颜色";
                break;
        }
        System.out.println("switch 结果: " + description);
    }
}