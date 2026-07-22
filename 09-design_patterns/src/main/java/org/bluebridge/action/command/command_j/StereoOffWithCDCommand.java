package org.bluebridge.action.command.command_j;

/**
 * 音响设备关闭命令对象
 *
 * @author lingwh
 * @date 2019/9/4 15:19
 */
public class StereoOffWithCDCommand implements Command {

    Stereo stereo;

    public StereoOffWithCDCommand(Stereo stereo) {
        this.stereo = stereo;
    }

    /**
     * 执行关闭音响设备命令
     */
    @Override
    public void execute() {
        stereo.off();
    }

    /**
     * 撤销关闭音响设备命令
     */
    @Override
    public void undo() {
        stereo.on();
    }
}
