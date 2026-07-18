package org.bluebridge.ioc.xml_two.entity;

/**
 * 老师
 *
 * @author lingwh
 * @date 2019/3/15 19:02
 */
public class Teacher {

    private Student student;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Teacher [student=" + student + "]";
    }
}
