package headfirst.designpatterns.command.remote;

/**
 * 客厅灯关闭命令
 *
 * @author lingwh
 * @date 2026/4/21 19:02
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
