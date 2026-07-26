package org.bluebridge.structure.proxy.dynamicproxy.dynamicproxy_d;

/**
 * 教师 Dao
 *
 * @author lingwh
 * @date 2026/7/22 15:20
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
