package headfirst.designpatterns.command.undo;

/**
 * @author lingwh
 * @desc 空命令
 * @date 2026/7/9 00:00
 */
public class NoCommand implements Command {
    public void execute() {}

    public void undo() {}
}
