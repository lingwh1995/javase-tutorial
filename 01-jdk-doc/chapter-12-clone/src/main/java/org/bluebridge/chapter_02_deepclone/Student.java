package org.bluebridge.chapter_02_deepclone;

import java.io.*;

/**
 * 使用序列化实现深克隆
 *
 * @author lingwh
 * @date 2019/7/12 16:21
 */
public class Student implements Serializable {

    private int id;
    private String name;
    private Integer age;
    private Teacher teacher;

    public Student() {
    }

    public Student(int id, String name, Integer age, Teacher teacher) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.teacher = teacher;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "Student{"
                + "id="
                + id
                + ", name='"
                + name
                + '\''
                + ", age="
                + age
                + ", teacher="
                + teacher
                + '}';
    }
}
