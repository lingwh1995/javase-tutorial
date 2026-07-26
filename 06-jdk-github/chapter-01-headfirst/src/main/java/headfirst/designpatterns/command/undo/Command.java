package headfirst.designpatterns.command.undo;

/**
 * 命令接口
 *
 * @author lingwh
 * @date 2023/12/7 16:27
 */
public interface Command {

    public void execute();

    public void undo();
}
