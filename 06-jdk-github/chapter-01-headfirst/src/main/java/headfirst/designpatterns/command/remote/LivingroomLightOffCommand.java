package headfirst.designpatterns.command.remote;

/**
 * 客厅灯关闭命令
 *
 * @author lingwh
 * @date 2023/12/7 10:58
 */
public class LivingroomLightOffCommand implements Command {

    Light light;

    public LivingroomLightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }
}
