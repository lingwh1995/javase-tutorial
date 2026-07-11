package headfirst.designpatterns.command.remote;

/**
 * @author lingwh
 * @desc 客厅灯关闭命令
 * @date 2026/7/9 00:00
 */
public class LivingroomLightOffCommand implements Command {
    Light light;

    public LivingroomLightOffCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        light.off();
    }
}
