package headfirst.designpatterns.command.remote;

/**
 * @author lingwh
 * @desc 灯开启命令
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
}
