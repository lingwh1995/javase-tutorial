package headfirst.designpatterns.command.party;

/**
 * 音响打开命令
 *
 * @author lingwh
 * @date 2023/12/7 14:36
 */
public class StereoOnCommand implements Command {

    Stereo stereo;

    public StereoOnCommand(Stereo stereo) {
        this.stereo = stereo;
    }

    @Override
    public void execute() {
        stereo.on();
    }

    @Override
    public void undo() {
        stereo.off();
    }
}
