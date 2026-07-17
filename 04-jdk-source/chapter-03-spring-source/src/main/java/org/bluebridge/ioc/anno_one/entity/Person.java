package org.bluebridge.ioc.anno_one.entity;

/**
 * 人员实体
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class Person {

    private Student student;
    private Teacher teacher;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "Person [student=" + student + ", teacher=" + teacher + "]";
    }
}
