package org.bluebridge.structure.proxy.staticproxy.staticproxy_b;

/**
 * 静态代理客户端
 *
 * @author lingwh
 * @date 2019/3/23 19:02
 */
public class Client {

    public static void main(String[] args) {
        // 创建被对象
        ITeacher teacherDao = new TeacherDao();
        // 创建代理对象
        ITeacher teacherDaoProxy = new TeacherDaoProxy(teacherDao);
        teacherDaoProxy.teach();
    }
}
