package structure.bridge.bridge_c;

/**
 * @author lingwh
 * @desc 特急消息接口
 * @date 2019/7/24 11:28
 */
public interface SpecialUrgencyMessage extends MessageInterface {
    /**
     * 催促功能，没有发送就催一下
     */
    void hurry();
}
