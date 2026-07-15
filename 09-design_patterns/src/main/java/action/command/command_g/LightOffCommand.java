package action.command.command_g;

/**
 * 电灯关闭命令
 *
 * @author lingwh
 * @date 2019/9/4 14:04
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
