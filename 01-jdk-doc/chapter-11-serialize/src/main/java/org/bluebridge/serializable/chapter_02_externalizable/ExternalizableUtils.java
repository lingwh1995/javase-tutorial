package org.bluebridge.serializable.chapter_02_externalizable;

import java.io.*;

/**
 * @author lingwh
 * @desc Externalizable工具类
 * @date 2026/7/9 00:00
 */
public class ExternalizableUtils {
    /**
     * 序列化方法
     *
     * @throws IOException
     * @throws FileNotFoundException
     */
    public void serializable(Person person) throws FileNotFoundException, IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("d:/data"));
        oos.writeObject(person);
        oos.close();
    }

    /**
     * 反序列化的方法
     *
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     */
    public Person deSerializable() throws FileNotFoundException, IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("d:/data"));
        return (Person) ois.readObject();
    }
}
