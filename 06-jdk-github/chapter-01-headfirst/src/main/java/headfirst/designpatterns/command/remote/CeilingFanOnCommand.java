package headfirst.designpatterns.command.remote;

/**
 * 吊扇开启命令
 *
 * @author lingwh
 * @date 2026/7/9 00:00
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
