package headfirst.designpatterns.command.remote;

/**
 * 灯关闭命令
 *
 * @author lingwh
 * @date 2023/12/7 12:47
 */
public class LightOffCommand implements Command {

    Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }
}
