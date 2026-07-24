package org.bluebridge.java8.section_08_string;

import java.util.StringJoiner;

/**
 * StringJoiner 使用示例
 *
 * @author lingwh
 * @date 2026/6/22 15:10
 */
public class StringJoinerTest {

    public static void main(String[] args) {
        StringJoiner stringJoiner = new StringJoiner("-", "(", ")");
        stringJoiner.add("1");
        stringJoiner.add("2");
        stringJoiner.add("3");
        System.out.println(stringJoiner.toString());
    }
}
