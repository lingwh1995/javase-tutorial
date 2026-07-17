package org.bluebridge.reflect.chapter_06_reflect_demo.lesson_03_my_orm;

/**
 * 学生实体类
 *
 * @author lingwh
 * @date 2026/6/22 18:04
 */
@Table(value = "TB_STUDENT")
public class Student {

    @Column(cloumnName = "ID", dataType = "VARCHAR2", length = 32)
    private String id;

    @Column(cloumnName = "NAME", dataType = "VARCHAR2", length = 32)
    private String name;

    @Column(cloumnName = "AGE", dataType = "NUMBER", length = 5)
    private String age;

    public Student() {
    }

    public Student(String id, String name, String age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", age=" + age + "]";
    }
}
