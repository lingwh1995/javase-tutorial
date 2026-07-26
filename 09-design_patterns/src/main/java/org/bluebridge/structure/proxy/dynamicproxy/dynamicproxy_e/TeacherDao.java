package org.bluebridge.structure.proxy.dynamicproxy.dynamicproxy_e;

/**
 * 教师数据访问对象
 *
 * @author lingwh
 * @date 2026/7/22 10:15
 */
public class TeacherDao {

    public void teach() {
        System.out.println("授课......");
    }

    public String sayello(String name) {
        System.out.println(name);
        return name;
    }
}
