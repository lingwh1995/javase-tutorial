package headfirst.designpatterns.command.undo;

/**
 * 调光灯关闭命令
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class DimmerLightOffCommand implements Command {

    Light light;
    int prevLevel;

    public DimmerLightOffCommand(Light light) {
        this.light = light;
        prevLevel = 100;
    }

    public void execute() {
        prevLevel = light.getLevel();
        light.off();
    }

    public void undo() {
        light.dim(prevLevel);
    }
}
