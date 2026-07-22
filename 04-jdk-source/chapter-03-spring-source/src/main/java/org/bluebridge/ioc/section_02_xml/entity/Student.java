package org.bluebridge.ioc.section_02_xml.entity;

/**
 * 学生
 *
 * @author lingwh
 * @date 2019/3/15 19:02
 */
public class Student {

    private String name;
    private Integer age;

    /**
     * 创建一个新的实例 Student.
     *
     */
    public Student() {
        super();
        System.out.println("Studeten...构造函数...");
    }

    /**
     * 创建一个新的实例 Student.
     *
     * @param name
     * @param age
     */
    public Student(String name, Integer age) {
        super();
        this.name = name;
        this.age = age;
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

    @Override
    public String toString() {
        return "Student [name=" + name + ", age=" + age + "]";
    }

}
