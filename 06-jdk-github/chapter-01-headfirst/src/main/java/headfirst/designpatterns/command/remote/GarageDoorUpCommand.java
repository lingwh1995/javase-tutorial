package headfirst.designpatterns.command.remote;

/**
 * @author lingwh
 * @desc 车库门上升命令
 * @date 2026/7/9 00:00
 */
public class GarageDoorUpCommand implements Command {
    GarageDoor garageDoor;

    public GarageDoorUpCommand(GarageDoor garageDoor) {
        this.garageDoor = garageDoor;
    }

    public void execute() {
        garageDoor.up();
    }
}
