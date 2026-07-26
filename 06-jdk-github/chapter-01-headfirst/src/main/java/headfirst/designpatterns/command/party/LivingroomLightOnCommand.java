package headfirst.designpatterns.command.party;

/**
 * 客厅灯打开命令
 *
 * @author lingwh
 * @date 2023/12/7 21:27
 */
public class LivingroomLightOnCommand implements Command {

    Light light;

    public LivingroomLightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }

    @Override
    public void undo() {
        light.off();
    }
}
