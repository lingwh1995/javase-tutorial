package org.bluebridge.reflect.section_04_reflect_gener;

/**
 * 反射操作的类
 *
 * @author lingwh
 * @date 2026/6/22 18:04
 */
public class Person {

    private String name;
    private String age;

    public Person() {
        System.out.println("我是无参构造方法...");
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
        System.out.println(drinkName);
    }

    /**
     * 私有方法
     *
     * @param bedName 床名称
     */
    private static int sleep(String bedName) {
        System.out.println("在" + bedName + "睡觉......");
        return 10;
    }
}
