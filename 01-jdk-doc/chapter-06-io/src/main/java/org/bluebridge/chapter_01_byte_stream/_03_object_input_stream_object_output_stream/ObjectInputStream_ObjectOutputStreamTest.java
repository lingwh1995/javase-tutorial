package org.bluebridge.chapter_01_byte_stream._03_object_input_stream_object_output_stream;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.*;

/**
 * @author lingwh
 * @desc ObjectStream 用于实现序列化和反序列化
 * @date 2025/8/16 13:39
 */
@Slf4j
public class ObjectInputStream_ObjectOutputStreamTest {

    /**
     * 序列化基本类型数据
     */
    @Test
    public void testSerializableBasicData() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("d:/io/object_stream.txt"));
            oos.writeBoolean(true);
            oos.writeDouble(0.5);
            oos.writeUTF("&");
            //如果输出流不关闭,则会抛出:java.io.EOFException
            oos.close();

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("d:/io/object_stream.txt"));
            log.info("ois.readBoolean()：{}", ois.readBoolean());
            log.info("ois.readDouble()：{}", ois.readDouble());
            log.info("ois.readUTF()：{}", ois.readUTF());
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 序列化引用类型数据
     */
    @Test
    public void testSerializableReferenceData() {
        try {
            Employee harry = new Employee("Tom", 20000, 2015, 7, 10);
            Manager carl = new Manager("Carl Cracker", 80000, 1987, 12, 15);
            carl.setSecretary(harry);
            Manager tony = new Manager("Tony Tester", 40000, 1990, 3, 15);
            tony.setSecretary(harry);
            Employee[] staff = new Employee[3];
            staff[0] = harry;
            staff[1] = carl;
            staff[2] = tony;
            String filePath = "d:/io/employee.dat";
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));
            oos.writeObject(staff);
            oos.close();

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
            Employee[] employees = (Employee[]) ois.readObject();
            for (Employee employee : employees) {
                log.info("employee: {}", employee);
            }
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
