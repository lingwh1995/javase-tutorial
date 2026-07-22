package org.bluebridge.action.command.command_c;

/**
 * 命令接口
 *
 * @author lingwh
 * @date 2026/4/21 19:02
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
