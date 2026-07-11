package action.command.command_c;

/**
 * @author lingwh
 * @desc 电视关闭命令
 * @date 2026/7/9 00:00
 */
public class TvOffCommand implements Command {

    private TvReceiver tvReceiver;

    public TvOffCommand(TvReceiver tvReceiver) {
        this.tvReceiver = tvReceiver;
    }

    @Override
    public void execute() {
        tvReceiver.off();
    }

    @Override
    public void undo() {
        tvReceiver.on();
    }
}
