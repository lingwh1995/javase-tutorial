package structure.flyweight.flyweight_a;

/**
 * 享元模式的外部状态
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class User {

    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
