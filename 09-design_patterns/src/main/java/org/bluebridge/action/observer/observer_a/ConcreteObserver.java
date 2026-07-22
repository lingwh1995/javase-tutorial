package org.bluebridge.action.observer.observer_a;

/**
 * 具体观察者
 *
 * @author lingwh
 * @date 2019/8/19 13:44
 */
public class ConcreteObserver implements Observer {

    /**
     * 示意，观者者的状态
     */
    private String observerState;

    @Override
    public void update(Subject subject) {
        // 具体的更新实现
        // 这里可能需要更新观察者的状态，使其与目标的状态保持一致
        observerState = ((ConcreteSubject) subject).getSubjectState();
    }
}
