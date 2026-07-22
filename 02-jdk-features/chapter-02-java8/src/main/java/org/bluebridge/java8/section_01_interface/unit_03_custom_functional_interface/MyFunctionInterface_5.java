package org.bluebridge.java8.section_01_interface.unit_03_custom_functional_interface;

import java.util.List;

/**
 * 无参返回列表的函数式接口
 *
 * @author lingwh
 * @date 2025/12/2 15:49
 */
@FunctionalInterface
public interface MyFunctionInterface_5 {

    List<Student> op();
}
