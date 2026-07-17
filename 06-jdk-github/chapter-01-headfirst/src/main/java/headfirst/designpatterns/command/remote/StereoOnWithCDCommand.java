package headfirst.designpatterns.command.remote;

/**
 * 音响开启CD命令
 *
 * @author lingwh
 * @date 2026/4/21 19:02
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
