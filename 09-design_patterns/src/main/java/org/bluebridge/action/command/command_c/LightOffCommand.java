package org.bluebridge.action.command.command_c;

/**
 * 灯光关闭命令
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class LightOffCommand implements Command {

    private LightReceeiver lightReceeiver;

    public LightOffCommand(LightReceeiver lightReceeiver) {
        this.lightReceeiver = lightReceeiver;
    }

    @Override
    public void execute() {
        lightReceeiver.off();
    }

    @Override
    public void undo() {
        lightReceeiver.on();
    }
}
