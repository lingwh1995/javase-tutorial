package headfirst.designpatterns.command.remote;

/**
 * @author lingwh
 * @desc 客厅灯开启命令
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
}
