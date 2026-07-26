package org.bluebridge.action.command.command_c;

/**
 * 电视打开命令
 *
 * @author lingwh
 * @date 2026/7/22 15:03
 */
public class TvOnCommand implements Command {

    private TvReceiver tvReceiver;

    public TvOnCommand(TvReceiver tvReceiver) {
        this.tvReceiver = tvReceiver;
    }

    @Override
    public void execute() {
        tvReceiver.on();
    }

    @Override
    public void undo() {
        tvReceiver.off();
    }
}
