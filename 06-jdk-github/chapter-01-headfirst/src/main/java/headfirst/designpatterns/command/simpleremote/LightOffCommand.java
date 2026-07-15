package headfirst.designpatterns.command.simpleremote;

/**
 * 灯关闭命令
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class LightOffCommand implements Command {

    Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        light.off();
    }
}
