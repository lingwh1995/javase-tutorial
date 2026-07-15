package headfirst.designpatterns.command.remote;

/**
 * 车库门下降命令
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class GarageDoorDownCommand implements Command {

    GarageDoor garageDoor;

    public GarageDoorDownCommand(GarageDoor garageDoor) {
        this.garageDoor = garageDoor;
    }

    public void execute() {
        garageDoor.up();
    }
}
