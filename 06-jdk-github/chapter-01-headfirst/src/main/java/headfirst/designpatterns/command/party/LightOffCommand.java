package headfirst.designpatterns.command.party;

/**
 * @author lingwh
 * @desc 灯关闭命令
 * @date 2026/7/9 00:00
 */
public class LightOffCommand implements Command {
    Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        light.off();
    }

    public void undo() {
        light.on();
    }
}
