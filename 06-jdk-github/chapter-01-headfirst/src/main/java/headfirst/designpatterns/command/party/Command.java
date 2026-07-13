package headfirst.designpatterns.command.party;

/**
 * 命令接口
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public interface Command {

    public void execute();

    public void undo();
}
