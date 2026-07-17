package headfirst.designpatterns.command.simpleremoteWL;

/**
 * 遥控器测试
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class RemoteControlTest {

    public static void main(String[] args) {
        SimpleRemoteControl remote = new SimpleRemoteControl();
        Light light = new Light();
        GarageDoor garageDoor = new GarageDoor();
        remote.setCommand(light::on);
        remote.buttonWasPressed();
        remote.setCommand(garageDoor::up);
        remote.buttonWasPressed();
        remote.setCommand(garageDoor::lightOn);
        remote.buttonWasPressed();
        remote.setCommand(garageDoor::lightOff);
        remote.buttonWasPressed();
    }
}
