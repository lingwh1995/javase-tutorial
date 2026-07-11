package action.command.command_c;

/**
 * @author lingwh
 * @desc 命令接口
 * @date 2026/7/9 00:00
 */
public interface Command {
    /**
     * 执行操作
     */
    void execute();

    /**
     * 撤销执行操作
     */
    void undo();
}
