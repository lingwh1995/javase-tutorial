package headfirst.designpatterns.command.remoteWL;

/**
 * 命令接口
 *
 * @author lingwh
 * @date 2023/12/7 14:41
 */
@FunctionalInterface
public interface Command {

    public void execute();
}
