package headfirst.designpatterns.command.undo;

/**
 * 调光灯开启命令
 *
 * @author lingwh
 * @date 2026/4/21 19:02
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
