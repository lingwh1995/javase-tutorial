package org.bluebridge.section_08_jdk8_lts.unit_01_interface.demo_03_custom_functional_interface;

/**
 * 带泛型的函数式接口（输入转输出）
 *
 * @author lingwh
 * @date 2025/12/2 15:57
 */
@FunctionalInterface
public interface MyFunctionInterface_7<I, O> {

    O op(I i);
}
