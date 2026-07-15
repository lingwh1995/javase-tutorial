package headfirst.designpatterns.command.simpleremote;

/**
 * 灯开启命令
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
}
