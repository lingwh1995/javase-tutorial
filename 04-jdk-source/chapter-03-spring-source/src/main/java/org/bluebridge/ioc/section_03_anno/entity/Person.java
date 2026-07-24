package org.bluebridge.ioc.section_03_anno.entity;

/**
 * 人员实体
 *
 * @author lingwh
 * @date 2019/3/15 10:30
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
