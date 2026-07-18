package headfirst.designpatterns.command.party;

/**
 * 电视关闭命令
 *
 * @author lingwh
 * @date 2026/7/13 19:02
 */
public class TVOffCommand implements Command {

    TV tv;

    public TVOffCommand(TV tv) {
        this.tv = tv;
    }

    public void execute() {
        tv.off();
    }

    public void undo() {
        tv.on();
    }
}
