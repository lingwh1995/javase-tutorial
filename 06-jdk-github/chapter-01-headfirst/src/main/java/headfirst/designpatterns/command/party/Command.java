package headfirst.designpatterns.command.party;

/**
 * @author lingwh
 * @desc 命令接口
 * @date 2026/7/9 00:00
 */
public interface Command {
    public void execute();

    public void undo();
}
