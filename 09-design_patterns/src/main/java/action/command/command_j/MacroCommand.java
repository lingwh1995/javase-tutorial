package action.command.command_j;

/**
 * 宏命令
 *
 * @author lingwh
 * @date 2019/9/5 8:47
 */
public class MacroCommand implements Command {

    /**
     * 在宏命令模式中,使用命令数组存储一大推命令
     */
    Command[] commands;

    public MacroCommand(Command[] commands) {
        this.commands = commands;
    }

    @Override
    public void execute() {
        for (int i = 0; i < commands.length; i++) {
            commands[i].execute();
        }
    }

    /**
     * 撤销命令
     */
    @Override
    public void undo() {
        for (int i = 0; i < commands.length; i++) {
            commands[i].undo();
        }
    }
}
