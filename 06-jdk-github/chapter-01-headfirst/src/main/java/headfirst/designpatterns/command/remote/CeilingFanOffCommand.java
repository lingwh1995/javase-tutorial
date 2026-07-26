package headfirst.designpatterns.command.remote;

/**
 * 吊扇关闭命令
 *
 * @author lingwh
 * @date 2023/12/7 08:33
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
