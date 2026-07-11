package headfirst.designpatterns.command.undo;

/**
 * @author lingwh
 * @desc 调光灯开启命令
 * @date 2026/7/9 00:00
 */
public class DimmerLightOnCommand implements Command {
    Light light;
    int prevLevel;

    public DimmerLightOnCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        prevLevel = light.getLevel();
        light.dim(75);
    }

    public void undo() {
        light.dim(prevLevel);
    }
}
