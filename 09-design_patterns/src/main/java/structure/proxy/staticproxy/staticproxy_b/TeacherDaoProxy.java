package structure.proxy.staticproxy.staticproxy_b;

/**
 * 教师数据访问对象代理
 *
 * @author lingwh
 * @date 2026/7/13 19:02
 */
public class TeacherDaoProxy implements ITeacher {

    private ITeacher teacherDao;

    public TeacherDaoProxy(ITeacher teacherDao) {
        this.teacherDao = teacherDao;
    }

    @Override
    public void teach() {
        System.out.println("代理开始......");
        teacherDao.teach();
        System.out.println("代理结束......");
    }
}
