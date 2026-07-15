package headfirst.designpatterns.command.remote;

/**
 * 客厅灯开启命令
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
}
