package org.bluebridge.section_06_atomic_field_updater;

/**
 * 学生实体类
 *
 * @author lingwh
 * @date 2026/4/21 15:15
 */
public class Student {

    volatile int id;
    volatile String name;

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

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}
