package org.bluebridge.ioc.xml_two.entity;

/**
 * @author lingwh
 * @desc 人
 * @date 2019/3/15 00:00
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
