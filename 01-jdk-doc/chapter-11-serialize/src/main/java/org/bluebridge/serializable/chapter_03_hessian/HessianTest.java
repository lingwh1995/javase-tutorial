package org.bluebridge.serializable.chapter_03_hessian;


import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author ronin
 */
public class HessianTest {
   
	/**
	 * 测试Hessian序列化
	 */
	@Test
	public void testHessian() {
        Person person = new Person("zs", "123456","35");
        //序列化
        byte[] serialize = serialize(person);
        //反序列化
        Person deserialize = (Person)deserialize(serialize);
        System.out.println(deserialize);
	}

	/**
	 * 序列化
	 * @param <T>
	 * @param obj
	 * @return
	 */
    public static <T> byte[] serialize(T obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        HessianOutput ho = new HessianOutput(bos);
        try {
            //obj必须实现Serializable接口
        	ho.writeObject(obj);
            bytes = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    /**
     * 反序列化
     * @param <T>
     * @param data
     * @return
     */
    public static <T> T deserialize(byte[] data) {
        if (data == null) {
            return null;
        }
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        HessianInput hi = new HessianInput(bis);
        Object object = null;
        try {
            object = hi.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (T)object;
    }
}
