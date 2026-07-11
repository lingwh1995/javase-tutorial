package structure.proxy.dynamicproxy.dynamicproxy_d;

/**
 * @author lingwh
 * @desc 教师Dao
 * @date 2026/7/9 00:00
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
