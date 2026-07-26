package org.bluebridge.action.command.command_c;

/**
 * 灯光关闭命令
 *
 * @author lingwh
 * @date 2026/7/22 15:03
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
