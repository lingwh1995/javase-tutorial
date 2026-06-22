package org.bluebridge.java8.chapter_08_string;

import java.util.StringJoiner;

public class StringJoinerTest {
    public static void main(String[] args) {
        StringJoiner stringJoiner = new StringJoiner("-", "(", ")");
        stringJoiner.add("1");
        stringJoiner.add("2");
        stringJoiner.add("3");
        System.out.println(stringJoiner.toString());
    }

}
