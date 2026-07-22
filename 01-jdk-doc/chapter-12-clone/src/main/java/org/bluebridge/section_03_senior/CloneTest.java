package org.bluebridge.section_03_senior;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 浅克隆修改副本数据和对原型数据的影响
 *
 * 1. 修改基本类型数据: 浅克隆时修改克隆出来的副本不影响原型中数据
 * 2. 修改String类型数据: 浅克隆时修改克隆出来的副本不影响原型中数据
 * 3. 修改包装类型数据: 浅克隆时修改克隆出来的副本不影响原型中数据
 * 4. 修改数组类型数据: 浅克隆时修改克隆出来的副本会影响原型中数据
 * 5. 修改List类型数据: 浅克隆时修改克隆出来的副本会影响原型中数据
 * 6. 修改引用类型的数据: 浅克隆时修改克隆出来的副本会影响原型中数据
 *
 * 防止修改副本时影响原型
 * 1. 引用类型的数据: 如User中有一个数据类型为Mark类型的引用，要想实现深克隆，则这个类需要实现序列化接口，重写Object中clone()方法
 * 2. 集合类型/数组类型数据: 克隆出副本后返回前，先拿到类型的数据，然后创建一个新的该类型的对象
 *
 * 深克隆方式用法
 * 1. 对实体中的某些字段进行特殊处理，如:复制数组、复制集合
 * 2. 或者使该实体中的引用也实现Cloneable接口，并重写Objet中clone()方法
 *
 * @author lingwh
 * @date 2019/7/11 11:13
 */
public class CloneTest {

    /**
     * 测试浅克隆和深克隆
     */
    @Test
    public void testShllowCloneAndDeepCloning() throws CloneNotSupportedException {
        /**
         * 匿名内部类方式初始化ArrayList
         */
        ArrayList<String> friends = new ArrayList<String>() {
            {
                add("小红");
                add("小明");
            }
        };
        System.out.println(friends);
        User user = new User(1, "zhagnsan", 18, new String[] { "羽毛球" }, friends, new Mark(86.9, 83.3));
        System.out.println("原型对象:" + user);
        User userClone = user.clone();
        System.out.println("克隆对象:" + userClone);
        System.out.println("---------------------------------------");
        // 修改基本类型数据:浅克隆时修改克隆出来的副本不影响原型中数据
        userClone.setId(11);
        // 修改String类型数据:浅克隆时修改克隆出来的副本不影响原型中数据
        userClone.setName("zhangsan1");
        // 修改包装类型数据:浅克隆时修改克隆出来的副本不影响原型中数据
        userClone.setAge(181);
        // 修改数组类型数据:浅克隆时修改克隆出来的副本会影响原型中数据
        String[] hobby = userClone.getHobby();
        hobby[0] = "羽毛球1";
        // 修改List类型数据:浅克隆时修改克隆出来的副本会影响原型中数据
        List<String> friends1 = userClone.getFriends();
        friends1.set(0, "嘻嘻");
        friends1.set(1, "哈哈");
        userClone.setFriends(friends1);
        // 修改引用类型的数据:浅克隆时修改克隆出来的副本会影响原型中数据
        userClone.setMark(new Mark(1.0, 2.0));
        System.out.println("原型对象:" + user);
        System.out.println("修改后的克隆对象:" + userClone);
    }
}
