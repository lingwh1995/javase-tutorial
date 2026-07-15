package headfirst.designpatterns.command.undo;

/**
 * 灯开启命令
 *
 * @author lingwh
 * @date 2026/4/21 19:02
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
