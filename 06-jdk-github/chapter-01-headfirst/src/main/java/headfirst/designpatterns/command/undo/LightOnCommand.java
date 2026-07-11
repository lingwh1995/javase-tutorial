package headfirst.designpatterns.command.undo;

/**
 * @author lingwh
 * @desc 灯开启命令
 * @date 2026/7/9 00:00
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
