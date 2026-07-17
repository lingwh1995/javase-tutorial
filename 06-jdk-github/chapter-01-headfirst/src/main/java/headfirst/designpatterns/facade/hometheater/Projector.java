package headfirst.designpatterns.facade.hometheater;

/**
 * 投影仪
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class Projector {

    String description;
    DvdPlayer dvdPlayer;

    public Projector(String description, DvdPlayer dvdPlayer) {
        this.description = description;
        this.dvdPlayer = dvdPlayer;
    }

    public void on() {
        System.out.println(description + " on");
    }

    public void off() {
        System.out.println(description + " off");
    }

    public void wideScreenMode() {
        System.out.println(description + " in widescreen mode (16x9 aspect ratio)");
    }

    public void tvMode() {
        System.out.println(description + " in tv mode (4x3 aspect ratio)");
    }

    public String toString() {
        return description;
    }
}
