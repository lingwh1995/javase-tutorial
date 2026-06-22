package org.bluebridge.java9.chapter_07_string;

public class StringTest {
    /**
     * Java9中String底层实现由char[]改为byte[]
     *      从很多不同应用程序收集的信息表名，字符串是堆使用的主要组成部分，而且，大多数字符串对象只包含一个字符，这样的字符只需要一个字节的存储空间，因此这些字符串
     *      对象的内部char数组中有一半的空间被闲置。JDK9之前String底层使用char数组存储数据private final char value[]，JDK9将String底层存储数据改为byte
     *      数组存储数据private final byte[] value。StringBuffer和StringBuilder也同样做了变更，将以往char数组改为byte数组。
     */
    String s = "hello java";
}
