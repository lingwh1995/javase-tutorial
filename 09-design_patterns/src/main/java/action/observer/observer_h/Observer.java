package action.observer.observer_h;

/**
 * @author lingwh
 * @desc 观察者接口
 * @date 2019/8/30 9:58
 */
public interface Observer {
    /**
     * 观察者接收消息的方法
     */
    void update(Subject subject);
}
