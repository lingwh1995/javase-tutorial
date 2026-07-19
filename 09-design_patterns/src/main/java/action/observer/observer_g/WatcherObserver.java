package action.observer.observer_g;

/**
 * 水质观察者接口定义
 *
 * @author lingwh
 * @date 2019/8/19 16:08
 */
public interface WatcherObserver {

    /**
     * 被通知的方法
     *
     * @param subject 传入被观察的目标对象
     */
    void update(WaterQualitySubject subject);

    /**
     * 设置观察人员的职务
     *
     * @param job 观察人员的职务
     */
    void setJob(String job);

    /**
     * 获取观察人员的职务
     *
     * @return 观察人员的职务
     */
    String getJob();
}
