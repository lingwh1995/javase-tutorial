package org.bluebridge.action.mediator.mediator_a;

/**
 * 具体同事类 2：音响设备
 *
 * @author lingwh
 * @date 2019/7/29 9:22
 */
public class MusicDevice extends SmartDevice {

    /**
     * 相关设备打开之后 使其进入准备状态
     *
     * @param instruction
     */
    @Override
    public void readyState(String instruction) {
        System.out.println("音乐设备准备" + instruction);
    }

    /**
     * 操作该设备
     *
     * @param instruction
     * @param mediator
     */
    @Override
    public void operateDevice(String instruction, SmartMediator mediator) {
        System.out.println("音乐设备已" + instruction);
        mediator.music(instruction);
    }
}
