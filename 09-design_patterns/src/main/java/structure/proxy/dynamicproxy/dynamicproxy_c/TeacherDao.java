package structure.proxy.dynamicproxy.dynamicproxy_c;

/**
 * TeacherDao
 *
 * @author lingwh
 * @date 2019/3/15 19:02
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
