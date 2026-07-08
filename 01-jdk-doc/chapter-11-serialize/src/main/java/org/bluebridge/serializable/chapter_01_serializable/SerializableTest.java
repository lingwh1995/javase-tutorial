package org.bluebridge.serializable.chapter_01_serializable;

import org.junit.Test;

import java.io.*;

/**
 * 使用对象序列化流实现序列化和反序列化
 * 	对象序列化流:把对象像流一样存入文本文件或者在网络中传输
 */
public class SerializableTest {
	
	/**
	 * 测试使用对象流完成序列化和反序列化
	 * @throws Exception 
	 */
    @Test
    public void testSerialize() throws Exception {
    	Person person = new Person();
    	person.setUserName("张三");
    	person.setPassword("123456");
    	person.setAge("18");
    	//执行序列化
    	serialize(person);
    	//执行反序列化
    	Person deserialize = deserialize();
    	System.out.println(deserialize);
    }

    /**
     * 使用对象流序列化
     */
    public void serialize(Person person) throws IOException {
        // ObjectOutputStream 对象输出流，将 flyPig 对象存储到E盘的 flyPig.txt 文件中，完成对 flyPig 对象的序列化操作
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("d:/data")));
        oos.writeObject(person);
        System.out.println("Person对象序列化成功！");
        oos.close();
    }

    /**
     * 使用对象流反序列化
     */
    public Person deserialize() throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("d:/data")));
        Person person = (Person) ois.readObject();
        System.out.println("Person对象反序列化成功！");
        return person;
    }
}
