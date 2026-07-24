package org.bluebridge.beanutils.test;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.bluebridge.beanutils.entity.Person;
import org.bluebridge.beanutils.entity.User;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * BeanUtils 工具类测试
 *
 * @author lingwh
 * @date 2019/6/20 14:27
 */
@Slf4j
public class BeanUtilsTest {

    /**
     * 把 Map 数据封装到 javaBean 中，此方法多用于处理表单数据
     */
    @Test
    public void populate() {
        try {
            HashMap<String, String> beanMap = new HashMap<String, String>();
            beanMap.put("id", "10002");
            beanMap.put("name", "lisi");
            beanMap.put("age", "32");
            beanMap.put("school", "ufe");
            User user = new User();
            BeanUtils.populate(user, beanMap);
            log.info("user: {}", user);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用 BeanUtils 给属性设置值
     */
    @Test
    public void copyProperty() {
        try {
            // 给 User 的 age 属性设置一个 Integer 类型的值，不涉及类型自动转换
            Integer ageValueInteger = 28;
            User user1 = new User();
            BeanUtils.copyProperty(user1, "age", ageValueInteger);
            log.info("不涉及类型自动转换: {}", user1);

            // 给 User 的 age 属性设置一个 String 类型的值，涉及类型自动转换
            String ageValueString = "49";
            User user2 = new User();
            BeanUtils.copyProperty(user2, "age", ageValueString);
            log.info("涉及类型自动转换: {}", user2);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用 BeanUtils 复制对象中数据
     */
    @Test
    public void copyPropertiesBeanToBean() {
        try {
            User source = new User("10001", "zhangsan", 18, "ufe");
            User target = new User();
            BeanUtils.copyProperties(target, source);
            log.info("target: {}", target);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用 BeanUtils 把 Map 中数据封装到 Bean 中/或者说将 Map 装换为 JavaBean 对象
     *
     * 注意
     *    age：String 类型自动转换为 Integer 类型了
     *    school：key 值应该是 school，不是 school1，key 不对应，不会报错，但是值封装不进去
     */
    @Test
    public void copyPropertiesMapToBean() {
        try {
            HashMap<String, String> beanMap = new HashMap<String, String>();
            beanMap.put("id", "10002");
            beanMap.put("name", "lisi");
            beanMap.put("age", "32");
            beanMap.put("school1", "ufe");
            User user = new User();
            BeanUtils.copyProperties(user, beanMap);
            log.info("user: {}", user);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 把 Map 中数据封装到 Bean 中，并进行时间格式转换
     */
    @Test
    public void copyPropertiesWithDateFormate() {
        try {
            Map<String, Object> beanMap = new HashMap<String, Object>();
            beanMap.put("id", "10003");
            beanMap.put("userName", "刘诗华");
            beanMap.put("password", "123456");
            beanMap.put("hireDate", "2018/11/19");

            // 目标数据
            Person person = new Person();

            // 时间数据格式对象
            DateConverter converter = new DateConverter();

            // converter.setPattern("yyyy-MM-dd HH:mm:ss"); // 单个数据格式
            // 一组时间格式
            String[] pattern = new String[3];
            pattern[0] = "yyyy-MM-dd HH:mm:ss";
            pattern[1] = "yyyy-MM-dd";
            pattern[2] = "yyyy/MM/dd";
            converter.setPatterns(pattern);

            // 如果 Id 上面没有数据，则设置为 null
            IntegerConverter integerConverter = new IntegerConverter(10003);
            ConvertUtils.register(integerConverter, Integer.class);

            // 注册 Date 时间对象格式
            ConvertUtils.register(converter, Date.class);
            // 开始复制数据信息
            BeanUtils.copyProperties(person, beanMap);
            log.info("person: {}", person);
            // User(id=null, userName=刘诗华, password=123456, hireDate=Mon Nov 19 00:00:00 CST 2018)
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用 BeanUtils 给属性设置值
     */
    @Test
    public void setProperty() {
        try {
            // 给 User 的 age 属性设置一个 String 类型的值，涉及类型自动转换
            String ageValueString = "49";
            User user = new User();
            BeanUtils.setProperty(user, "age", ageValueString);
            log.info("涉及类型自动转换: {}", user);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用 BeanUtils 给属性设置值
     */
    @Test
    public void getProperty() {
        try {
            User user = new User("10001", "zhangsan", 18, "ufe");
            String age = BeanUtils.getProperty(user, "age");
            log.info("age: {}", age);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用 BeanUtils 克隆 JavaBean
     */
    @Test
    public void cloneBean() {
        try {
            User user = new User("10001", "zhangsan", 18, "ufe");
            User userClone = (User) BeanUtils.cloneBean(user);
            log.info("user: {}", user);
            log.info("userClone: {}", userClone);
        } catch (IllegalAccessException
                | InstantiationException
                | InvocationTargetException
                | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
