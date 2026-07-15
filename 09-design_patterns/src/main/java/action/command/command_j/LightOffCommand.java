package action.command.command_j;

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

    /**
     * 执行关闭电灯命令
     */
    @Override
    public void execute() {
        light.off();
    }

    /**
     * 撤销关闭电灯命令
     */
    @Override
    public void undo() {
        light.on();
    }
}
