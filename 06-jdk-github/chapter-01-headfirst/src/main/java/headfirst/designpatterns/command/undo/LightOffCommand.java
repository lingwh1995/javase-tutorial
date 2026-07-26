package headfirst.designpatterns.command.undo;

/**
 * 灯关闭命令
 *
 * @author lingwh
 * @date 2023/12/7 12:46
 */
public class LightOffCommand implements Command {

    Light light;
    int level;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        level = light.getLevel();
        light.off();
    }

    @Override
    public void undo() {
        light.dim(level);
    }
}
