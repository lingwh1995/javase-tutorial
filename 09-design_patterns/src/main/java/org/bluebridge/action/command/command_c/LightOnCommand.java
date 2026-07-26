package org.bluebridge.action.command.command_c;

/**
 * 灯光打开命令
 *
 * @author lingwh
 * @date 2026/7/22 15:03
 */
public class LightOnCommand implements Command {

    private LightReceeiver lightReceeiver;

    public LightOnCommand(LightReceeiver lightReceeiver) {
        this.lightReceeiver = lightReceeiver;
    }

    @Override
    public void execute() {
        lightReceeiver.on();
    }

    @Override
    public void undo() {
        lightReceeiver.off();
    }
}
