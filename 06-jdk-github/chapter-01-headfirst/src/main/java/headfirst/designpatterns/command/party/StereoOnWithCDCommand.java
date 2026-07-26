package headfirst.designpatterns.command.party;

/**
 * 音响 CD 打开命令
 *
 * @author lingwh
 * @date 2023/12/7 13:51
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

    @Override
    public void undo() {
        stereo.off();
    }
}
