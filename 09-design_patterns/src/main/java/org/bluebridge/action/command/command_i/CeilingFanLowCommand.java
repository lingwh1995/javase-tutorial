package org.bluebridge.action.command.command_i;

/**
 * 吊扇低档命令
 *
 * @author lingwh
 * @date 2019/9/4 16:59
 */
public class CeilingFanLowCommand implements Command {

    /**
     * 持有吊扇的引用
     */
    CeilingFan ceilingFan;

    /**
     * 吊扇之前的速度
     */
    int preSpeed;

    public CeilingFanLowCommand(CeilingFan ceilingFan) {
        this.ceilingFan = ceilingFan;
    }

    /**
     * 执行命令
     */
    @Override
    public void execute() {
        preSpeed = ceilingFan.getSpeed();
        System.out.println("上一次风扇档位:" + preSpeed);
        ceilingFan.low();
        System.out.println("当前风扇档位:" + ceilingFan.getSpeed());
    }

    /**
     * 撤销命令：将吊扇的速度设置会之前的值，达到撤销的目的
     */
    @Override
    public void undo() {
        System.out.println("上一次风扇档位:" + ceilingFan.getSpeed());
        if (preSpeed == CeilingFan.HIGH) {
            ceilingFan.high();
        } else if (preSpeed == CeilingFan.MEDIUM) {
            ceilingFan.medium();
        } else if (preSpeed == CeilingFan.LOW) {
            ceilingFan.low();
        } else if (preSpeed == CeilingFan.OFF) {
            ceilingFan.off();
        }
        System.out.println("当前风扇档位:" + ceilingFan.getSpeed());
    }
}
