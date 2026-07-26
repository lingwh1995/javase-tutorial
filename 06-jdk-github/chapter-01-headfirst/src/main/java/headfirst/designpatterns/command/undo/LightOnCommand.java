package headfirst.designpatterns.command.undo;

/**
 * 灯开启命令
 *
 * @author lingwh
 * @date 2023/12/7 11:28
 */
public class LightOnCommand implements Command {

    Light light;
    int level;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        level = light.getLevel();
        light.on();
    }

    public void undo() {
        light.dim(level);
    }
}
