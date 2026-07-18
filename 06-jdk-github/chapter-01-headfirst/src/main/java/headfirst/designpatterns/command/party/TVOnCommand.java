package headfirst.designpatterns.command.party;

/**
 * 电视打开命令
 *
 * @author lingwh
 * @date 2026/7/13 19:02
 */
public class TVOnCommand implements Command {

    TV tv;

    public TVOnCommand(TV tv) {
        this.tv = tv;
    }

    public void execute() {
        tv.on();
        tv.setInputChannel();
    }

    public void undo() {
        tv.off();
    }
}
