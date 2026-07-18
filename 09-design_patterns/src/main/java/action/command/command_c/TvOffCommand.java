package action.command.command_c;

/**
 * 电视关闭命令
 *
 * @author lingwh
 * @date 2026/7/13 19:02
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
