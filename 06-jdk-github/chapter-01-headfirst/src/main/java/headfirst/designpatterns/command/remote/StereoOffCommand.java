package headfirst.designpatterns.command.remote;

/**
 * 音响关闭命令
 *
 * @author lingwh
 * @date 2023/12/7 18:49
 */
public class StereoOffCommand implements Command {

    Stereo stereo;

    public StereoOffCommand(Stereo stereo) {
        this.stereo = stereo;
    }

    @Override
    public void execute() {
        stereo.off();
    }
}
