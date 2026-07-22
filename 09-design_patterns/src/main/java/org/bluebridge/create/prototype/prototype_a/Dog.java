package org.bluebridge.create.prototype.prototype_a;

import java.io.Serializable;
import java.util.Date;

/**
 * 序列化/反序列化实现深克隆
 *
 * @author lingwh
 * @date 2019/3/23 12:19
 */
public class Dog implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private Date birthday;

    /**
     * 创建一个新的实例 Dog.
     */
    public Dog() {
        super();
    }

    /**
     * 创建一个新的实例 Dog.
     *
     * @param name
     * @param birthday
     */
    public Dog(String name, Date birthday) {
        super();
        this.name = name;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Dog [name=" + name + ", birthday=" + birthday + "]";
    }
}
