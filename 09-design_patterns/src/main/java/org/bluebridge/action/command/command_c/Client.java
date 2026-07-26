package org.bluebridge.action.command.command_c;

/**
 * 客户端
 *
 * @author lingwh
 * @date 2026/7/22 15:03
 */
public class Client {

    public static void main(String[] args) {
        // 使用命令模式，完成使用遥控器操作电灯的操作
        LightReceeiver lightReceeiver = new LightReceeiver();
        // 创建电灯相关的开关命令
        LightOnCommand lightOnCommand = new LightOnCommand(lightReceeiver);
        LightOffCommand lightOffCommand = new LightOffCommand(lightReceeiver);

        // 创建遥控器
        RemoteContoller remoteContoller = new RemoteContoller();
        // no=0 是电灯的开和关的操作
        remoteContoller.setCommand(0, lightOnCommand, lightOffCommand);
        // 开灯
        remoteContoller.onButtonWasPush(0);
        // 关灯
        remoteContoller.offButtonWasPush(0);
        // 撤销关灯操作
        remoteContoller.undoButtonWasPush();

        System.out.println("-----------------------------------------------");
        TvReceiver tvReceiver = new TvReceiver();
        TvOnCommand tvOnCommand = new TvOnCommand(tvReceiver);
        TvOffCommand tvOffCommand = new TvOffCommand(tvReceiver);

        // no=1 是电视机的开和关的操作
        remoteContoller.setCommand(1, tvOnCommand, tvOffCommand);
        remoteContoller.onButtonWasPush(1);
        remoteContoller.offButtonWasPush(1);
        remoteContoller.undoButtonWasPush();
    }
}
