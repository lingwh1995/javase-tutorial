package headfirst.designpatterns.command.simpleremote;

/**
 * 车库门开启命令
 *
 * @author lingwh
 * @date 2023/12/7 18:27
 */
public class GarageDoorOpenCommand implements Command {

    GarageDoor garageDoor;

    public GarageDoorOpenCommand(GarageDoor garageDoor) {
        this.garageDoor = garageDoor;
    }

    @Override
    public void execute() {
        garageDoor.up();
    }
}
