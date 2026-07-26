package org.bluebridge.action.command.command_k;

import java.util.ArrayList;
import java.util.List;

/**
 * 计算器类，计算器上有加法按钮、减法按钮
 *
 * @author lingwh
 * @date 2019/9/10 16:23
 */
public class Calculator {

    /**
     * 加法命令对象
     */
    private Command addCommand;

    /**
     * 减法命令对象
     */
    private Command substractCommand;

    /**
     * 记录撤销命令
     */
    private List<Command> undoCommands = new ArrayList<Command>();

    public void setAddCommand(Command addCommand) {
        this.addCommand = addCommand;
    }

    public void setSubstractCommand(Command substractCommand) {
        this.substractCommand = substractCommand;
    }

    /**
     * 当加按钮被按下
     */
    public void addButtonWasPressed() {
        addCommand.execute();
        undoCommands.add(addCommand);
    }

    /**
     * 当减按钮被按下
     */
    public void subButtonWasPressed() {
        substractCommand.execute();
        undoCommands.add(substractCommand);
    }

    /**
     * 撤销按钮被按下 在命令历史中取出最后一个命令，撤销该命令，并从历史命令中删除该命令，相当于没有执行过该命令
     */
    public void undoButtonWasPressed() {
        if (this.undoCommands.size() > 0) {
            // 取出最后一个命令来撤销
            Command cmd = this.undoCommands.get(this.undoCommands.size() - 1);
            cmd.undo();
            // 然后把最后一个命令删除掉，
            this.undoCommands.remove(cmd);
        } else {
            System.out.println("很抱歉，没有可撤销的命令");
        }
    }
}
