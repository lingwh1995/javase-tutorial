package create.prototype.prototype_a;

import java.util.Date;

/**
 * 深克隆/深复制实体
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class Person implements Cloneable {

    private String name;
    private Date birthday;

    /**
     * 创建一个新的实例 Person.
     */
    public Person() {
        super();
    }

    /**
     * 创建一个新的实例 Person.
     *
     * @param name
     * @param birthday
     */
    public Person(String name, Date birthday) {
        super();
        this.name = name;
        this.birthday = birthday;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Person personClone = (Person) super.clone();
        // 克隆属性
        Date birthClone = (Date) this.birthday.clone();
        personClone.setBirthday(birthClone);
        return personClone;
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
        return "Person [name=" + name + ", birthday=" + birthday + "]";
    }
}
