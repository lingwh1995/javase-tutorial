package structure.proxy.dynamicproxy.dynamicproxy_c;

/**
 * @author lingwh
 * @desc TeacherDao
 * @date 2019/3/15 00:00
 */
public class TeacherDao implements ITeacher {
    @Override
    public void teach() {
        System.out.println("授课中......");
    }

    @Override
    public String sayello(String name) {
        System.out.println(name);
        return name;
    }
}
