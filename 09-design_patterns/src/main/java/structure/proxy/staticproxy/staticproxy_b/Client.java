package structure.proxy.staticproxy.staticproxy_b;

/**
 * @author lingwh
 * @desc 静态代理客户端
 * @date 2019/3/23 00:00
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
