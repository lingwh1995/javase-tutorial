package org.bluebridge.structure.proxy.staticproxy.staticproxy_b;

/**
 * 教师数据访问对象
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class TeacherDao implements ITeacher {

    @Override
    public void teach() {
        System.out.println("教书......");
    }
}
