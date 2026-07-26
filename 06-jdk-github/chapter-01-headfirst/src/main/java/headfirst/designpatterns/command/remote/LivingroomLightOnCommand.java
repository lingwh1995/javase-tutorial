package headfirst.designpatterns.command.remote;

/**
 * 客厅灯开启命令
 *
 * @author lingwh
 * @date 2023/12/7 09:32
 */
public class LivingroomLightOnCommand implements Command {

    Light light;

    public LivingroomLightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }
}
