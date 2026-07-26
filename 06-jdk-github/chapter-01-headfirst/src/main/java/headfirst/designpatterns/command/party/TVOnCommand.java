package headfirst.designpatterns.command.party;

/**
 * 电视打开命令
 *
 * @author lingwh
 * @date 2023/12/7 10:19
 */
public class TVOnCommand implements Command {

    TV tv;

    public TVOnCommand(TV tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.on();
        tv.setInputChannel();
    }

    @Override
    public void undo() {
        tv.off();
    }
}
