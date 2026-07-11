package headfirst.designpatterns.command.party;

/**
 * @author lingwh
 * @desc 灯打开命令
 * @date 2026/7/9 00:00
 */
public class LightOnCommand implements Command {
    Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        light.on();
    }

    public void undo() {
        light.off();
    }
}
