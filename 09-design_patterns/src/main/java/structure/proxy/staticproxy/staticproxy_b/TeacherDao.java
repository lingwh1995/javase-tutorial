package structure.proxy.staticproxy.staticproxy_b;

/**
 * @author lingwh
 * @desc 教师数据访问对象
 * @date 2026/7/9 00:00
 */
public class TeacherDao implements ITeacher {
    @Override
    public void teach() {
        System.out.println("教书......");
    }
}
