package headfirst.designpatterns.command.remote;

/**
 * 音响开启 CD 命令
 *
 * @author lingwh
 * @date 2023/12/7 17:26
 */
public class StereoOnWithCDCommand implements Command {

    Stereo stereo;

    public StereoOnWithCDCommand(Stereo stereo) {
        this.stereo = stereo;
    }

    @Override
    public void execute() {
        stereo.on();
        stereo.setCD();
        stereo.setVolume(11);
    }
}
