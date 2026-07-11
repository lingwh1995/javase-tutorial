package action.command.command_j;

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

    /**
     * 执行打开电灯命令
     */
    @Override
    public void execute() {
        light.on();
    }

    /**
     * 撤销打开电灯命令
     */
    @Override
    public void undo() {
        light.off();
    }
}
