package headfirst.designpatterns.facade.hometheater;

/**
 * @author lingwh
 * @desc 爆米花机
 * @date 2026/7/9 00:00
 */
public class PopcornPopper {
    String description;

    public PopcornPopper(String description) {
        this.description = description;
    }

    public void on() {
        System.out.println(description + " on");
    }

    public void off() {
        System.out.println(description + " off");
    }

    public void pop() {
        System.out.println(description + " popping popcorn!");
    }

    public String toString() {
        return description;
    }
}
