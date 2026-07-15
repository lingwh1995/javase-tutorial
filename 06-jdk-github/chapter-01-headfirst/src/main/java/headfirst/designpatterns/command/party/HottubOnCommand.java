package headfirst.designpatterns.command.party;

/**
 * 浴缸打开命令
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
        hottub.setTemperature(104);
        hottub.circulate();
    }

    public void undo() {
        hottub.off();
    }
}
