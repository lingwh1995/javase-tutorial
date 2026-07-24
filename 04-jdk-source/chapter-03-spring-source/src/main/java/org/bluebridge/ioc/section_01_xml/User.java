package org.bluebridge.ioc.section_01_xml;

/**
 * 用户
 *
 * @author lingwh
 * @date 2019/3/13 10:30
 */
public class User {

    private String name;
    private String age;

    /**
     * 创建一个新的实例 User.
     *
     */
    public User() {

    }

    /**
     * 创建一个新的实例 User.
     *
     * @param name
     * @param age
     */
    public User(String name, String age) {
        super();
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
        return "User [name=" + name + ", age=" + age + "]";
    }
}
