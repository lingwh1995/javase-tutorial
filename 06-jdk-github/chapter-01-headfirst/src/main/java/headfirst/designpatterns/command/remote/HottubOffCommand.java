package headfirst.designpatterns.command.remote;

/**
 * 热水浴缸关闭命令
 *
 * @author lingwh
 * @date 2023/12/7 15:17
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
