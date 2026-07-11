package headfirst.designpatterns.command.party;

/**
 * @author lingwh
 * @desc 客厅灯打开命令
 * @date 2026/7/9 00:00
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
