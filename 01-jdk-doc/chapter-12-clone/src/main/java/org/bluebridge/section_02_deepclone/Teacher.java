package org.bluebridge.section_02_deepclone;

import java.io.Serializable;

/**
 * 教师类
 *
 * @author lingwh
 * @date 2026/7/8 18:39
 */
public class Teacher implements Serializable {

    private int id;
    private Integer age;
    private String name;

    public Teacher() {
    }

    public Teacher(int id, Integer age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
