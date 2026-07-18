package org.bluebridge.ioc.anno_one.entity;

/**
 * 教师实体
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
