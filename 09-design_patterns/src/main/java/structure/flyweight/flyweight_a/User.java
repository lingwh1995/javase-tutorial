package structure.flyweight.flyweight_a;

/**
 * @author lingwh
 * @desc 享元模式的外部状态
 * @date 2026/7/9 00:00
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
