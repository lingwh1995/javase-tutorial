package headfirst.designpatterns.command.simpleremoteWL;

/**
 * 命令接口
 *
 * @author lingwh
 * @date 2023/12/7 12:08
 */
@FunctionalInterface
public interface Command {

    public void execute();
}
