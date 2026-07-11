package headfirst.designpatterns.command.remote;

/**
 * @author lingwh
 * @desc 热水浴缸开启命令
 * @date 2026/7/9 00:00
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
