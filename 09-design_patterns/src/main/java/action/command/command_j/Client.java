package action.command.command_j;

/**
 * 宏命令模式的遥控器(总共就两个按钮)
 *
 * 1. 一个按钮实现打开电灯、打开音响设备
 * 2. 一个按钮实现关闭电灯、关闭音响设备
 *
 * @author lingwh
 * @desc 宏命令模式客户端
 * @date 2019/9/4 15:21
 */
public class Client {
    public static void main(String[] args) {
        RemoteControl remoteControl = new RemoteControl();
        // 创建电灯对象
        Light light = new Light();
        // 创建音响设备
        Stereo stereo = new Stereo();

        // 创建打开电灯的对象
        LightOnCommand lightOnCommand = new LightOnCommand(light);
        // 创建关闭电灯的对象
        LightOffCommand lightOffCommand = new LightOffCommand(light);

        // 创建打开音响设备的对象
        StereoOnWithCDCommand stereoOnWithCDCommand = new StereoOnWithCDCommand(stereo);
        // 创建关闭音响设备的对象
        StereoOffWithCDCommand stereoOffWithCDCommand = new StereoOffWithCDCommand(stereo);

        // 创建打开设备的宏命令
        Command[] onCommands = {lightOnCommand, stereoOnWithCDCommand};
        MacroCommand macroOnCommand = new MacroCommand(onCommands);
        // 创建关闭设备的宏命令
        Command[] offCommands = {lightOffCommand, stereoOffWithCDCommand};
        MacroCommand macroOffCommand = new MacroCommand(offCommands);

        // 直接执行/撤销宏命令 ,不经过遥控板
        // macroOnCommand.execute();
        // macroOnCommand.undo();

        // 通过遥控板执行宏命令
        remoteControl.setCommand(0, macroOnCommand, macroOffCommand);
        remoteControl.onButtonWasPressed(0);
        // 通过遥控板撤销宏命令
        remoteControl.undoButtonWasPressed();
    }
}
