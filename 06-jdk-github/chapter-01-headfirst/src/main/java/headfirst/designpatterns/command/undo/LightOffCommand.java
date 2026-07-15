package headfirst.designpatterns.command.undo;

/**
 * 灯关闭命令
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class LightOffCommand implements Command {

    Light light;
    int level;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        level = light.getLevel();
        light.off();
    }

    public void undo() {
        light.dim(level);
    }
}
