package headfirst.designpatterns.command.party;

/**
 * 音响打开命令
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class StereoOnCommand implements Command {

    Stereo stereo;

    public StereoOnCommand(Stereo stereo) {
        this.stereo = stereo;
    }

    public void execute() {
        stereo.on();
    }

    public void undo() {
        stereo.off();
    }
}
