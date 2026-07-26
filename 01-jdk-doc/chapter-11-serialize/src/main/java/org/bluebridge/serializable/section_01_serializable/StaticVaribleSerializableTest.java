package org.bluebridge.serializable.section_01_serializable;

import org.junit.Test;

import java.io.*;

/**
 * 被 static 修饰的变量(静态变量)不参与序列化过程
 *
 * @author lingwh
 * @date 2019/7/9 10:30
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
            // 初始时 staticVar 为 5
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("result.obj"));
            oos.writeObject(new StaticVaribleSerializableTest());
            oos.close();

            // 序列化后修改为 10
            StaticVaribleSerializableTest.staticVar = 10;
            ObjectInputStream ios = new ObjectInputStream(new FileInputStream("result.obj"));
            StaticVaribleSerializableTest t = (StaticVaribleSerializableTest) ios.readObject();
            ios.close();

            // 再读取，通过 t.staticVar 打印新的值，打印的值为 10，说明被 static 修饰的变量并不会参与序列化
            System.out.println(staticVar);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
