package org.bluebridge.serializable.section_01_serializable;

import org.junit.Test;

import java.io.*;

/**
 * 被static修饰的变量(静态变量)不参与序列化过程
 *
 * @author lingwh
 * @date 2026/7/8 18:39
 */
public class StaticVaribleSerializableTest implements Serializable {
    private static final long serialVersionUID = 1L;

    public static int staticVar = 5;

    /**
     * 测试静态变量序列化
     */
    @Test
    public void testStaticVarSerializable() throws FileNotFoundException {
        try {
            // 初始时staticVar为5
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("result.obj"));
            oos.writeObject(new StaticVaribleSerializableTest());
            oos.close();

            // 序列化后修改为10
            StaticVaribleSerializableTest.staticVar = 10;
            ObjectInputStream ios = new ObjectInputStream(new FileInputStream("result.obj"));
            StaticVaribleSerializableTest t = (StaticVaribleSerializableTest) ios.readObject();
            ios.close();

            // 再读取，通过t.staticVar打印新的值,打印的值为10，说明被static修饰的变量并不会参与序列化
            System.out.println(t.staticVar);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
