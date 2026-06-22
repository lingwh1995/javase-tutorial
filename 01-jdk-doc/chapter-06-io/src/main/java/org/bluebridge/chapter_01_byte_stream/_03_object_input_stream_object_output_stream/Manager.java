package org.bluebridge.chapter_01_byte_stream._03_object_input_stream_object_output_stream;

/**
 * @author lingwh
 * @desc ObjectStream 用于实现序列化和饭序列化
 * @date 2025/8/16 13:42
 */
public class Manager extends Employee{
    private static final long serialVersionUID = -668252664793507835L;
    private Employee secretary;

    public Manager(String name, double salary, int year, int month, int dayOfMonth) {
        super(name, salary, year, month, dayOfMonth);
    }

    public void setSecretary(Employee secretary) {
        this.secretary = secretary;
    }

    @Override
    public String toString() {
        return super.toString() + " [secretary=" + secretary + "]";
    }
}
