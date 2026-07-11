package action.command.command_c;

/**
 * @author lingwh
 * @desc 灯光关闭命令
 * @date 2026/7/9 00:00
 */
public class LightOffCommand implements Command {

    private LightReceeiver lightReceeiver;

    public LightOffCommand(LightReceeiver lightReceeiver) {
        this.lightReceeiver = lightReceeiver;
    }

    @Override
    public void execute() {
        lightReceeiver.off();
    }

    @Override
    public void undo() {
        lightReceeiver.on();
    }
}
