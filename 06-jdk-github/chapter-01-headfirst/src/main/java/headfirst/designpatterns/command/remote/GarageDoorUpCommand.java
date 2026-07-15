package headfirst.designpatterns.command.remote;

/**
 * 车库门上升命令
 *
 * @author lingwh
 * @date 2026/4/21 19:02
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
