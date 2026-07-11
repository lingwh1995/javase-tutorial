package action.command.command_g;

/**
 * @author lingwh
 * @desc 遥控器命令模式客户端
 * @date 2019/9/4 14:10
 */
public class Client {
    public static void main(String[] args) {
        // 遥控器对象
        SimpleRemoteControl remote = new SimpleRemoteControl();
        // 电灯对象
        Light light = new Light();
        // 打开电灯命令对象
        LightOnCommand lightOnCommand = new LightOnCommand(light);
        remote.setCommand(lightOnCommand);
        remote.buttonWasPressed();
        // 关闭电灯命令对象
        LightOffCommand lightOffCommand = new LightOffCommand(light);
        remote.setCommand(lightOffCommand);
        remote.buttonWasPressed();

        // 汽车库门对象
        GrageDoor grageDoor = new GrageDoor();
        // 打开汽车库门命令对象
        GrageDoorOpenCommand grageDoorOpenCommand = new GrageDoorOpenCommand(grageDoor);
        remote.setCommand(grageDoorOpenCommand);
        remote.buttonWasPressed();
        // 关闭汽车库门命令对象
        GrageDoorCloseCommand grageDoorCloseCommand = new GrageDoorCloseCommand(grageDoor);
        remote.setCommand(grageDoorCloseCommand);
        remote.buttonWasPressed();
    }
}
