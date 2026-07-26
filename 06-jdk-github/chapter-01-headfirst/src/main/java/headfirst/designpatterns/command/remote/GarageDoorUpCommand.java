package headfirst.designpatterns.command.remote;

/**
 * 车库门上升命令
 *
 * @author lingwh
 * @date 2023/12/7 17:28
 */
public class GarageDoorUpCommand implements Command {

    GarageDoor garageDoor;

    public GarageDoorUpCommand(GarageDoor garageDoor) {
        this.garageDoor = garageDoor;
    }

    @Override
    public void execute() {
        garageDoor.up();
    }
}
