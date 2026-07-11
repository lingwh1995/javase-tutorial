package action.command.command_g;

/**
 * @author lingwh
 * @desc 遥控器对象
 * @date 2019/9/4 14:07
 */
public class SimpleRemoteControl {
    Command solt;

    public SimpleRemoteControl() {}

    public void setCommand(Command command) {
        solt = command;
    }

    public void buttonWasPressed() {
        solt.execute();
    }
}
