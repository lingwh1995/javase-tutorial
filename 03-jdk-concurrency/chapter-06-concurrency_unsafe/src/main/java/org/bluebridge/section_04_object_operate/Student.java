package org.bluebridge.section_04_object_operate;

import lombok.Data;

/**
 * 学生实体类
 *
 * @author lingwh
 * @date 2026/7/13 11:45
 */
@Data
public class Student {

    private int id;
    private String name;

    public Student() {
        this.name = "张三";
    }
}
