package headfirst.designpatterns.command.remote;

/**
 * @author lingwh
 * @desc 音响开启CD命令
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
}
