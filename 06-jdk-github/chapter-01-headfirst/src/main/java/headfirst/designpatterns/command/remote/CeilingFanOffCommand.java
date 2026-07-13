package headfirst.designpatterns.command.remote;

/**
 * 吊扇关闭命令
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class CeilingFanOffCommand implements Command {

    CeilingFan ceilingFan;

    public CeilingFanOffCommand(CeilingFan ceilingFan) {
        this.ceilingFan = ceilingFan;
    }

    public void execute() {
        ceilingFan.off();
    }
}
