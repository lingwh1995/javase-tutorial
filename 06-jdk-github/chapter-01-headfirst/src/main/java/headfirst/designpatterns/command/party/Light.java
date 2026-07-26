package headfirst.designpatterns.command.party;

/**
 * 灯
 *
 * @author lingwh
 * @date 2023/12/7 11:25
 */
public class Light {

    String location;
    int level;

    public Light(String location) {
        this.location = location;
    }

    public void on() {
        level = 100;
        System.out.println("Light is on");
    }

    public void off() {
        level = 0;
        System.out.println("Light is off");
    }

    public void dim(int level) {
        this.level = level;
        if (level == 0) {
            off();
        } else {
            System.out.println("Light is dimmed to " + level + "%");
        }
    }

    public int getLevel() {
        return level;
    }
}
