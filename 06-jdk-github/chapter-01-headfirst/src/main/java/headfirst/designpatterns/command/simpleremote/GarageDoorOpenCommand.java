package headfirst.designpatterns.command.simpleremote;

/**
 * 车库门开启命令
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class GarageDoorOpenCommand implements Command {

    GarageDoor garageDoor;

    public GarageDoorOpenCommand(GarageDoor garageDoor) {
        this.garageDoor = garageDoor;
    }

    public void execute() {
        garageDoor.up();
    }
}
