package org.bluebridge.ioc.anno_one.entity;

/**
 * @author lingwh
 * @desc 教师实体
 * @date 2019/3/15 00:00
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
