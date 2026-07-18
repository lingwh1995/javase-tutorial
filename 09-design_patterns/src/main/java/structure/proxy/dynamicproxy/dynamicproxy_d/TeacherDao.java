package structure.proxy.dynamicproxy.dynamicproxy_d;

/**
 * 教师Dao
 *
 * @author lingwh
 * @date 2026/4/21 19:02
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
