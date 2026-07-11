package action.command.command_h;

/**
 * @author lingwh
 * @desc 空命令对象
 * @date 2019/9/4 15:25
 */
public class NoCommand implements Command {
    /**
     * 执行命令
     */
    @Override
    public void execute() {
        // 空实现,不做任何处理
    }

    /**
     * 撤销命令
     */
    @Override
    public void undo() {
        // 空实现,不做任何处理
    }
}
