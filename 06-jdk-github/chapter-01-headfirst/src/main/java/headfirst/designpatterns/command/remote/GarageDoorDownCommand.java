package headfirst.designpatterns.command.remote;

/**
 * 车库门下降命令
 *
 * @author lingwh
 * @date 2023/12/7 18:55
 */
public class GarageDoorDownCommand implements Command {

    GarageDoor garageDoor;

    public GarageDoorDownCommand(GarageDoor garageDoor) {
        this.garageDoor = garageDoor;
    }

    @Override
    public void execute() {
        garageDoor.up();
    }
}
