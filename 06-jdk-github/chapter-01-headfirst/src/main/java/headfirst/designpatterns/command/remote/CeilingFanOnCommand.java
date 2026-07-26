package headfirst.designpatterns.command.remote;

/**
 * 吊扇开启命令
 *
 * @author lingwh
 * @date 2023/12/7 21:36
 */
public class CeilingFanOnCommand implements Command {

    CeilingFan ceilingFan;

    public CeilingFanOnCommand(CeilingFan ceilingFan) {
        this.ceilingFan = ceilingFan;
    }

    public void execute() {
        ceilingFan.high();
    }
}
