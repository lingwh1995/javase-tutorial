package headfirst.designpatterns.command.party;

/**
 * @author lingwh
 * @desc 浴缸关闭命令
 * @date 2026/7/9 00:00
 */
public class HottubOffCommand implements Command {
    Hottub hottub;

    public HottubOffCommand(Hottub hottub) {
        this.hottub = hottub;
    }

    public void execute() {
        hottub.setTemperature(98);
        hottub.off();
    }

    public void undo() {
        hottub.on();
    }
}
