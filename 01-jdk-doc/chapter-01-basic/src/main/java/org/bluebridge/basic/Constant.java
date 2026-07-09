package org.bluebridge.basic;

import java.util.ArrayList;
import java.util.List;

/**
 * Java 中的常量
 *
 * 定义语法
 *   public/private + static + final + 数据类型 + 全大写下划线命名
 *
 * 注意事项
 *   1. 常量只能被初始化一次
 *   2. 初始化的时机可以自由控制，不一定定义时必须初始化
 */
public final class Constant {

    public static void main(String[] args) {
        System.out.println(IP_ADDR);
        System.out.println(MAX_SIZE);

        System.out.println(FILE_NAME);
        System.out.println(NUMS);
    }

    /**
     * 定义常量并直接初始化
     */
    private static final String IP_ADDR = "192.168.0.10";
    private static final int MAX_SIZE = 1024;

    /**
     * 定义常量并延迟初始化
     */
    public static final String FILE_NAME;
    public static final List<Integer> NUMS;

    static {
        FILE_NAME = "file.txt";
        NUMS = new ArrayList<>();
        NUMS.add(1);
        NUMS.add(2);
        NUMS.add(3);
    }
}
