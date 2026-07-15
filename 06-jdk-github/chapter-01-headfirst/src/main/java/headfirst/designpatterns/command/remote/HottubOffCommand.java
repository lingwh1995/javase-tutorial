package headfirst.designpatterns.command.remote;

/**
 * 热水浴缸关闭命令
 *
 * @author lingwh
 * @date 2026/4/21 19:02
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
