package headfirst.designpatterns.command.remote;

/**
 * @author lingwh
 * @desc 车库门下降命令
 * @date 2026/7/9 00:00
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
