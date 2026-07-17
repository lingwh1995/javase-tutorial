package headfirst.designpatterns.command.simpleremote;

/**
 * 简单遥控器(命令调用者)
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class SimpleRemoteControl {

    Command slot;

    public SimpleRemoteControl() {
    }

    public void setCommand(Command command) {
        slot = command;
    }

    public void buttonWasPressed() {
        slot.execute();
    }
}
