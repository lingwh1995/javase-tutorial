package org.bluebridge.action.observer.observer_d;

/**
 * 观察者接口
 *
 * @author lingwh
 * @date 2019/8/19 13:50
 */
public interface Observer {

    /**
     * 被通知的方法
     *
     * @param subject 具体的目标对象，可以获取报纸的内容
     */
    void update(Subject subject);
}
