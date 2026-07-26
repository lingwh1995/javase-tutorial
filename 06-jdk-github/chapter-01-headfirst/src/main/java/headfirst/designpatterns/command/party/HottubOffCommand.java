package headfirst.designpatterns.command.party;

/**
 * 浴缸关闭命令
 *
 * @author lingwh
 * @date 2023/12/7 13:37
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
