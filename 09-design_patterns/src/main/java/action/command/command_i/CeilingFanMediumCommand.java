package action.command.command_i;

import action.command.command_h.Command;

/**
 * @author lingwh
 * @desc 吊扇中档命令
 * @date 2019/9/4 17:50
 */
public class CeilingFanMediumCommand implements Command {

    /**
     * 持有吊扇的引用
     */
    CeilingFan ceilingFan;

    /**
     * 吊扇之前的风速
     */
    int preSpeed;

    public CeilingFanMediumCommand(CeilingFan ceilingFan) {
        this.ceilingFan = ceilingFan;
    }

    /**
     * 执行命令
     */
    @Override
    public void execute() {
        preSpeed = ceilingFan.getSpeed();
        System.out.println("上一次风扇档位:" + preSpeed);
        ceilingFan.medium();
        System.out.println("当前风扇档位:" + ceilingFan.getSpeed());
    }

    /**
     * 撤销命令
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
