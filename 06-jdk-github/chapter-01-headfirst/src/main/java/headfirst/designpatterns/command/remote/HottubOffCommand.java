package headfirst.designpatterns.command.remote;

/**
 * @author lingwh
 * @desc 热水浴缸关闭命令
 * @date 2026/7/9 00:00
 */
public class HottubOffCommand implements Command {
    Hottub hottub;

    public HottubOffCommand(Hottub hottub) {
        this.hottub = hottub;
    }

    public void execute() {
        hottub.cool();
        hottub.off();
    }
}
