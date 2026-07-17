package org.bluebridge.lang3;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.Test;

import java.io.Serializable;

/**
 * 序列化工具类
 *
 * @author lingwh
 * @date 2019/7/26 16:31
 */
@Slf4j
public class SerializationUtilsTest {

    @Test
    public void testSerializationUtils() {
        Person zhangsan = new Person("zhangsan", "28");
        byte[] serialize = SerializationUtils.serialize(zhangsan);
        log.info("serialize: {}", serialize);
        Person deserialize = (Person) SerializationUtils.deserialize(serialize);
        log.info("deserialize: {}", deserialize);
    }

    @Data
    @AllArgsConstructor
    private static class Person implements Serializable {
        private static final long serialVersionUID = 1L;

        private String name;
        private String age;
    }
}
