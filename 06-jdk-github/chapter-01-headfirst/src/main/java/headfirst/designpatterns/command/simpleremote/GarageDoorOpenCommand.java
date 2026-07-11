package headfirst.designpatterns.command.simpleremote;

/**
 * @author lingwh
 * @desc 车库门开启命令
 * @date 2026/7/9 00:00
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
