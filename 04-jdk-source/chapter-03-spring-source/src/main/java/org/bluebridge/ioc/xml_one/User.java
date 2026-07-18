package org.bluebridge.ioc.xml_one;

/**
 * 用户
 *
 * @author lingwh
 * @date 2026/7/13 19:02
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
