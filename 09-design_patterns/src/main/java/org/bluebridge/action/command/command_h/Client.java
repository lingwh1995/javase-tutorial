package org.bluebridge.action.command.command_h;

/**
 * 撤销命令模式客户端
 *
 * @author lingwh
 * @date 2019/9/4 15:21
 */
public class Client {

    public static void main(String[] args) {
        // 遥控器对象
        RemoteControl remoteControl = new RemoteControl();
        Light light = new Light();

        // 电灯打开命令对象
        LightOnCommand lightOnCommand = new LightOnCommand(light);
        // 电灯关闭对象
        LightOffCommand lightOffCommand = new LightOffCommand(light);
        remoteControl.setCommand(0, lightOnCommand, lightOffCommand);
        // 执行打开电灯命令
        remoteControl.onButtonWasPressed(0);
        // 撤销打开电灯命令
        remoteControl.undoButtonWasPressed();
        // 执行关闭电灯命令
        remoteControl.offButtonWasPressed(0);
        // 撤销关闭电灯命令
        remoteControl.undoButtonWasPressed();

        // 音响设备
        Stereo stereo = new Stereo();
        // 打开音响设备命令对象
        StereoOnWithCDCommand stereoOnWithCDCommand = new StereoOnWithCDCommand(stereo);
        // 关闭音响设备命令对象
        StereoOffWithCDCommand stereoOffWithCDCommand = new StereoOffWithCDCommand(stereo);
        remoteControl.setCommand(1, stereoOnWithCDCommand, stereoOffWithCDCommand);
        // 执行打开音响设备命令
        remoteControl.onButtonWasPressed(1);
        // 撤销打开音响设备的命令
        remoteControl.undoButtonWasPressed();
        // 执行关闭音响设备命令
        remoteControl.offButtonWasPressed(1);
        // 撤销关闭音响设备的命令
        remoteControl.undoButtonWasPressed();
        System.out.println(remoteControl);
    }
}
