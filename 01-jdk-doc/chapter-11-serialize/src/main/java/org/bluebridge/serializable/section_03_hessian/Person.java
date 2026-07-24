package org.bluebridge.serializable.section_03_hessian;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 人员类
 *
 * @author lingwh
 * @date 2019/7/9 10:30
 */
public class Person implements Externalizable {

    private static final long serialVersionUID = 1L;
    String userName;
    String password;
    String age;

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        // 增加一个新的对象
        Date date = new Date();
        out.writeObject(password);
        out.writeObject(userName);
        // 不序列化age对象
        out.writeObject(date);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        // 注意这里的接受顺序是有限制的哦，否则的话会出错的
        // 例如上面先write的是A对象的话，那么下面先接受的也一定是A对象...
        userName = (String) in.readObject();
        password = (String) in.readObject();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = (Date) in.readObject();
        System.out.println("反序列化后的日期为:" + sdf.format(date));
    }

    public Person(String userName, String password, String age) {
        this.userName = userName;
        this.password = password;
        this.age = age;
    }

    public Person() {
        System.out.println("调用了构造方法......");
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
