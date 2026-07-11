package action.command.command_k;

/**
 * @author lingwh
 * @desc 命令接口
 * @date 2019/9/10 16:14
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
