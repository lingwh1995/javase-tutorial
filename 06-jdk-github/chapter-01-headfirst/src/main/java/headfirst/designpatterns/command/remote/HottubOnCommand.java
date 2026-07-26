package headfirst.designpatterns.command.remote;

/**
 * 热水浴缸开启命令
 *
 * @author lingwh
 * @date 2023/12/7 14:52
 */
public class HottubOnCommand implements Command {

    Hottub hottub;

    public HottubOnCommand(Hottub hottub) {
        this.hottub = hottub;
    }

    @Override
    public void execute() {
        hottub.on();
        hottub.heat();
        hottub.bubblesOn();
    }
}
