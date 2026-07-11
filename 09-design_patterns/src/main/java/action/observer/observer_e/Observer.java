package action.observer.observer_e;

/**
 * @author lingwh
 * @desc 推模型的观察者，比如报纸的读者
 * @date 2019/8/19 15:00
 */
public interface Observer {

    /**
     * 被通知的方法，直接把报纸的内容推送过来
     *
     * @param content 报纸的内容
     */
    void update(String content);
}
