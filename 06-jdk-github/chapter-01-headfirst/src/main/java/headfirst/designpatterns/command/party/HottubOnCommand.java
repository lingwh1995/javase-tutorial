package headfirst.designpatterns.command.party;

/**
 * @author lingwh
 * @desc 浴缸打开命令
 * @date 2026/7/9 00:00
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
