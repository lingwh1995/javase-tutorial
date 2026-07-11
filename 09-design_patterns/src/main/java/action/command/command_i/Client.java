package action.command.command_i;

/**
 * @author lingwh
 * @desc 吊扇命令模式客户端
 * @date 2019/9/4 18:19
 */
public class Client {
    public static void main(String[] args) {
        // 遥控器对象
        RemoteControl remoteControl = new RemoteControl();

        // 测试吊扇
        // 创建吊扇对象
        CeilingFan ceilingFan = new CeilingFan("living room");
        // 创建高档位风速对象
        CeilingFanHighCommand ceilingFanHighCommand = new CeilingFanHighCommand(ceilingFan);
        // 创建关闭档位风速对象
        CeilingFanOffCommand ceilingFanOffCommand = new CeilingFanOffCommand(ceilingFan);
        remoteControl.setCommand(0, ceilingFanHighCommand, ceilingFanOffCommand);
        // 执行换到高档位操作
        remoteControl.onButtonWasPressed(0);
        // 撤销换到高档位操作
        remoteControl.undoButtonWasPressed();

        // 创建中档位风速对象
        CeilingFanMediumCommand ceilingFanMediumCommand = new CeilingFanMediumCommand(ceilingFan);
        remoteControl.setCommand(1, ceilingFanMediumCommand, ceilingFanOffCommand);
        // 执行换到中档位操作
        remoteControl.onButtonWasPressed(1);
        // 撤销换到中档位操作
        remoteControl.undoButtonWasPressed();

        // 创建低档位风速对象
        CeilingFanLowCommand ceilingFanLowCommand = new CeilingFanLowCommand(ceilingFan);
        remoteControl.setCommand(2, ceilingFanLowCommand, ceilingFanOffCommand);
        // 执行换到低档位操作
        remoteControl.onButtonWasPressed(2);
        // 撤销换到低档位操作
        remoteControl.undoButtonWasPressed();
    }
}
