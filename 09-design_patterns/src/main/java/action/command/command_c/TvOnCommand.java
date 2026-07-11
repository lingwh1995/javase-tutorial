package action.command.command_c;

/**
 * @author lingwh
 * @desc 电视打开命令
 * @date 2026/7/9 00:00
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
