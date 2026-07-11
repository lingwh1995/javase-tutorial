package headfirst.designpatterns.command.party;

/**
 * @author lingwh
 * @desc 音响打开命令
 * @date 2026/7/9 00:00
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
