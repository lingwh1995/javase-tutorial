package action.command.command_g;

/**
 * @author lingwh
 * @desc 电灯打开命令
 * @date 2019/9/4 14:04
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
