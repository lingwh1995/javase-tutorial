package headfirst.designpatterns.command.undo;

/**
 * @author lingwh
 * @desc 调光灯关闭命令
 * @date 2026/7/9 00:00
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
