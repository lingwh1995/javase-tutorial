package org.bluebridge.reflect.chapter_01_reflect_basic;

import lombok.extern.slf4j.Slf4j;

/**
 * Person 实体
 *
 * @author lingwh
 * @date 2019/3/10 11:00
 */
@Slf4j
public class Person {

    private String name;
    private String age;

    public Person() {
        log.info("无参构造方法......");
    }

    public Person(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" + "name='" + name + '\'' + ", age='" + age + '\'' + '}';
    }

    /**
     * 私有方法
     *
     * @param drinkName 饮料名称
     */
    private void drink(String drinkName) {
        log.info("正在喝{}......", drinkName);
    }

    /**
     * 私有方法
     *
     * @param bedName 床名称
     */
    private static int sleep(String bedName) {
        log.info("在{}睡觉......", bedName);
        return 10;
    }
}
