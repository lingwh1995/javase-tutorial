package org.bluebridge.action.command.command_c;

/**
 * 空命令
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class NoCommand implements Command {

    @Override
    public void execute() {}

    @Override
    public void undo() {}
}
