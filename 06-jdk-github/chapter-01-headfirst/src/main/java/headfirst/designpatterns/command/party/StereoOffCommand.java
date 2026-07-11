package headfirst.designpatterns.command.party;

/**
 * @author lingwh
 * @desc 音响关闭命令
 * @date 2026/7/9 00:00
 */
public class StereoOffCommand implements Command {
    Stereo stereo;

    public StereoOffCommand(Stereo stereo) {
        this.stereo = stereo;
    }

    public void execute() {
        stereo.off();
    }

    public void undo() {
        stereo.on();
    }
}
