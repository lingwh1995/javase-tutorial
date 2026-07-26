package headfirst.designpatterns.command.party;

/**
 * 灯关闭命令
 *
 * @author lingwh
 * @date 2023/12/7 10:48
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

    @Override
    public void undo() {
        light.on();
    }
}
