package org.bluebridge.cas_06_atomic_field_updater;

/**
 * @author lingwh
 * @desc 学生实体类
 * @date 2026/7/9 00:00
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
