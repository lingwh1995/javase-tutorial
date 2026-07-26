package headfirst.designpatterns.command.party;

/**
 * 客厅灯关闭命令
 *
 * @author lingwh
 * @date 2023/12/7 08:52
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

    @Override
    public void undo() {
        light.on();
    }
}
