package action.command.command_j;

/**
 * @author lingwh
 * @desc 遥控器
 * @date 2019/9/4 15:05
 */
public class RemoteControl {
    private Command[] onCommands;
    private Command[] offCommands;

    /**
     * 用来记录前一个命令
     */
    private Command undoCommand;

    public RemoteControl() {
        onCommands = new Command[7];
        offCommands = new Command[7];
        NoCommand noCommand = new NoCommand();
        for (int i = 0; i < 7; i++) {
            onCommands[i] = noCommand;
            offCommands[i] = noCommand;
        }
        // 一开始,并没有所谓的前一个命令对象，将其设置为noCommand对象
        undoCommand = noCommand;
    }

    public void setCommand(int slot, Command onCommand, Command offCommand) {
        onCommands[slot] = onCommand;
        offCommands[slot] = offCommand;
    }

    /**
     * 打开按钮被按下
     *
     * @param solt
     */
    public void onButtonWasPressed(int solt) {
        onCommands[solt].execute();
        // 不管是打开还是关闭命令,处理方式都是一样的
        undoCommand = onCommands[solt];
    }

    /**
     * 关闭按钮被按下
     *
     * @param solt
     */
    public void offButtonWasPressed(int solt) {
        offCommands[solt].execute();
        // 不管是打开还是关闭命令,处理方式都是一样的
        undoCommand = offCommands[solt];
    }

    /**
     * 撤销按钮被按下,执行撤销操作 注意:undoCommand永远保存的都是前一个被执行的命令,执行撤销命令则会倒转前一个命令
     */
    public void undoButtonWasPressed() {
        System.out.println("............");
        undoCommand.undo();
        System.out.println("............");
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\n---------------------Remote Control---------------------\n");
        for (int i = 0; i < onCommands.length; i++) {
            stringBuffer.append(
                    "[solt"
                            + i
                            + "]"
                            + onCommands[i].getClass().getName()
                            + "  "
                            + offCommands[i].getClass().getName()
                            + "\n");
        }
        return stringBuffer.toString();
    }
}
