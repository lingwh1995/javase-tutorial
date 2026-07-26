package headfirst.designpatterns.command.remote;

/**
 * 灯开启命令
 *
 * @author lingwh
 * @date 2023/12/7 11:21
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
