package headfirst.designpatterns.command.simpleremote;

/**
 * 简单遥控器(命令调用者)
 *
 * @author lingwh
 * @date 2023/12/7 13:24
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
