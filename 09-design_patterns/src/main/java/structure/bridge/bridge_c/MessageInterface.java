package structure.bridge.bridge_c;

/**
 * @author lingwh
 * @desc 消息接口
 * @date 2019/7/24 11:02
 */
public interface MessageInterface {
    boolean sendMssage(String message, String receiver);
}
