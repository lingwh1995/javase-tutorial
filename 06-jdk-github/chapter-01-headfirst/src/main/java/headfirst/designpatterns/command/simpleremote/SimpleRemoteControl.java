package headfirst.designpatterns.command.simpleremote;

/**
 * @author lingwh
 * @desc 简单遥控器(命令调用者)
 * @date 2026/7/9 00:00
 */
public class SimpleRemoteControl {
    Command slot;

    public SimpleRemoteControl() {}

    public void setCommand(Command command) {
        slot = command;
    }

    public void buttonWasPressed() {
        slot.execute();
    }
}
