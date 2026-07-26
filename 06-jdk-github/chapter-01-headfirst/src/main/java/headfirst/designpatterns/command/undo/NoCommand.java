package headfirst.designpatterns.command.undo;

/**
 * 空命令
 *
 * @author lingwh
 * @date 2023/12/7 10:54
 */
public class NoCommand implements Command {

    @Override
    public void execute() {
    }

    @Override
    public void undo() {
    }
}
