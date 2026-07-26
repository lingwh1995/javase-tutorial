package headfirst.designpatterns.command.party;

/**
 * 命令接口
 *
 * @author lingwh
 * @date 2023/12/7 15:24
 */
public interface Command {

    public void execute();

    public void undo();
}
