package org.bluebridge.java8.chapter_01_interface.chapter_03_custom_functional_interface;

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
