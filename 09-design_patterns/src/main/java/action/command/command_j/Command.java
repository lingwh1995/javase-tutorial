package action.command.command_j;

/**
 * @author lingwh
 * @desc 命令接口
 * @date 2019/9/4 14:03
 */
public interface Command {

    /**
     * 执行命令
     */
    public void execute();

    /**
     * 撤销命令
     */
    public void undo();
}
