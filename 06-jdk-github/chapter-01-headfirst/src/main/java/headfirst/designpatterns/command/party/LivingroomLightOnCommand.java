package headfirst.designpatterns.command.party;

/**
 * 客厅灯打开命令
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class LivingroomLightOnCommand implements Command {

    Light light;

    public LivingroomLightOnCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        light.on();
    }

    public void undo() {
        light.off();
    }
}
