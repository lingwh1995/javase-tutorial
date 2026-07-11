package org.bluebridge.unsafe_04_object_operate;

import lombok.Data;

/**
 * @author lingwh
 * @desc 学生实体类
 * @date 2026/7/9 00:00
 */
@Data
public class Student {
    private int id;
    private String name;

    public Student() {
        this.name = "张三";
    }
}
