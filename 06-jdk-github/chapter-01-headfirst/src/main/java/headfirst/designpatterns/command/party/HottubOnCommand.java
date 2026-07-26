package headfirst.designpatterns.command.party;

/**
 * 浴缸打开命令
 *
 * @author lingwh
 * @date 2023/12/7 12:49
 */
public class HottubOnCommand implements Command {

    Hottub hottub;

    public HottubOnCommand(Hottub hottub) {
        this.hottub = hottub;
    }

    @Override
    public void execute() {
        hottub.on();
        hottub.setTemperature(104);
        hottub.circulate();
    }

    @Override
    public void undo() {
        hottub.off();
    }
}
