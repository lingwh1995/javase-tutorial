package headfirst.designpatterns.command.party;

/**
 * @author lingwh
 * @desc 音响CD打开命令
 * @date 2026/7/9 00:00
 */
public class StereoOnWithCDCommand implements Command {
    Stereo stereo;

    public StereoOnWithCDCommand(Stereo stereo) {
        this.stereo = stereo;
    }

    public void execute() {
        stereo.on();
        stereo.setCD();
        stereo.setVolume(11);
    }

    public void undo() {
        stereo.off();
    }
}
