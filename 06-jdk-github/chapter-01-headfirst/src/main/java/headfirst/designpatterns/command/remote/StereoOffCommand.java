package headfirst.designpatterns.command.remote;

/**
 * 音响关闭命令
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class StereoOffCommand implements Command {

    Stereo stereo;

    public StereoOffCommand(Stereo stereo) {
        this.stereo = stereo;
    }

    public void execute() {
        stereo.off();
    }
}
