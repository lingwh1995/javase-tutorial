package org.bluebridge.java8.chapter_01_interface.chapter_03_custom_functional_interface;

import java.util.List;

/**
 * @author lingwh
 * @desc 无参返回列表的函数式接口
 * @date 2025/12/2 15:49
 */
@FunctionalInterface
public interface MyFunctionInterface_5 {
    List<Student> op();
}
