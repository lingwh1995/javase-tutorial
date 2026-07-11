package headfirst.designpatterns.command.remote;

/**
 * @author lingwh
 * @desc 吊扇关闭命令
 * @date 2026/7/9 00:00
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
