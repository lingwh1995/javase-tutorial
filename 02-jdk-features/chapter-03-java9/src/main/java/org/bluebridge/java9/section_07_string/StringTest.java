package org.bluebridge.java9.section_07_string;

/**
 * Java9 String 测试
 *
 * 1. Java9 中 String 底层实现由 char[] 改为 byte[]
 * 2. 从很多不同应用程序收集的信息表名，字符串是堆使用的主要组成部分，而且，大多数字符串对象只包含一个字符，这样的字符只需要一个字节的存储空间，因此这些字符串
 *    对象的内部 char 数组中有一半的空间被闲置。JDK9 之前 String 底层使用 char 数组存储数据 private final char value[]，JDK9 将 String 底层存储数据改为 byte 数
 *    组存储数据 private final byte[] value。StringBuffer 和 StringBuilder 也同样做了变更，将以往 char 数组改为 byte 数组。
 *
 * @author lingwh
 * @date 2025/1/24 15:10
 */
public class StringTest {

    String s = "hello java";
}
