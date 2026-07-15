package structure.bridge.bridge_e;

/**
 * 发送消息的接口
 *
 * @author lingwh
 * @date 2019/7/24 13:31
 */
public interface MessageImplementor {

    /**
     * @param message 要发送的消息内容
     * @param toUser  接收消息的用户
     */
    void send(String message, String toUser);
}
