package org.bluebridge.section_01_byte_stream.unit_03_object_input_stream_object_output_stream;

import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * ObjectStream 用于实现序列化和饭序列化
 *
 * @author lingwh
 * @date 2025/8/16 13:41
 */
public class Employee implements Serializable {

    private static final long serialVersionUID = -167978670073609475L;

    /**
     * 被transient修饰的属性不会被序列化
     */
    private transient String name;

    private double salary;
    private Date hireDay;

    public Employee(String name, double salary, int year, int month, int dayOfMonth) {
        this.name = name;
        this.salary = salary;
        GregorianCalendar calendar = new GregorianCalendar(year, month - 1, dayOfMonth);
        this.hireDay = calendar.getTime();
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public Date getHireDay() {
        return hireDay;
    }

    public void raiseSalary(double byPercent) {
        double raise = salary * byPercent / 100;
        salary += raise;
    }

    @Override
    public String toString() {
        return getClass().getName() + " [name=" + name + ", salary=" + salary + ", hireDay=" + hireDay + "]";
    }
}
