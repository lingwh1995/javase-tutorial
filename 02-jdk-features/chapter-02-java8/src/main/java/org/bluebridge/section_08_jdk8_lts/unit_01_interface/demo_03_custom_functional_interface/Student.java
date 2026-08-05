package org.bluebridge.section_08_jdk8_lts.unit_01_interface.demo_03_custom_functional_interface;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 学生实体类
 *
 * @author lingwh
 * @date 2025/12/2 15:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    private String name;
    private int age;
}
