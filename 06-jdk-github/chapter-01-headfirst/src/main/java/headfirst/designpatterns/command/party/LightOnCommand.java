package headfirst.designpatterns.command.party;

/**
 * 灯打开命令
 *
 * @author lingwh
 * @date 2026/4/21 19:02
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
