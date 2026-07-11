package headfirst.designpatterns.command.party;

/**
 * @author lingwh
 * @desc 电视关闭命令
 * @date 2026/7/9 00:00
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
