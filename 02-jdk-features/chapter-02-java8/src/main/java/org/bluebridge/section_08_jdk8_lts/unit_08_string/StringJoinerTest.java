package org.bluebridge.section_08_jdk8_lts.unit_08_string;

import java.util.StringJoiner;

/**
 * StringJoiner 使用示例
 *
 * @author lingwh
 * @date 2025/12/2 15:10
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
