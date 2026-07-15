package headfirst.designpatterns.command.remote;

/**
 * 热水浴缸开启命令
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class HottubOnCommand implements Command {

    Hottub hottub;

    public HottubOnCommand(Hottub hottub) {
        this.hottub = hottub;
    }

    public void execute() {
        hottub.on();
        hottub.heat();
        hottub.bubblesOn();
    }
}
