package headfirst.designpatterns.command.party;

/**
 * 电视关闭命令
 *
 * @author lingwh
 * @date 2023/12/7 11:42
 */
public class TVOffCommand implements Command {

    TV tv;

    public TVOffCommand(TV tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.off();
    }

    @Override
    public void undo() {
        tv.on();
    }
}
