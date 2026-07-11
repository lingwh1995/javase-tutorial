package action.command.command_c;

/**
 * @author lingwh
 * @desc 空命令
 * @date 2026/7/9 00:00
 */
public class NoCommand implements Command {
    @Override
    public void execute() {}

    @Override
    public void undo() {}
}
