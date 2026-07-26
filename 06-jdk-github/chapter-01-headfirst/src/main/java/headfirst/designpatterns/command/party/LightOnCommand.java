package headfirst.designpatterns.command.party;

/**
 * 灯打开命令
 *
 * @author lingwh
 * @date 2023/12/7 09:36
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

    @Override
    public void undo() {
        light.off();
    }
}
