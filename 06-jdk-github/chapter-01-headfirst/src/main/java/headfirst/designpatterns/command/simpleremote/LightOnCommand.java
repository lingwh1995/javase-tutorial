package headfirst.designpatterns.command.simpleremote;

/**
 * 灯开启命令
 *
 * @author lingwh
 * @date 2023/12/7 15:16
 */
public class LightOnCommand implements Command {

    Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }
}
