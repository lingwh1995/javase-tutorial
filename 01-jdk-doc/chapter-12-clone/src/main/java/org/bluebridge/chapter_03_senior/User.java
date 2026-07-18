package org.bluebridge.chapter_03_senior;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 用户类
 *
 * @author lingwh
 * @date 2026/7/13 18:39
 */
public class User implements Cloneable {

    /**
     * 基本类型
     */
    private int id;

    /**
     * String类型
     */
    private String name;

    /**
     * 包装类型
     */
    private Integer age;

    /**
     * 引用类型
     */
    private String[] hobby;

    /**
     * 集合类型
     */
    private List<String> friends;

    /**
     * 引用类型
     */
    private Mark mark;

    @Override
    protected User clone() throws CloneNotSupportedException {
        User user = (User) super.clone();
        // 处理数组类型数据
        // 获取副本中hobby的引用，此引用和原型中hobby指向堆中同一块区域
        String[] hobby = user.getHobby();
        // 把原型中的引用指向堆中的数据复制一份
        String[] hobbyCopy = Arrays.copyOf(hobby, hobby.length);
        // 把副本中对应的引用指向这个复制的数据，这样修改副本中的引用指向的数据就不会影响到原型中的数据了
        user.setHobby(hobbyCopy);

        // 处理集合类型的数据
        // 获取副本中List的引用，此引用和原型中hobby指向堆中同一块区域
        List<String> friends = user.getFriends();
        // 把原型中的引用指向堆中的数据复制一份
        ArrayList<String> friendsCopy = new ArrayList<>(friends);
        // 把副本中对应的引用指向这个复制的数据，这样修改副本中的引用指向的数据就不会影响到原型中的数据了
        user.setFriends(friendsCopy);
        return user;
    }

    public User() {

    }

    public User(int id, String name, Integer age, String[] hobby, List<String> friends, Mark mark) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.hobby = hobby;
        this.friends = friends;
        this.mark = mark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String[] getHobby() {
        return hobby;
    }

    public void setHobby(String[] hobby) {
        this.hobby = hobby;
    }

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", hobby=" + Arrays.toString(hobby) +
                ", friends=" + friends +
                ", mark=" + mark +
                '}';
    }
}
